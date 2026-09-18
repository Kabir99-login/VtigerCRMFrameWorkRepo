package genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	FileInputStream fis;
	public String readDataFromExcel(String sheetName,int rowNum,int cellNum) throws Throwable
	{ 
		fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		return wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		}
	public int getRowCount(String sheetName) throws Throwable
	{
		fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		return wb.getSheet(sheetName).getPhysicalNumberOfRows();
	}
	public int getCellCount(String sheetName) throws Throwable
	{
		fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		return wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
	}
	public void writeDataInExistingCell(String sheetName,int rowNum,int cellNum,String value) throws Throwable, IOException
	{
		fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).setCellValue(value);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
	}
	public void writeDataInNewCell(String sheetName, int rowNum, int cellNum, String value) throws Throwable, IOException
	{
		fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(value);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/TestData.xlsx");
		wb.write(fos);
	}
	
	public String formatedDataFromExcel(String sheetName,int rowNum,int cellNum) throws Throwable
	{
		fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		
		DataFormatter df=new DataFormatter();
		return df.formatCellValue(wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
		
	}
	
	

}
