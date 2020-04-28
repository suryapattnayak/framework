package com.Amazon.ExcelReader;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exelreader {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;

	public Exelreader(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int colCount(){
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Total Columns :"+colCount);
		return colCount;
	}
	
	public String getSheetData(int rowNum, int colmNum){
		String data = sheet.getRow(rowNum).getCell(colmNum).getStringCellValue();
		System.out.println(data);
		return data;
	}
	
	public int rowCount() {
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Total Rows :"+rowCount);
		return rowCount;
	}
	
	public static String wordRead(String sheetName, String colName, int rowNum)
	{
		
		int col_Num = -1;
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        for(int i = 0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                col_Num = i;
        }
        row = sheet.getRow(rowNum - 1);
        cell = row.getCell(col_Num);
        
		return cell.getStringCellValue();
		
	}
}
