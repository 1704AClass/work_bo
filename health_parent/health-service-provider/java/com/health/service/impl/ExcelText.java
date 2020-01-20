package com.health.service.impl;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelText {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		//创建工作薄
		
		XSSFWorkbook workbook = new XSSFWorkbook("D:\\wb.xlsx");
		XSSFSheet sheet = workbook.getSheetAt(0);
		//遍历工作薄
		
		for (Row row : sheet) {
	
			for (Cell cell : row) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String stringCellValue = cell.getStringCellValue();
				System.out.println(stringCellValue);
			}
		}	
		
		workbook.close();
	}
}
