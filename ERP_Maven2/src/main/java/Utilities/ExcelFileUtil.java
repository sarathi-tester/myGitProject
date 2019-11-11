package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil
{
	Workbook wb;
	//to load Excel file
	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fis=new FileInputStream("./TestInputs/InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
	}
	//Count no.of rows
	public  int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	// Count no.of columns
	public  int colCount(String sheetname,int rownum)
	{
		return wb.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}
	// get data from cell
	public  String getData(String sheetname,int rownum,int colnum)
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getNumericCellValue();
			data= String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(rownum).getCell(colnum).getStringCellValue();
		}
		return data;
	}
	
	// Writing data
	public void setData(String sheetname,int row,int colnum,String status)throws Throwable
	{
		Sheet sh=wb.getSheet(sheetname);
		Row rownum=sh.getRow(row);
		Cell cell=rownum.createCell(colnum);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			//Apply color to the text
			font.setColor(IndexedColors.GREEN.getIndex());
			//Apply bold 
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			//Apply color to the text
			font.setColor(IndexedColors.RED.getIndex());
			//Apply bold 
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		if(status.equalsIgnoreCase("NotExecuted"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			//Apply color to the text
			font.setColor(IndexedColors.BLUE.getIndex());
			//Apply bold 
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(colnum).setCellStyle(style);
		}
		
		FileOutputStream fos=new FileOutputStream("./TestOutput/HybridResults.xlsx");
		wb.write(fos);
		//wb.close();
	}
		
}
