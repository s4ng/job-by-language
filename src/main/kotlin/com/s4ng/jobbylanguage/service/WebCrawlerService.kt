package com.s4ng.jobbylanguage.service

import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlButton
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.s4ng.jobbylanguage.model.dto.JobOpeningDto
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.Objects
import java.util.logging.Level
import java.util.logging.Logger
import java.util.stream.Collectors

@Service
class WebCrawlerService {

    fun getDataFromWeb(): MutableList<JobOpeningDto> {

        val webClient = WebClient()
        webClient.options.isThrowExceptionOnScriptError = false
        webClient.options.isJavaScriptEnabled = true
        webClient.options.isRedirectEnabled = true

        Logger.getLogger("com.gargoylesoftware").level = Level.OFF
        val page = webClient.getPage<HtmlPage>("https://www.sara" + "min.co.kr/zf_user/jobs/list/job-category")
        webClient.waitForBackgroundJavaScript(50000)

        val itButton = page.getFirstByXPath<HtmlButton>("//*[@id=\"sp_main_wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/button[6]")
        itButton.click<Page>()
        webClient.waitForBackgroundJavaScript(50000)

        val buttonList: MutableList<HtmlButton> = ArrayList()
        var count = 1
        var jobNameButton: HtmlButton?
        do {
            jobNameButton = page.getFirstByXPath("//*[@id=\"sp_job_category_subDepth_2\"]/div[2]/div/div[2]/div/dl[3]/dd/button[$count]")
            if (jobNameButton != null) {
                buttonList.add(jobNameButton)
            }
            count++
        } while (jobNameButton != null)

        return buttonList.stream().map { obj: HtmlButton -> obj.asNormalizedText() }
                .map { text: String -> toDto(text) }
                .collect(Collectors.toList())
    }

    private fun toDto(text: String): JobOpeningDto? {

        val name: String
        val count: String
        val tokens = text.split("\\(".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        name = tokens[0]
        count = tokens[1].substring(0, tokens[1].length - 1).replace(",".toRegex(), "")
        return JobOpeningDto(name, count.toInt())
    }
}