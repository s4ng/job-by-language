package com.s4ng.jobbylanguage.service

import com.s4ng.jobbylanguage.model.dto.*
import com.s4ng.jobbylanguage.model.entity.JobOpeningEntity
import com.s4ng.jobbylanguage.model.entity.StackEntity
import com.s4ng.jobbylanguage.model.enum.StackCategory
import com.s4ng.jobbylanguage.repository.JobOpeningRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class MainService(@Autowired val jobOpeningRepository: JobOpeningRepository) {

    val colorList: Array<String> = arrayOf(
            "rgba(204, 0, 0, 0.5)",
            "rgba(255, 0, 0, 0.5)",
            "rgba(255, 255, 0, 0.5)",
            "rgba(0, 255, 0, 0.5)",
            "rgba(0, 128, 255, 0.5)",
            "rgba(0, 0, 153 0.5)",
            "rgba(102, 0, 204, 0.5)"
    )

    fun getData(): StatisticsDataDto {

        val languageEntities: List<JobOpeningEntity> = jobOpeningRepository
                .findByStackStackCategoryAndCreatedTimeAfter(StackCategory.Language, ZonedDateTime.now().minusMonths(12))
        val frameworkEntities: List<JobOpeningEntity> = jobOpeningRepository
                .findByStackStackCategoryAndCreatedTimeAfter(StackCategory.Framework, ZonedDateTime.now().minusMonths(12))
        val databaseEntities: List<JobOpeningEntity> = jobOpeningRepository
                .findByStackStackCategoryAndCreatedTimeAfter(StackCategory.Database, ZonedDateTime.now().minusMonths(12))



        return StatisticsDataDto(
                languageRank = this.createRankDto(languageEntities, true),
                languageFlow = createFlowDto(languageEntities),
                frameworkRank = this.createRankDto(frameworkEntities, true),
                frameworkFlow = createFlowDto(languageEntities),
                databaseRank = this.createRankDto(databaseEntities, true),
                databaseFlow = createFlowDto(languageEntities),
                lowLanguageRank = this.createRankDto(languageEntities, false),
                lowFrameworkRank = this.createRankDto(frameworkEntities, false)
        )
    }

    fun createRankDto(jobOpenings: List<JobOpeningEntity>, isTop: Boolean): RankDataDto {

        val sorts: List<SimpleJobOpening> = this.getSortedSimpleJobOpenings(jobOpenings, isTop)
        val count: Int = if (isTop) 6 else 4

        val names: MutableList<String> = mutableListOf()
        val values: MutableList<Int> = mutableListOf()

        for (i: Int in 0..count) {
            names += sorts[i].name
            values += sorts[i].value / sorts[i].count
        }

        return RankDataDto(names, values)
    }

    fun createFlowDto(jobOpenings: List<JobOpeningEntity>): FlowDataDto {

        val sorts: List<SimpleJobOpening> = getSortedSimpleJobOpenings(jobOpenings, true)

        val top7StackNames: MutableList<String> = mutableListOf()
        for (i: Int in 0..6) {
            top7StackNames += sorts[i].name
        }

        val isMonth: Boolean = jobOpenings[0].createdTime.isBefore(ZonedDateTime.now().minusDays(32L))

        val flowData: MutableList<SingleFlowData> = mutableListOf()
        val labels: MutableList<Int> = mutableListOf()
        for (i: Int in 0..6) {
            val values: MutableList<Int> = mutableListOf()
            var now: Int = -1
            for (jobOpening: JobOpeningEntity in jobOpenings) {
                if (jobOpening.stack.name != top7StackNames[i]) {
                    continue
                }
                if (isMonth) {
                    if (jobOpening.createdTime.monthValue != now) {
                        if (i == 0) {
                            labels += jobOpening.createdTime.monthValue
                        }
                        now = jobOpening.createdTime.monthValue
                        values += jobOpening.value
                    }
                } else {
                    if (jobOpening.createdTime.dayOfMonth != now) {
                        if (i == 0) {
                            labels += jobOpening.createdTime.dayOfMonth
                        }
                        now = jobOpening.createdTime.dayOfMonth
                        values += jobOpening.value
                    }
                }
            }
            flowData += SingleFlowData(top7StackNames[i], values, backgroundColor = colorList[i], borderColor = colorList[i])
        }

        return FlowDataDto(flowData, labels, isMonth)
    }

    fun getSortedSimpleJobOpenings(jobOpenings: List<JobOpeningEntity>, isTop: Boolean): List<SimpleJobOpening> {

        val aMonthAgo: ZonedDateTime = ZonedDateTime.now().minusMonths(1L)
        val jobMap = HashMap<StackEntity, SimpleJobOpening>()
        for (job: JobOpeningEntity in jobOpenings) {
            if (job.createdTime.isAfter(aMonthAgo)) {
                if (!jobMap.containsKey(job.stack)) {
                    jobMap[job.stack] = SimpleJobOpening(job.stack.name, job.value, 1, job.createdTime)
                } else {
                    jobMap[job.stack]?.addValue(job.value)
                }
            }
        }

        val sorts: List<SimpleJobOpening>
        if (isTop) {
            sorts = jobMap.values.sortedBy { e -> e.value / e.count }.reversed()
        } else {
            sorts = jobMap.values.sortedBy { e -> e.value / e.count }
        }

        return sorts
    }
}