package com.n2yo.scraper.writer.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.n2yo.scraper.data.SatStatusEntry;

public interface SpreadSheetWriterService {

	public void createSpreadSheet(List<Future<SatStatusEntry>> data) throws IOException, InterruptedException, ExecutionException;
}
