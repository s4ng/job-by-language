package com.s4ng.jobbylanguage.repository

import com.s4ng.jobbylanguage.model.entity.JobOpeningEntity
import com.s4ng.jobbylanguage.model.enum.StackCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime

@Repository
interface JobOpeningRepository : JpaRepository<JobOpeningEntity, Long> {

    fun findByStackStackCategoryAndCreatedTimeAfter(category: StackCategory, time: ZonedDateTime): List<JobOpeningEntity>
}