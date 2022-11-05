package com.s4ng.jobbylanguage.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.s4ng.jobbylanguage.model.dto.JobOpeningDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebCrawlerService {

	public List< JobOpeningDto > getDataFromWeb() throws IOException {

		WebClient webClient = new WebClient();
		webClient.getOptions().setThrowExceptionOnScriptError( false );
		webClient.getOptions().setJavaScriptEnabled( true );
		webClient.getOptions().setRedirectEnabled( true );
		java.util.logging.Logger.getLogger( "com.gargoylesoftware" ).setLevel( Level.OFF );

		HtmlPage page = webClient.getPage( "https://www.sara" + "min.co.kr/zf_user/jobs/list/job-category" );
		webClient.waitForBackgroundJavaScript( 50000 );

		HtmlButton itButton = page.getFirstByXPath( "//*[@id=\"sp_main_wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/button[6]" );
		itButton.click();
		webClient.waitForBackgroundJavaScript( 50000 );

		List< HtmlButton > buttonList = new ArrayList<>();

		int count = 1;
		HtmlButton jobNameButton;
		do {
			jobNameButton = page.getFirstByXPath( "//*[@id=\"sp_job_category_subDepth_2\"]/div[2]/div/div[2]/div/dl[3]/dd/button[" + count + "]" );
			if ( jobNameButton != null ) {
				buttonList.add( jobNameButton );
			}
			count++;
		} while ( jobNameButton != null );

		return buttonList.stream().map( HtmlButton::asNormalizedText )
				.map( this::toDto )
				.collect( Collectors.toList() );
	}

	private JobOpeningDto toDto( String text ) {

		String name;
		String count;

		String[] tokens = text.split( "\\(" );

		name = tokens[ 0 ];
		count = tokens[ 1 ].substring( 0, tokens[ 1 ].length() - 1 ).replaceAll( ",", "" );

		return new JobOpeningDto( name, Integer.parseInt( count ) );
	}
}
