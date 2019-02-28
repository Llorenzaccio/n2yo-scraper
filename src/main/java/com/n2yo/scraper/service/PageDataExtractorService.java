package com.n2yo.scraper.service;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.n2yo.scraper.data.SatStatusEntry;

public interface PageDataExtractorService {
	
	public SatStatusEntry extractData(HtmlPage page) throws IOException;
}
