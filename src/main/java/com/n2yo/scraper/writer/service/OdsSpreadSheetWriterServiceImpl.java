package com.n2yo.scraper.writer.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.springframework.stereotype.Service;

import com.n2yo.scraper.data.SatStatusEntry;

@Service
public class OdsSpreadSheetWriterServiceImpl implements SpreadSheetWriterService {
	
	private static final int HEADER_ROW = 1;

	public void createSpreadSheet(List<Future<SatStatusEntry>> data) throws IOException, InterruptedException, ExecutionException {
		 final SpreadSheet spreadSheet = SpreadSheet.create(1, 5, 1000);
		 Sheet sheet = spreadSheet.getSheet(0);
		 int row = 2;
		 int currentInOrbit = 0;
		 sheet.getCellAt("B" + HEADER_ROW).setValue("DATE");
		 sheet.getCellAt("B" + HEADER_ROW).setValue("SENT");
		 sheet.getCellAt("C" + HEADER_ROW).setValue("REMAINING @ " + data.get(0).get().getExtractionDate());
		 for (Future<SatStatusEntry> entry : data) {
			 currentInOrbit += entry.get().getInOrbit();
			 sheet.getCellAt("A" + row).setValue(entry.get().getMonth() + "/"+entry.get().getYear());
			 sheet.getCellAt("B" + row).setValue(entry.get().getDecayed() + entry.get().getInOrbit());
			 sheet.getCellAt("C" + row).setValue(currentInOrbit);
			 row++;
		 }
		 // Or better yet use a named range
		 // (relative to the first cell of the range, wherever it might be).
//		 sheet.getSpreadSheet().getTableModel("Products").setValueAt(1, 5, 4);
		 // Save to file and open it.
		 File outputFile = new File("fillingTest.ods");
		 OOUtils.open(sheet.getSpreadSheet().saveAs(outputFile));
	}

}
