package com.s4ng.jobbylanguage.service

import com.s4ng.jobbylanguage.model.dto.*
import com.s4ng.jobbylanguage.model.entity.JobOpeningEntity
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
                languageFlow = createFlowDto(languageEntities, false),
                frameworkRank = this.createRankDto(frameworkEntities, true),
                frameworkFlow = createFlowDto(frameworkEntities, true),
                databaseRank = this.createRankDto(databaseEntities, true),
                databaseFlow = createFlowDto(databaseEntities, false),
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

    fun createFlowDto(jobOpenings: List<JobOpeningEntity>, isFramework: Boolean): FlowDataDto {

        val sorts: List<SimpleJobOpening> = getSortedSimpleJobOpenings(jobOpenings, true)
        val stackSize = if (isFramework) 8 else 6

        val top7StackNames: MutableList<String> = mutableListOf()
        for (i: Int in 0..6) {
            top7StackNames += sorts[i].name
        }
        if (isFramework) {
            top7StackNames += "springboot"
            top7StackNames += "reactjs"
        }

        val isMonth: Boolean = jobOpenings[0].createdTime.isBefore(ZonedDateTime.now().minusDays(91L))

        var flowData: MutableList<SingleFlowData> = mutableListOf()
        val labels: MutableList<Int> = mutableListOf()
        for (i: Int in 0..stackSize) {
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
            flowData += SingleFlowData(top7StackNames[i], values, null, null)
        }

        if (isFramework) {
            var springIndex: Int? = null;
            var springBootIndex: Int? = null;
            var reactIndex: Int? = null;
            var reactjsIndex: Int? = null;
            for (j: Int in 0..8) {
                if (top7StackNames[j] == "spring") {
                    springIndex = j;
                }
                if (top7StackNames[j] == "springboot") {
                    springBootIndex = j;
                }
                if (top7StackNames[j] == "react") {
                    reactIndex = j;
                }
                if (top7StackNames[j] == "reactjs") {
                    reactjsIndex = j;
                }
            }
            for (j: Int in 0 until labels.size) {
                if (springIndex != null && springBootIndex != null) {
                    flowData[springIndex].data[j] += flowData[springBootIndex].data[j];
                }
                if (reactIndex != null && reactjsIndex != null) {
                    flowData[reactIndex].data[j] += flowData[reactjsIndex].data[j];
                }
            }
        }


        if (flowData.size > 7) {
            flowData = flowData.subList(0, 7);
        }
        for (i: Int in 0..6) {
            flowData[i].setColor(colorList[i])
        }

        return FlowDataDto(flowData, labels, isMonth)
    }

    fun getSortedSimpleJobOpenings(jobOpenings: List<JobOpeningEntity>, isTop: Boolean): List<SimpleJobOpening> {

        val aMonthAgo: ZonedDateTime = ZonedDateTime.now().minusMonths(1L)
        val jobMap = HashMap<String, SimpleJobOpening>()
        for (job: JobOpeningEntity in jobOpenings) {
            if (job.createdTime.isAfter(aMonthAgo)) {
                if (!jobMap.containsKey(job.stack.name)) {
                    jobMap[job.stack.name] = SimpleJobOpening(job.stack.name, job.value, 1, job.createdTime)
                } else {
                    jobMap[job.stack.name]?.addValue(job.value)
                }
            }
        }

        if (jobMap.containsKey("spring") && jobMap.containsKey("springboot")) {
            jobMap["spring"]!!.addOnlyValue(jobMap["springboot"]!!.value)
            jobMap.remove("springboot")
        }

        if (jobMap.containsKey("react") && jobMap.containsKey("reactjs")) {
            jobMap["react"]!!.addOnlyValue(jobMap["reactjs"]!!.value)
            jobMap.remove("reactjs")
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
