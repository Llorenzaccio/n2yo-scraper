package com.n2yo.scraper.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class N2yoUrlServiceImpl implements UrlService {
	
	private static final String BASE_URL = "https://www.n2yo.com/browse/";
	
	private static final int FIRST_YEAR = 1957;
		
	private static final int FIRST_MONTH = 10;
		
	@Override
	public List<String> generateUrls() {
		List<String> result = new ArrayList<>();
		for (int year = FIRST_YEAR; year <= getLatestYear(); year++) {
			for (int month = year == FIRST_YEAR ? FIRST_MONTH : 1; month <= (year == getLatestYear() ? getLatestMonth() : 12); month++) {
				result.add(buildUrl(year, month));
			}
		}
		return result;
	}
	
	private int getLatestYear() {
		return LocalDate.now().getYear();		
	}
	
	private int getLatestMonth() {
		return LocalDate.now().getMonthValue();
	}

	private String buildUrl(int year, int month) {
		return new StringBuilder().append(BASE_URL).append("?y=").append(year).append("&m=").append(String.format("%02d", month)).toString();
	}

}
