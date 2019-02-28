package com.n2yo.scraper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.n2yo.scraper.service.N2yoPageDataExtractorServiceImpl;
import com.n2yo.scraper.service.N2yoUrlServiceImpl;
import com.n2yo.scraper.service.PageDataExtractorService;
import com.n2yo.scraper.service.UrlService;
import com.n2yo.scraper.writer.service.OdsSpreadSheetWriterServiceImpl;
import com.n2yo.scraper.writer.service.SpreadSheetWriterService;
import com.ny2o.scraper.callable.SatStatusExtractor;
import com.n2yo.scraper.data.SatStatusEntry;

@SpringBootApplication
@Component
public class N2yoScraperApp implements CommandLineRunner{

	@Autowired
	static UrlService n2yoUrlService;

	@Autowired
	SpreadSheetWriterService odsSpreadSheetWriterService;


	@Autowired
	static PageDataExtractorService n2yoPageDataExtractorService;

	static List<Future<SatStatusEntry>> data;

	@Override
	public void run(String... args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		data = Collections.synchronizedList(new ArrayList<>());
		for(String url : getUrlService().generateUrls()) {
			data.add(executor.submit(new SatStatusExtractor(url)));
		}
		executor.shutdown();
		odsSpreadSheetWriterService.createSpreadSheet(data);
	}

	public static void main(String[] args) {		
		SpringApplication.run(N2yoScraperApp.class, args);
	}

	@Bean
	public UrlService getUrlService(){
		return new N2yoUrlServiceImpl();
	}

	@Bean
	public PageDataExtractorService getPageDataExtractorService(){
		return new N2yoPageDataExtractorServiceImpl();
	}

	@Bean
	public SpreadSheetWriterService getOdsSpreadSheetWriterService() {
		return new OdsSpreadSheetWriterServiceImpl();
	}
}
