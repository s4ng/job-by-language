package com.s4ng.jobbylanguage.service

import com.s4ng.jobbylanguage.bootstrap.JobbylanguageApplication
import com.s4ng.jobbylanguage.repository.JobOpeningRepository
import com.s4ng.jobbylanguage.repository.StackRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import javax.transaction.Transactional

@SpringBootTest(classes = [JobbylanguageApplication::class])
@ContextConfiguration(classes = [JobOpeningService::class, JobOpeningRepository::class, StackRepository::class])
internal class JobOpeningServiceTest(
        @Autowired val jobOpeningService: JobOpeningService,
        @Autowired val stackRepository: StackRepository
) {


    @Test
    @Transactional
    fun `job opening 저장 테스트`() {
        jobOpeningService.save("c++", 5)
        val jobOpeningEntity = stackRepository.findByNameIgnoreCase("c++");
        if (jobOpeningEntity != null) {
            assert(jobOpeningEntity.name.equals("c++"))
        }
    }
}