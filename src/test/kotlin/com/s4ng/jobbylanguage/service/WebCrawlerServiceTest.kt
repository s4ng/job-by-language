package com.s4ng.jobbylanguage.service

import com.s4ng.jobbylanguage.bootstrap.JobbylanguageApplication
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [JobbylanguageApplication::class])
internal class WebCrawlerServiceTest( @Autowired val webCrawlerService2: WebCrawlerService) {

    @Test
    fun `데이터 크롤링 테스트 2`() {
        webCrawlerService2.dataFromWeb
    }
}