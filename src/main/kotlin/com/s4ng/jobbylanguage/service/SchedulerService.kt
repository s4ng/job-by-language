package com.s4ng.jobbylanguage.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.io.IOException

@Component
class SchedulerService(
        @Autowired val webCrawlerService: WebCrawlerService,
        @Autowired val jobOpeningService: JobOpeningService) {

    @Scheduled(cron = "0 0 5 * * *", zone = "Asia/Seoul")
    fun everyDayJob() {
        val jobOpenings = webCrawlerService.getDataFromWeb()
        jobOpeningService.save(jobOpenings)
    }
}