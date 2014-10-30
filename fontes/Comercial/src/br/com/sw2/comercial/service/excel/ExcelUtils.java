package br.com.sw2.comercial.service.excel;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtils {
	
	private static final String FORMAT_DATE = "yyyyMMddHHmmss";
	public static final String PATH = "d:\\SW2\\OdonTO-DO\\fontes\\Comercial\\src\\main\\resources\\";

	public void addNumber(WritableSheet sheet, int coulmn, int row,
			Double numero) throws WriteException, RowsExceededException {
		jxl.write.Number number = new jxl.write.Number(coulmn, row, numero);
		sheet.addCell(number);
	}

	public void addLabel(WritableSheet sheet, int coulmn, int row,
			String text) throws WriteException, RowsExceededException {
		Label label = new Label(coulmn, row, text);
		sheet.addCell(label);
	}
	
	public void addDate(WritableSheet sheet, int coulmn, int row,
			Date date) throws WriteException, RowsExceededException {
		DateFormat customDateFormat = new DateFormat ("dd/MM/yyyy hh:mm"); 
		WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat); 
		DateTime dateCell = new DateTime(coulmn, row, date, dateFormat); 
		sheet.addCell(dateCell); 
	}

	public Path gerarPath(String nome) {
		SimpleDateFormat formatoData = new SimpleDateFormat(FORMAT_DATE);
		Calendar data = Calendar.getInstance();
		Path arquivo = Paths.get(PATH + nome +  formatoData.format(data.getTime()) + ".xls");
		return arquivo;
	}
}
