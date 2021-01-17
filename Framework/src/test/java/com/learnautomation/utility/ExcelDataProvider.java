package com.learnautomation.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider			//it will read data from excel 
{
	XSSFWorkbook wb;
	//this constructor will load the excel as soon as we create object of class, so that we dont have to provide excel path again and again
	public ExcelDataProvider()
	{
		
		File src = new File("./TestData/Data.xlsx");						
		try {
			FileInputStream fis = new FileInputStream(src);										//where it will convert this file into raw data and we will provide the source as well
																				
			wb = new XSSFWorkbook(fis);															//to read xlsx - HSSF and we provide file input stream object and we need wb variable across class, so we globalize this variable
			} catch (Exception e) {																//now these two statements show error or exception, so we surround both of them with try/catc
			
			System.out.println("Unable to read excel file"+e.getMessage()); 					//deleted printstack of trycatch and one try catch block also. and added sysout-its meaningful
			}																					//to print exception log = e.getmessage()
	}
	
	public String getStringData(String sheetName,int row, int column)			
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();			//this will return you a string hence return type string-taking mentioned variables as parameters		
	}
	public double getNumericData(String sheetName,int row, int column)							//get numeric cell value will return you a double
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}
	public String getStringData(int sheetIndex,int row, int column)								//this is method overloading where method names are same but the parameter types are different
	{
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();		//getsheetAt will take sheet number instead of sheet name		
	}
}
