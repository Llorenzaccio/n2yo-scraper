package com.n2yo.scraper.data;

import java.time.LocalDate;

public class SatStatusEntry {

	private int month;
	
	private int year;
	
	private LocalDate extractionDate;
	
	private int decayed;
	
	private int inOrbit;

	public SatStatusEntry(int month, int year, LocalDate extractionDate, int decayed, int inOrbit) {
		super();
		this.month = month;
		this.year = year;
		this.extractionDate = extractionDate;
		this.decayed = decayed;
		this.inOrbit = inOrbit;
	}
	
	@Override
	public String toString() {
		return "SatStatusEntry [month=" + month + ", year=" + year + ", extractionDate=" + extractionDate + ", decayed="
				+ decayed + ", inOrbit=" + inOrbit + "]";
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public LocalDate getExtractionDate() {
		return extractionDate;
	}

	public void setExtractionDate(LocalDate extractionDate) {
		this.extractionDate = extractionDate;
	}

	public int getDecayed() {
		return decayed;
	}

	public void setDecayed(int decayed) {
		this.decayed = decayed;
	}

	public int getInOrbit() {
		return inOrbit;
	}

	public void setInOrbit(int inOrbit) {
		this.inOrbit = inOrbit;
	}
}
