package com.n2yo.scraper.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.n2yo.scraper.data.SatStatusEntry;

public class N2yoPageDataExtractorServiceImpl implements PageDataExtractorService {
	
	SatStatusEntry satStatEntry;
	
	@Override
	public SatStatusEntry extractData(HtmlPage page) throws IOException {
		HtmlTable table = page.getHtmlElementById("satsbylaunchdatetab");
		int decayed = table.getByXPath("//table/tbody/tr/td[contains(.,'DECAYED')]").size() == 0 ? 0 : table.getByXPath("//table/tbody/tr/td[contains(.,'DECAYED')]").size() - 1;
		int inOrbit = table.getByXPath("//table/tbody/tr/td[contains(.,'IN ORBIT')]").size() == 0 ? 0 : table.getByXPath("//table/tbody/tr/td[contains(.,'IN ORBIT')]").size() - 1;
		int year = extractYear(page.getUrl().toString());
		int month = extractMonth(page.getUrl().toString());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + year + " - " + month);
		return new SatStatusEntry(month, year, LocalDate.now(), decayed, inOrbit);
	}

	private int extractYear(String url) {
		return extractRegexp(url, "([0-9]{4})");
	}
	
	private int extractMonth(String url) {
		return extractRegexp(url, "m=([0-9]{2})");
	}
	
	private int extractRegexp(String url, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(url);
		if (matcher.find())
		{
		    return Integer.parseInt(matcher.group(1));
		}
		return -1;
	}
}
