package utilities;

import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {

	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	//Constructor (By using constructor we can pass path of the excel)
	
	public Excelutils(String path)
	{
		
		this.path=path;
	}
	
	
	
	
public int getrowcount(String xlsheetname) throws IOException
{
	
	fi = new FileInputStream(path);
	wb = new XSSFWorkbook(fi);
	ws = wb.getSheet(xlsheetname);
	int rowcount = ws.getLastRowNum();
	
	wb.close();
	fi.close();
	return rowcount;
	
}

public int getcellcount(String xlsheetname, int rownum) throws IOException
{
	
	fi = new FileInputStream(path);
	wb = new XSSFWorkbook(fi);
	ws = wb.getSheet(xlsheetname);
	row = ws.getRow(rownum);
	int cellcount = row.getLastCellNum();
	
	wb.close();
	fi.close();
	return cellcount;
}
	
public String getcelldata(String xlsheetname, int rownum, int celnum) throws IOException 
{
	
	fi = new FileInputStream(path);
	wb = new XSSFWorkbook(fi);
	ws = wb.getSheet(xlsheetname);
	row = ws.getRow(rownum);
	cell = row.getCell(celnum);
	
	String data;
	
	try {
		
		//data= cell.toString();
		DataFormatter dataformat = new DataFormatter();
		data = dataformat.formatCellValue(cell); //Returns the formatted value of a cell as a string regardless of the cell type.
			
	} 
	catch (Exception e) 
	{
		
			data="";
	}
		
	wb.close();
	fi.close();
	return data;

	}
	

public void setcelldata(String xlsheetname, int rownum, int celnum, String data) throws IOException 
{

	File xlfile = new File(path);
	if(xlfile.exists()) 	// If file not exists then create new file
		
	{
		wb = new XSSFWorkbook();
		fo=new FileOutputStream(path);
		wb.write(fo);
	}
	
	fi=new FileInputStream(path);
	wb = new XSSFWorkbook(fi);
	
	if(wb.getSheetIndex(xlsheetname)==-1) // if sheet not exists then create new sheet
		wb.createSheet(xlsheetname);
	ws=wb.getSheet(xlsheetname);
	
	if(ws.getRow(rownum)==null) 	// If row not exists then create new row
		ws.createRow(rownum);
	row = ws.getRow(rownum);
	
	
	cell = row.createCell(celnum);
	cell.setCellValue(data);

	fo= new FileOutputStream(path);
	wb.write(fo);
	wb.close();
	fi.close();
	fo.close();


}


public void fillGreencolor(String xlsheetname, int rownum, int celnum ) throws IOException 
{

fi = new FileInputStream(path);
wb = new XSSFWorkbook(fi);
ws = wb.getSheet(xlsheetname);

row = ws.getRow(rownum);
cell =row.getCell(celnum);

style = wb.createCellStyle();

style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

cell.setCellStyle(style);
//fo= new FileOutputStream(path);
wb.write(fo);
wb.close();
fi.close();
fo.close();


}


public void fillRedcolor(String xlsheetname, int rownum, int celnum ) throws IOException 
{

fi = new FileInputStream(path);
wb = new XSSFWorkbook(fi);
ws = wb.getSheet(xlsheetname);
row = ws.getRow(rownum);
cell =row.getCell(celnum);

style = wb.createCellStyle();

style.setFillForegroundColor(IndexedColors.RED.getIndex());
style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

cell.setCellStyle(style);
//fo= new FileOutputStream(path);
wb.write(fo);
wb.close();
fi.close();
fo.close();

}








	
}	
	
	








	
	
	
	
	



	
	

