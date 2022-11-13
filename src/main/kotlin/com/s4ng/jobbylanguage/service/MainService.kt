package com.s4ng.jobbylanguage.service

import com.s4ng.jobbylanguage.model.dto.RankDataDto
import com.s4ng.jobbylanguage.model.dto.SimpleJobOpening
import com.s4ng.jobbylanguage.model.dto.StatisticsDataDto
import com.s4ng.jobbylanguage.model.entity.JobOpeningEntity
import com.s4ng.jobbylanguage.model.entity.StackEntity
import com.s4ng.jobbylanguage.model.enum.StackCategory
import com.s4ng.jobbylanguage.repository.JobOpeningRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class MainService(@Autowired val jobOpeningRepository: JobOpeningRepository) {

    fun getData(): StatisticsDataDto {

        val languageEntities: List<JobOpeningEntity> = jobOpeningRepository
                .findByStackStackCategoryAndCreatedTimeAfter(StackCategory.Language, ZonedDateTime.now().minusMonths(12))
        val frameworkEntities: List<JobOpeningEntity> = jobOpeningRepository
                .findByStackStackCategoryAndCreatedTimeAfter(StackCategory.Framework, ZonedDateTime.now().minusMonths(12))
        val databaseEntities: List<JobOpeningEntity> = jobOpeningRepository
                .findByStackStackCategoryAndCreatedTimeAfter(StackCategory.Database, ZonedDateTime.now().minusMonths(12))



        return StatisticsDataDto(
                languageRank = this.createRankDto(languageEntities, true),
                languageFlow = null,
                frameworkRank = this.createRankDto(frameworkEntities, true),
                frameworkFlow = null,
                databaseRank = this.createRankDto(databaseEntities, true),
                databaseFlow = null,
                lowLanguageRank = this.createRankDto(languageEntities, false),
                lowFrameworkRank = this.createRankDto(frameworkEntities, false)
        )
    }

    fun createRankDto(jobOpenings: List<JobOpeningEntity>, isTop: Boolean): RankDataDto {

        val aMonthAgo: ZonedDateTime = ZonedDateTime.now().minusMonths(1L)
        var jobMap = HashMap<StackEntity, SimpleJobOpening>()
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
        val count: Int
        if (isTop) {
            sorts = jobMap.values.sortedBy { e -> e.value / e.count }.reversed()
            count = 6;
        } else {
            sorts = jobMap.values.sortedBy { e -> e.value / e.count }
            count = 4;
        }

        var names: MutableList<String> = mutableListOf()
        var values: MutableList<Int> = mutableListOf()

        for (i: Int in 0..count) {
            names += sorts[i].name
            values += sorts[i].value / sorts[i].count
        }

        return RankDataDto(names, values)
    }
}