import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ComparisonOperator;
import java.io.*;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.BorderFormatting;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.util.CellReference;

public class StatsWriter 
{
	public ArrayList<String> allCategory = new ArrayList<String>();
	public ArrayList<String> hist = new ArrayList<String>();
	public ArrayList<String> lit = new ArrayList<String>();
	public ArrayList<String> sci = new ArrayList<String>();
	public ArrayList<String> arts = new ArrayList<String>();
	public ArrayList<String> geo = new ArrayList<String>();
	public ArrayList<String> phil = new ArrayList<String>();
	public ArrayList<String> rel = new ArrayList<String>();
	public ArrayList<String> ssci = new ArrayList<String>();
	public ArrayList<String> ce = new ArrayList<String>();
	public ArrayList<String> pc = new ArrayList<String>();
	public ArrayList<String> myth = new ArrayList<String>();
	public ArrayList<String> misc = new ArrayList<String>();
	public ArrayList<ArrayList> allList = new ArrayList<ArrayList>();
	public FileInputStream writeFile;
	public FileOutputStream output, output2;
	public HSSFWorkbook workbook;
	public HSSFSheet newSheet;
	Cell borderCell;
	Row borderRow;
	Workbook newWorkbook;
	
	public StatsWriter()
	{
		hist.add("Hist");
		hist.add("USHist");
		hist.add("EHist");
		hist.add("WHist");
		hist.add("AnHist");
		hist.add("OHist");
		
		lit.add("Lit");
		lit.add("USWorks");
		lit.add("BWorks");
		lit.add("CWorks");
		lit.add("WWorks");
		lit.add("USAuth");
		lit.add("BAuth");
		lit.add("CAuth");
		lit.add("WAuth");
		lit.add("OLit");
		
		sci.add("Sci");
		sci.add("Astro");
		sci.add("Bio");
		sci.add("Chem");
		sci.add("Phys");
		sci.add("SGeo");
		sci.add("GSci");
		sci.add("Math");
		sci.add("OSci");
		
		arts.add("FA");
		arts.add("MComp");
		arts.add("MPiece");
		arts.add("MPerf");
		arts.add("ArtA");
		arts.add("ArtW");
		arts.add("ArtArc");
		arts.add("FAOth");

		geo.add("Geo");
		geo.add("USGeo");
		geo.add("EGeo");
		geo.add("AsGeo");
		geo.add("AfGeo");
		geo.add("OGeo");
		
		phil.add("Phil");
		phil.add("Theo");
		phil.add("CPhil");
		phil.add("MPhil");
		phil.add("OPhil");
		
		rel.add("Rel");
		rel.add("JCRel");
		rel.add("WRel");
		rel.add("ORel");
		
		ssci.add("SSci");
		ssci.add("Econ");
		ssci.add("Anthro");
		ssci.add("Psych");
		ssci.add("Govt");
		ssci.add("OSSci");
		
		ce.add("CE");
		ce.add("USCE");
		ce.add("WCE");
		ce.add("OCE");
		
		pc.add("PC");
		pc.add("Sports");
		pc.add("TV");
		pc.add("Movies");
		pc.add("Music");
		pc.add("OPC");
		
		myth.add("Myth");
		myth.add("CMyth");
		myth.add("NMyth");
		myth.add("WMyth");
		myth.add("OMyth");
		
		misc.add("Misc");
		misc.add("GK");
		misc.add("MCK");
		misc.add("Other");
		
		allList.add(hist);
		allList.add(lit);
		allList.add(sci);
		allList.add(arts);
		allList.add(geo);
		allList.add(phil);
		allList.add(ssci);
		allList.add(ce);
		allList.add(pc);
		allList.add(myth);
		allList.add(misc);
		
	}
	
	public void createFile(String fileName)
	{
		newWorkbook = new HSSFWorkbook();
		makeSheet("CreatedSheet1");
	}
	
	public void getFile(String fileName)
	{
		try 
		{
			writeFile = new FileInputStream(new File(fileName));
			workbook = new HSSFWorkbook(writeFile);
			//HSSFSheet testSheet = workbook.getSheet("Testing123");
			//HSSFSheet testSheet2 = workbook.createSheet("Hoping");
			//System.out.println(testSheet.getRow(0).getCell(0).getStringCellValue());
			
		} 
		catch (FileNotFoundException e) //Couldn't find file 
		{
			e.printStackTrace();
		} 
		catch (IOException e) //Couldn't find/create workbook?
		{
			e.printStackTrace();
		}
	}
	
	public void write(String sheetName, int row, int column)
	{
		HSSFSheet writeSheet = workbook.getSheet(sheetName);
		//newSheet = workbook.createSheet("TestingSheet");
		Row writeRow = writeSheet.getRow(row);
		Cell readCell = writeRow.getCell(column);
		Cell writeCell = writeRow.createCell(column);
		
		CellReference cr = new CellReference(writeCell.getRowIndex(), writeCell.getColumnIndex());
		
		System.out.println(cr.formatAsString());
		
		Cell formulaCell = writeSheet.getRow(4).createCell(1);
		formulaCell.setCellFormula("SUM($B$2:$B$4)");
		writeCell.setCellValue(10 + readCell.getNumericCellValue());
	}
	
	public void createPlayerPage(String playerName, String sheetName, int row, int column)
	{
		HSSFSheet playerSheet = workbook.getSheet(sheetName);
		System.out.println(workbook.getSheetIndex("TestSheet"));
		//Row startRow = playerSheet.getRow(row);
		borderRow = playerSheet.getRow(row+1);
		borderCell = borderRow.createCell(column+1);
		Cell writingCell;
		CellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		borderStyle.setBorderTop(CellStyle.BORDER_THIN);
		borderCell.setCellStyle(borderStyle);
		for(int i=0; i<5; i++)
		{
			borderRow = playerSheet.getRow(row+1+i);
			writingCell = borderRow.createCell(column+3);
			writingCell.setCellValue(lit.get(i));
			System.out.println(lit.get(i));
			for(int j=0; j<5; j++)
			{
				
				borderCell = borderRow.createCell(column+4+j);
				borderCell.setCellStyle(borderStyle);
			}
		}
	}
	
	public void setFormula(String sheetName, int row, int column)
	{
		HSSFSheet writeSheet = workbook.getSheet(sheetName);
		Row writeRow = writeSheet.getRow(row);
		Cell formulaCell = writeRow.createCell(column);
		formulaCell.setCellFormula("SUM($B$2:$B$4");
	}
	
	public void closeFile()
	{
		try 
		{
			writeFile.close();
		} 
		catch (IOException e) //Couldn't close file
		{
			e.printStackTrace();
		}
	}
	
	public void outputFile(String fileName)
	{
		try 
		{
			output = new FileOutputStream(fileName);
			output2 = new FileOutputStream("QuizBowlCreatedSheet.xls");
			newWorkbook.write(output2);
			workbook.write(output);
			output2.close();
			output.close();
		} 
		catch (FileNotFoundException e) //Couldn't find file
		{
			e.printStackTrace();
		} 
		catch (IOException e) //Couldn't implement .write method
		{
			e.printStackTrace();
		}
		
	}
	
	public void removeCategory(ArrayList<String> bigCategory, String category)
	{
		bigCategory.remove(bigCategory.indexOf(category));
			
		for(ArrayList<String> categories : allList)
		{
			for(String currCat : categories)
			{
				System.out.print(currCat + " ");
			}
			System.out.println();
		}
		System.out.println();
		
	}
	
	public void transcribeSheet(String sheetName)
	{
		HSSFSheet sheet = workbook.getSheet(sheetName);
		Row tRow = sheet.getRow(108);
		Iterator<Cell> iterator = tRow.iterator();
		int cellsInRow = 5;
		Cell readCell = tRow.getCell(2);
		for(int i=0; i<6; i++)
		{
			if(tRow.getCell(i) != null)
			{
				System.out.println(tRow.getCell(i).getNumericCellValue());
			}
			if(tRow.getCell(i) == null)
			{
				System.out.println("Null cell");
			}
			/*if(readCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			{
				System.out.println(readCell.getNumericCellValue());
			}*/
		}
		
		
		
	}
	
	public void makeSheet(String sheetName)
	{
		Sheet sheetRunner = newWorkbook.createSheet(sheetName);
		for(int i=0; i<200; i++)
		{
			Row rowRunner = sheetRunner.createRow(i);
		}
	}
}
