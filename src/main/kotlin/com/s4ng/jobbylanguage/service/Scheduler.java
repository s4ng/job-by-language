package com.s4ng.jobbylanguage.service;

import com.s4ng.jobbylanguage.model.dto.JobOpeningDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

	private final WebCrawlerService webCrawlerService;
	private final JobOpeningService  jobOpeningService;

	@Scheduled(cron = "0 0 5 * * *", zone = "Asia/Seoul")
	public void everyDayJob() throws IOException {
		List< JobOpeningDto > jobOpenings = webCrawlerService.getDataFromWeb();
		jobOpeningService.save( jobOpenings );
	}
}
