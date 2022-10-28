package com.s4ng.jobbylanguage.service

import com.gargoylesoftware.htmlunit.WebClient
import com.s4ng.jobbylanguage.model.dto.JobOpeningDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WebCrawlerService(@Autowired jobOpeningService: JobOpeningService) {

    fun getDataFromWeb(): List<JobOpeningDto>? {

        val webClient = WebClient();
        return null
    }
}