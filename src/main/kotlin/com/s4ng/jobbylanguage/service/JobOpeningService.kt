package com.s4ng.jobbylanguage.service

import com.s4ng.jobbylanguage.model.dto.JobOpeningDto
import com.s4ng.jobbylanguage.model.entity.JobOpeningEntity
import com.s4ng.jobbylanguage.model.entity.StackEntity
import com.s4ng.jobbylanguage.repository.JobOpeningRepository
import com.s4ng.jobbylanguage.repository.StackRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class JobOpeningService(
        private val stackRepository: StackRepository,
        private val jobOpeningRepository: JobOpeningRepository
) {

    fun save(dtoList: List<JobOpeningDto>) {

        for (dto: JobOpeningDto in dtoList) {
            this.save(dto.name, dto.count)
        }
    }

    fun save(stackName: String, count: Int) {

        var stackEntity = stackRepository.findByNameIgnoreCase(stackName)
        if (stackEntity == null) {
            stackEntity = this.saveStack(stackName)
        }

        val jobOpeningEntity = JobOpeningEntity(stack = stackEntity, value = count, createdTime = ZonedDateTime.now())
        jobOpeningRepository.save(jobOpeningEntity)
    }

    fun saveStack(stackName: String): StackEntity {
        val stackEntity = StackEntity(name = stackName.lowercase())
        stackRepository.save(stackEntity)
        return stackEntity
    }
}