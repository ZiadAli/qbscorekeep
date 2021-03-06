import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ComparisonOperator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.awt.List;
import java.io.*;

import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.BorderFormatting;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.util.CellReference;

@SuppressWarnings("unused")
public class ExcelWriter 
{
	
	Workbook createWorkbook;
	XSSFWorkbook workbook;
	FileInputStream existingFile;
	FileOutputStream outputCreated, output;

	public static ArrayList<Statistic> statsCategories = new ArrayList<Statistic>();
	public static ArrayList<Statistic> bonusStats = new ArrayList<Statistic>();
	ArrayList<Statistic> onBonusStats;
	ArrayList<Statistic> onStats;
	
	ArrayList<String> statsLocations = new ArrayList<String>();
	ArrayList<Integer> categoryIndices = new ArrayList<Integer>();

	public static ArrayList<Category> categoryList = new ArrayList<Category>();
	ArrayList<Category> onCategoryList;
	
	public ExcelWriter()
	{
		Category all = new Category("ALL", "ALL", "All");
		
		Category history = new Category("HISTORY", "HISTORY", "Hist"); 
		Category aHistory = new Category("American", "HISTORY", "USHist"); 
		Category eHistory = new Category("European", "HISTORY", "EHist"); 
		Category wHistory = new Category("World", "HISTORY", "WHist"); 
		Category anHistory = new Category("Ancient", "HISTORY", "AnHist"); 
		Category oHistory = new Category("Other", "HISTORY", "OHist"); 
		
		Category lit = new Category("LITERATURE", "LITERATURE", "Lit"); 
		//Category worksLit = new Category("Works", "LITERATURE", "Work"); 
		Category usWorksLit = new Category("American", "LITERATURE", "USWork"); 
		Category britWorksLit = new Category("British", "LITERATURE", "UKWork"); 
		Category grWorksLit = new Category("Greco-Roman", "LITERATURE", "GRWork"); 
		Category worldWorksLit = new Category("World", "LITERATURE", "WWork");
		//Category authLit = new Category("Authors", "LITERATURE", "Auth");
		Category usAuthlit = new Category("American", "LITERATURE", "USAuth");
		Category brithAuthlit = new Category("British", "LITERATURE", "UKAuth");
		Category grAuthlit = new Category("Greco-Roman", "LITERATURE", "GRAuth");
		Category worldAuthlit = new Category("World", "LITERATURE", "WAuth");
		Category uslit = new Category("American", "LITERATURE", "USLit");
		Category britlit = new Category("British", "LITERATURE", "UKLit");
		Category grlit = new Category("Greco-Roman", "LITERATURE", "GRLit");
		Category worldlit = new Category("World", "LITERATURE", "WLit");
		Category otherlit = new Category("Other", "LITERATURE", "OLit");

		Category sci = new Category("SCIENCE", "SCIENCE", "Sci");
		Category astronomy = new Category("Astronomy", "SCIENCE", "Astro");
		Category biology = new Category("Biology", "SCIENCE", "Bio");
		Category chemistry = new Category("Chemistry", "SCIENCE", "Chem");
		Category physics = new Category("Physics", "SCIENCE", "Phys");
		Category math = new Category("Math", "SCIENCE", "Math");
		Category gSci = new Category("General", "SCIENCE", "GSci");
		Category otherSci = new Category("Other", "SCIENCE", "OSci");
		
		Category finearts = new Category("FINE ARTS", "FINE ARTS", "FiArt");
		Category music = new Category("Music", "FINE ARTS", "Music");
		Category composers = new Category("Musicians/Composers", "FINE ARTS", "MusComp");
		Category musPiece = new Category("Pieces", "FINE ARTS", "MPiece");
		Category musPerf = new Category("Performances", "FINE ARTS", "MPerf");
		Category otherMusic = new Category("Other", "FINE ARTS", "OMus");
		Category art = new Category("Art", "FINE ARTS", "Art");
		Category artists = new Category("Artists", "FINE ARTS", "Artist");
		Category artPiece = new Category("Piece", "FINE ARTS", "APiece");
		Category architecture = new Category("Architecture", "FINE ARTS", "Arch");
		Category otherArt = new Category("Other", "FINE ARTS", "OArt");
		
		Category geo = new Category("GEOGRAPHY", "GEOGRAPHY", "Geo");
		Category usGeo = new Category("American", "GEOGRAPHY", "USGeo");
		Category worldGeo = new Category("World", "GEOGRAPHY", "WGeo");
		Category otherGeo = new Category("Other", "GEOGRAPHY", "OGeo");
		
		Category phil = new Category("PHILOSOPHY", "PHILOSOPHY", "Phil");
		Category theo = new Category("Theology", "PHILOSOPHY", "Theo");
		Category classicalPhil = new Category("Classical", "PHILOSOPHY", "CPhil");
		Category modernPhil = new Category("Modern", "PHILOSOPHY", "MPhil");
		Category otherPhil = new Category("Other", "PHILOSOPHY", "OPhil");
		
		Category rel = new Category("RELIGION", "RELIGION", "Rel");
		Category christianity = new Category("Christianity", "RELIGION", "Christ");
		Category judaism = new Category("Judaism", "RELIGION", "Jud");
		Category worldRel = new Category("World", "RELIGION", "WRel");
		Category otherRel = new Category("Other", "RELIGION", "ORel");
		
		Category ssci = new Category("SOCIAL SCIENCE", "SOCIAL SCIENCE", "SSci");
		Category econ = new Category("Economics", "SOCIAL SCIENCE", "Econ");
		Category psych = new Category("Psychology", "SOCIAL SCIENCE", "Psych");
		Category govt = new Category("Government", "SOCIAL SCIENCE", "Govt");
		Category otherSSci = new Category("Other", "SOCIAL SCIENCE", "OSSci");
		
		Category current = new Category("CURRENT EVENTS", "CURRENT EVENTS", "CE");
		Category USCurrent = new Category("American", "CURRENT EVENTS", "USCE");
		Category WCurrent = new Category("World", "CURRENT EVENTS", "WCE");
		Category otherCurrent = new Category("Other", "CURRENT EVENTS", "OCE");
		
		Category trash = new Category("TRASH", "TRASH", "Trash");
		Category sports = new Category("Sports", "TRASH", "Sport");
		Category television = new Category("Television", "TRASH", "TV");
		Category film = new Category("Film", "TRASH", "Film");
		Category trashMusic = new Category("Music", "TRASH", "TMusic");
		Category otherTrash = new Category("Other", "TRASH", "OTrash");
		
		Category myth = new Category("MYTHOLOGY","MYTHOLOGY", "Myth");
		Category grMyth = new Category("Greco-Roman","MYTHOLOGY", "GRMyth");
		Category norseMyth = new Category("Norse","MYTHOLOGY", "NMyth");
		Category hinduMyth = new Category("Hindu","MYTHOLOGY", "HMyth");
		Category worldMyth = new Category("World","MYTHOLOGY", "WMyth");
		Category otherMyth = new Category("Other","MYTHOLOGY", "OMyth");
		
		Category misc = new Category("MISCELLANEOUS", "MISCELLANEOUS", "Misc");
		Category gk = new Category("General Knowledge", "MISCELLANEOUS", "GK");
		Category common = new Category("Common Link", "MISCELLANEOUS", "CLink");
		Category otherMisc = new Category("Other", "MISCELLANEOUS", "Other");
		
		Statistic pp20th = new Statistic(true, "PP20TH");
		Statistic ptuh = new Statistic(true, "P/TUH");
		Statistic thirties = new Statistic("30's");
		Statistic twenties = new Statistic("20's");
		Statistic powers = new Statistic("Powers");
		Statistic tens = new Statistic("Tens");
		Statistic negs = new Statistic("Negs");
		Statistic pn = new Statistic(true, "P/N");
		Statistic gn = new Statistic(true, "G/N");
		Statistic tuh = new Statistic("TUH");
		
		Statistic buh = new Statistic("BHrd", true);
		Statistic bpts = new Statistic("BPts", true);
		Statistic ppb = new Statistic("PPB", true);
		Statistic bbh = new Statistic("BBHrd", true); 
		Statistic bbpts = new Statistic("BBPts", true);
		Statistic ppbb =new Statistic("PPBB", true);
	}
	
	public void createWorkbook(String workbookName)
	{
		createWorkbook = new XSSFWorkbook();
		createSheet("Master", createWorkbook);
		try 
		{
			outputCreated = new FileOutputStream(workbookName + ".xlsx");
			createWorkbook.write(outputCreated);
			outputCreated.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) //Couldn't write workbook info to output
		{
			e.printStackTrace();
		}
		
	}
	
	public void getWorkbook(String fileName)
	{
		try 
		{
			existingFile = new FileInputStream(new File(fileName));
			workbook = new XSSFWorkbook(existingFile);
		} 
		catch (FileNotFoundException e) //Couldn't find file
		{
			e.printStackTrace();
		} 
		catch (IOException e) //Couldn't create workbook
		{
			e.printStackTrace();
		}
	}
	
	public void createSheet(String sheetName, Workbook sheetWorkbook)
	{
		Sheet newSheet = sheetWorkbook.createSheet(sheetName);
	}
	
	public void closeWorkbook(String fileName)
	{
		try 
		{
			existingFile.close();
			output = new FileOutputStream(fileName);
			workbook.write(output);
			output.close();
		} 
		catch (IOException e) //Couldn't close existingFile
		{
			e.printStackTrace();
		}
		
	}
	
	public void masterPlayerPage(String sheetName, int startRow, int startColumn)
	{
		Sheet masterSheet = workbook.getSheet(sheetName);
		Row statsRow = null;
		Row infoRow = null;
		CellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		borderStyle.setBorderTop(CellStyle.BORDER_THIN);
		
		CellStyle hiddenStyle = workbook.createCellStyle();
		DataFormat hiddenFormat = workbook.createDataFormat();
		hiddenStyle.setDataFormat(hiddenFormat.getFormat(";;;"));
		
		if(masterSheet.getRow(startRow) == null)
		{
			statsRow = masterSheet.createRow(startRow);
			infoRow = masterSheet.createRow(0);
			System.out.println("Master Page Row 0 was empty");
		}
		else
		{
			statsRow = masterSheet.getRow(startRow);
			infoRow = masterSheet.getRow(0);
			System.out.println("Master Page Row 0 was not empty");
		}
		
		Cell statsCell = statsRow.createCell(startColumn);
		Cell infoCell = infoRow.createCell(1);
		CellReference cr = new CellReference(startRow+1, startColumn-2);
		infoCell.setCellValue(cr.formatAsString());
		infoCell.setCellStyle(hiddenStyle);
		for(int i=0; i<onStats.size(); i++)
		{
			statsCell = statsRow.createCell(startColumn+i);
			statsCell.setCellValue(onStats.get(i).getStatisticName());
			statsCell.setCellStyle(borderStyle);
		}
	}
	
	public void masterTeams()
	{
		
	}
	
	public void masterPlayerCategories(String sheetName, int startRow, int startColumn)
	{
		Sheet masterSheet = workbook.getSheet(sheetName);
		Row statsRow = null;
		Row infoRow = null;
		CellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		borderStyle.setBorderTop(CellStyle.BORDER_THIN);
		
		CellStyle hiddenStyle = workbook.createCellStyle();
		DataFormat hiddenFormat = workbook.createDataFormat();
		hiddenStyle.setDataFormat(hiddenFormat.getFormat(";;;"));
		
		if(masterSheet.getRow(startRow-1) == null)
		{
			masterSheet.createRow(startRow-1);
		}
		
		if(masterSheet.getRow(startRow) == null)
		{
			statsRow = masterSheet.createRow(startRow);
			infoRow = masterSheet.createRow(0);
			System.out.println("Master Page Row 0 was empty");
		}
		else
		{
			statsRow = masterSheet.getRow(startRow);
			infoRow = masterSheet.getRow(0);
			System.out.println("Master Page Row 0 was not empty");
		}
		
		Cell statsCell = statsRow.createCell(startColumn);
		Cell infoCell = infoRow.createCell(4);
		CellReference cr = new CellReference(startRow+1, startColumn-2);
		infoCell.setCellValue(cr.formatAsString()); //Sets the cell in the top row, 4th column to the starting location of the table
		infoCell.setCellStyle(hiddenStyle);
		for(int i=0; i<onCategoryList.size(); i++)
		{
			if(masterSheet.getRow(startRow+1+i) == null)
			{
				masterSheet.createRow(startRow+1+i);
			}
			statsRow = masterSheet.getRow(startRow+1+i);
			statsCell = statsRow.createCell(startColumn);
			if(onCategoryList.get(i).getCategoryName().equals(onCategoryList.get(i).getCategoryName().toUpperCase()) && !onCategoryList.get(i).getCategoryName().equals("ALL"))
			{
				statsCell = statsRow.createCell(startColumn-1);
			}
			statsCell.setCellValue(onCategoryList.get(i).getCategoryName());
		}
	}
	
	public void resetCategoriesStats()
	{
		onCategoryList = new ArrayList<Category>();
		for(Category cat : categoryList)
		{
			if(cat.isEnabled())
			{
				onCategoryList.add(cat);
			}
		}
		
		onStats = new ArrayList<Statistic>();
		for(Statistic stat : statsCategories)
		{
			if(stat.isEnabled())
			{
				onStats.add(stat);
			}
		};
		
		onBonusStats = new ArrayList<Statistic>();
		for(Statistic stat : bonusStats)
		{
			if(stat.isEnabled())
			{
				onBonusStats.add(stat);
			}
		}
	}
	
	public void teamPage(String sheetName, Team team, int row, int column)
	{
		Sheet teamSheet = workbook.getSheet(sheetName);
		String teamName = team.getTeamName();
		Row startRow;
		
		if(teamSheet.getRow(row) == null)
		{
			startRow = teamSheet.createRow(row);
			System.out.println("Row was null");
		}
		startRow = teamSheet.getRow(row);
		if(startRow.getCell(column) == null)
		{
			System.out.println("This cell is null");
			startRow.createCell(column, XSSFCell.CELL_TYPE_BLANK);
		}
		if(startRow.getCell(column).getCellType() == XSSFCell.CELL_TYPE_BLANK)
		{
			System.out.println("Ready to write!");
			createTable(sheetName, row, column, onCategoryList.size(), onStats.size()+onBonusStats.size());
			addCategories(sheetName, row, column-1);
			addStatistics(sheetName, row-1, column, true, onCategoryList.size());
			formatStatistics(sheetName, row, column);
			teamSheet.getRow(row-1).createCell(column-1).setCellValue(teamName);
		}
		else if(startRow.getCell(column) != null)
		{
			System.out.println("There is something here");
		}
	}

	public void playerPage(String sheetName, String playerName, int row, int column)
	{		
		String tableSheet = sheetName;
		Sheet teamSheet = workbook.getSheet(sheetName);
		Row startRow;
		
		if(teamSheet.getRow(row) == null)
		{
			startRow = teamSheet.createRow(row);
			System.out.println("Row was null");
		}
		startRow = teamSheet.getRow(row);
		if(startRow.getCell(column) == null)
		{
			System.out.println("This cell is null");
			startRow.createCell(column, XSSFCell.CELL_TYPE_BLANK);
		}
		if(startRow.getCell(column).getCellType() == XSSFCell.CELL_TYPE_BLANK)
		{
			System.out.println("Ready to write!");
			createTable(tableSheet, row, column, onCategoryList.size(), onStats.size());
			addCategories(tableSheet, row, column-1);
			addStatistics(tableSheet, row-1, column, false, onCategoryList.size());
			formatStatistics(tableSheet, row, column);
			teamSheet.getRow(row-1).createCell(column-1).setCellValue(playerName);
			CellReference cr = new CellReference(row-1, column-2);
			addMasterPlayer("Master", sheetName, cr.formatAsString());
			addMasterPlayerCategory("Master", sheetName, cr.formatAsString());
		}
		else if(startRow.getCell(column) != null)
		{
			System.out.println("There is something here");
		}
	}
	
	public void addMasterPlayer(String sheetName, String playerSheetName, String playerID)
	{
		Sheet masterSheet = workbook.getSheet(sheetName);
		Sheet playerSheet = workbook.getSheet(playerSheetName);
		String playerFullID = playerSheetName + "!" + playerID;
		String tableID = masterSheet.getRow(0).getCell(1).getStringCellValue(); //Finds where the table starts
		CellReference crTable = new CellReference(tableID);
		int startColumn = crTable.getCol();
		int startRow = 0;
		boolean empty = false;
		CellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		borderStyle.setBorderTop(CellStyle.BORDER_THIN);
		
		CellStyle hiddenStyle = workbook.createCellStyle();
		DataFormat hiddenFormat = workbook.createDataFormat();
		hiddenStyle.setDataFormat(hiddenFormat.getFormat(";;;"));
		
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		
		for(int i=0; empty == false; i++)
		{
			if(masterSheet.getRow(crTable.getRow()+i) == null)
			{
				System.out.println("Found an empty cell in Master Player Page");
				startRow = crTable.getRow()+i;
				empty = true;
			}
			else if(masterSheet.getRow(crTable.getRow()+i).getCell(startColumn) == null)
			{
				System.out.println("Found an empty cell in Master Player Page");
				startRow = crTable.getRow()+i;
				empty = true;
			}
			else if(masterSheet.getRow(crTable.getRow()+i).getCell(startColumn).getCellType() == XSSFCell.CELL_TYPE_BLANK)
			{
				System.out.println("Found an empty cell in Master Player Page");
				startRow = crTable.getRow()+i;
				empty = true;
			}
			else if(masterSheet.getRow(crTable.getRow()+i).getCell(startColumn).getCellType() != XSSFCell.CELL_TYPE_BLANK)
			{
				System.out.println("Master Player Page cell was full");
			}
		}
		CellReference lastPlayer = new CellReference(startRow, crTable.getCol()); 
		masterSheet.getRow(0).createCell(2).setCellValue(lastPlayer.formatAsString());
		masterSheet.getRow(0).getCell(2).setCellStyle(hiddenStyle);
		
		Row playerRow = null; //Creates player's row in master sheet if it doesn't exist already
		if(masterSheet.getRow(startRow) == null)
		{
			playerRow = masterSheet.createRow(startRow);
		}
		else
		{
			playerRow = masterSheet.getRow(startRow);
		}
		
		Cell playerIDCell = playerRow.createCell(startColumn);
		Cell playerNameCell = playerRow.createCell(startColumn+1);
		Cell playerStatsCell = playerRow.createCell(startColumn+2);
		CellReference cr = new CellReference(playerID);
		int playerRowNum = cr.getRow();
		int playerColumnNum = cr.getCol();
		CellReference crName = new CellReference(playerRowNum, playerColumnNum+1);
		CellReference crStats = new CellReference(playerRowNum+1, playerColumnNum+2);
		
		playerIDCell.setCellValue(playerFullID); //
		playerIDCell.setCellStyle(hiddenStyle);
		playerNameCell.setCellFormula(playerSheetName + "!" + crName.formatAsString());
		
		for(int i=0; i<onStats.size(); i++)
		{
			crStats = new CellReference(playerRowNum+1, playerColumnNum+2+i);
			playerStatsCell = playerRow.createCell(startColumn+2+i);
			playerStatsCell.setCellFormula(playerSheetName + "!" + crStats.formatAsString());
			if(onStats.get(i).isFractionEnabled()) //If category has decimal, sets category to decimal style
			{
				playerStatsCell.setCellStyle(decimalStyle);
			}
			else
			{
				playerStatsCell.setCellStyle(borderStyle);
			}
		}
	}
	
	public void addMasterPlayerCategory(String sheetName, String playerSheetName, String playerID)
	{
		Sheet masterSheet = workbook.getSheet(sheetName);
		Sheet playerSheet = workbook.getSheet(playerSheetName);
		String playerFullID = playerSheetName + "!" + playerID;
		String tableID = masterSheet.getRow(0).getCell(4).getStringCellValue(); //Finds where the table starts
		CellReference crTable = new CellReference(tableID);
		int startColumn = crTable.getCol()+3;
		int startRow = crTable.getRow()-1;
		boolean empty = false;
		
		CellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		borderStyle.setBorderTop(CellStyle.BORDER_THIN);
		
		CellStyle hiddenStyle = workbook.createCellStyle();
		DataFormat hiddenFormat = workbook.createDataFormat();
		hiddenStyle.setDataFormat(hiddenFormat.getFormat(";;;"));
		
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		
		for(int i=0; empty == false; i++)
		{
			if(masterSheet.getRow(startRow).getCell(startColumn+i) == null)
			{
				System.out.println("Found a null cell in Master Player Page2");
				startColumn = startColumn+i;
				empty = true;
			}
			else if(masterSheet.getRow(startRow).getCell(startColumn+i).getCellType() == XSSFCell.CELL_TYPE_BLANK)
			{
				System.out.println("Found an empty cell in Master Player Page2");
				startColumn = startColumn+i;
				empty = true;
			}
			else if(masterSheet.getRow(startRow).getCell(startColumn+i).getCellType() != XSSFCell.CELL_TYPE_BLANK)
			{
				System.out.println("Master Player Page cell was full2");
			}
		}
		CellReference lastPlayer = new CellReference(startRow, startColumn); 
		masterSheet.getRow(0).createCell(5).setCellValue(lastPlayer.formatAsString());
		masterSheet.getRow(0).getCell(5).setCellStyle(hiddenStyle);
		
		Row playerRow = masterSheet.getRow(startRow);
		Row playerIDRow = masterSheet.getRow(startRow-1);
		
		Cell playerIDCell = playerIDRow.createCell(startColumn);
		Cell playerNameCell = playerRow.createCell(startColumn);
		CellReference cr = new CellReference(playerID);
		int playerRowNum = cr.getRow();
		int playerColumnNum = cr.getCol();
		CellReference crName = new CellReference(playerRowNum, playerColumnNum+1);
		CellReference crCats = new CellReference(playerRowNum+1, playerColumnNum+2);
		
		playerIDCell.setCellValue(playerFullID); //
		playerIDCell.setCellStyle(hiddenStyle);
		playerNameCell.setCellFormula(playerSheetName + "!" + crName.formatAsString());
		
		for(int i=0; i<onCategoryList.size(); i++)
		{
			crCats = new CellReference(playerRowNum+1+i, playerColumnNum+2);
			Row playerStatsRow = masterSheet.getRow(startRow+1+i);
			Cell playerStatsCell = playerStatsRow.createCell(startColumn);
			playerStatsCell.setCellFormula(playerSheetName + "!" + crCats.formatAsString());
			playerStatsCell.setCellStyle(decimalStyle);
		}
	}
	
	public void sortMasterPlayers(String sheetName)
	{
		Sheet masterSheet = workbook.getSheet("Master");
		String firstID = masterSheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println("FirstID: " + firstID);
		String lastID = masterSheet.getRow(0).getCell(2).getStringCellValue();
		System.out.println("LastID: " + lastID);
		ArrayList<Double> statNumbers = new ArrayList<Double>();
		HashMap<String, Double> statMap = new HashMap<String, Double>();
		ArrayList<String> idList = new ArrayList<String>();
		ArrayList<Double> idNumbers = new ArrayList<Double>();
		ArrayList<String> idListFinal = new ArrayList<String>();
		
		CellReference crFirst = new CellReference(firstID);
		CellReference crLast = new CellReference(lastID);
		
		for(int i=0; i<crLast.getRow()-crFirst.getRow()+1; i++)
		{
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			Cell statCell = masterSheet.getRow(crFirst.getRow()+i).getCell(crFirst.getCol()+2);
			Cell idCell = masterSheet.getRow(crFirst.getRow()+i).getCell(crFirst.getCol());
			double statValue = evaluator.evaluate(statCell).getNumberValue();
			String playerID = idCell.getStringCellValue();
			System.out.println("Stat cell numeric value: " + statValue);
			statNumbers.add(statValue);
			statMap.put(playerID, statValue);
		}
		Collections.sort(statNumbers);
		Collections.reverse(statNumbers);
		for(String keys : statMap.keySet())
		{
			idList.add(keys);
		}
		for(Double numbers : statMap.values())
		{
			idNumbers.add(numbers);
		}
		
		for(String keys : idList)
		{
			System.out.println("IDList: " + keys);
		}
		for(Double numbers : idNumbers)
		{
			System.out.println("IDNumbers: " + numbers);
		}
		System.out.println("Stat numbers size: " + statNumbers.size());
		System.out.println("Id numbers size: " + idNumbers.size());
		
		for(int i=0; i<statNumbers.size(); i++)
		{
			for(int j=0; j<idNumbers.size(); j++)
			{
				System.out.println(idNumbers.get(j));
				System.out.println(statNumbers.get(i));
				if((idNumbers.get(j))-(statNumbers.get(i)) <= .00001 && (idNumbers.get(j))-(statNumbers.get(i)) >= -.00001)
				{
					System.out.println("Found match: " + idList.get(j));
					idListFinal.add(idList.get(j));
					idNumbers.remove(j);
					idList.remove(j);
				}
				else
				{
					System.out.println("Not a match");
				}
			}
		}
		
		for(String ids : idListFinal)
		{
			System.out.println("FINAL ID LIST: " + ids);
		}
		
		for(int i=0; i<crLast.getRow()-crFirst.getRow()+1; i++)
		{
			masterSheet.getRow(crFirst.getRow()+i).createCell(crFirst.getCol());
		}
		
		for(int i=0; i<idListFinal.size(); i++)
		{
			String id = idListFinal.get(i);
			int exclamationIndex = id.indexOf("!");
			addMasterPlayer("Master", id.substring(0, exclamationIndex), id.substring(exclamationIndex+1, id.length()));
		}
	}
	
	public void sortMasterPlayerCategory(String sheetName)
	{
		Sheet masterSheet = workbook.getSheet("Master");
		String firstID = masterSheet.getRow(0).getCell(4).getStringCellValue();
		System.out.println("FirstID2: " + firstID);
		String lastID = masterSheet.getRow(0).getCell(5).getStringCellValue();
		System.out.println("LastID2: " + lastID);
		ArrayList<Double> statNumbers = new ArrayList<Double>();
		HashMap<String, Double> statMap = new HashMap<String, Double>();
		ArrayList<String> idList = new ArrayList<String>();
		ArrayList<Double> idNumbers = new ArrayList<Double>();
		ArrayList<String> idListFinal = new ArrayList<String>();
		
		CellReference crFirst = new CellReference(firstID);
		crFirst = new CellReference(crFirst.getRow()-1, crFirst.getCol()+3);
		CellReference crLast = new CellReference(lastID);
		
		for(int i=0; i<crLast.getCol()-crFirst.getCol()+1; i++)
		{
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			Cell statCell = masterSheet.getRow(crFirst.getRow()+1).getCell(crFirst.getCol()+i);
			Cell idCell = masterSheet.getRow(crFirst.getRow()-1).getCell(crFirst.getCol()+i);
			double statValue = evaluator.evaluate(statCell).getNumberValue();
			String playerID = idCell.getStringCellValue();
			System.out.println("Stat cell numeric value2: " + statValue);
			statNumbers.add(statValue);
			statMap.put(playerID, statValue);
		}
		Collections.sort(statNumbers);
		Collections.reverse(statNumbers);
		for(String keys : statMap.keySet())
		{
			idList.add(keys);
		}
		for(Double numbers : statMap.values())
		{
			idNumbers.add(numbers);
		}
		
		for(String keys : idList)
		{
			System.out.println("IDList: " + keys);
		}
		for(Double numbers : idNumbers)
		{
			System.out.println("IDNumbers: " + numbers);
		}
		System.out.println("Stat numbers size: " + statNumbers.size());
		System.out.println("Id numbers size: " + idNumbers.size());
		
		for(int i=0; i<statNumbers.size(); i++)
		{
			for(int j=0; j<idNumbers.size(); j++)
			{
				System.out.println(idNumbers.get(j));
				System.out.println(statNumbers.get(i));
				if((idNumbers.get(j))-(statNumbers.get(i)) <= .00001 && (idNumbers.get(j))-(statNumbers.get(i)) >= -.00001)
				{
					System.out.println("Found match: " + idList.get(j));
					idListFinal.add(idList.get(j));
					idNumbers.remove(j);
					idList.remove(j);
				}
				else
				{
					System.out.println("Not a match");
				}
			}
		}
		
		for(String ids : idListFinal)
		{
			System.out.println("FINAL ID LIST: " + ids);
		}
		
		for(int i=0; i<crLast.getCol()-crFirst.getCol()+1; i++)
		{
			System.out.println("Method should iterate " + (crLast.getRow()-crFirst.getRow()+1) + " times and i= " + i);
			masterSheet.getRow(crFirst.getRow()).createCell(crFirst.getCol()+i);
		}
		
		for(int i=0; i<idListFinal.size(); i++)
		{
			String id = idListFinal.get(i);
			int exclamationIndex = id.indexOf("!");
			addMasterPlayerCategory("Master", id.substring(0, exclamationIndex), id.substring(exclamationIndex+1, id.length()));
		}
	}
	
	public void createTable(String sheetName, int rowStart, int columnStart, int rowsTotal, int columnsTotal)
	{
		Sheet tableSheet = workbook.getSheet(sheetName);
		CellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		borderStyle.setBorderTop(CellStyle.BORDER_THIN);
		Cell tableCell; 
		for(int i=0; i<rowsTotal; i++)
		{
			if(tableSheet.getRow(rowStart+i) == null)
			{
				tableSheet.createRow(rowStart+i);
			}
			Row tableRow = tableSheet.getRow(rowStart+i);
			for(int j=0; j<columnsTotal; j++)
			{
				tableCell = tableRow.createCell(columnStart+j);
				tableCell.setCellStyle(borderStyle);
			}
		}
	}
	
	public void addCategories(String sheetName, int rowStart, int columnStart)
	{
		Sheet categorySheet = workbook.getSheet(sheetName);
		
		Cell categoryCell;
		
		for(int i=0; i< onCategoryList.size(); i++)
		{
			categoryCell = categorySheet.getRow(rowStart+i).createCell(columnStart);
			
			//If category is a main category, cell column is moved back one
			if(onCategoryList.get(i).getCategoryName().toUpperCase().equals(onCategoryList.get(i).getCategoryName()))
			{
				categoryCell = categorySheet.getRow(rowStart+i).createCell(columnStart-1);
			}

			String categoryName = onCategoryList.get(i).getCategoryName();
			categoryCell.setCellValue(categoryName);
		}
	}
	
	public void addStatistics(String sheetName, int rowStart, int columnStart, boolean teamPage, int rowsTotal)
	{
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		statsLocations = new ArrayList<String>();
		Sheet statsSheet = workbook.getSheet(sheetName);
		Cell statsCell;
		CellReference cr;
		if(statsSheet.getRow(rowStart) == null)
		{
			statsSheet.createRow(rowStart);
		}
		Row statsRow = statsSheet.getRow(rowStart);
		
		for(int i=0;i<onStats.size();i++)
		{
			statsCell = statsRow.createCell(columnStart+i);
			statsCell.setCellValue(onStats.get(i).getStatisticName());
			cr = new CellReference(statsCell.getRowIndex()+1, statsCell.getColumnIndex());
			statsLocations.add(onStats.get(i).getStatisticName() + cr.formatAsString());
		}
		
		if(teamPage)
		{
			for(int i=0;i<onBonusStats.size();i++)
			{
				statsCell = statsRow.createCell(columnStart+i+onStats.size());
				statsCell.setCellValue(onBonusStats.get(i).getStatisticName());
				cr = new CellReference(statsCell.getRowIndex()+1, statsCell.getColumnIndex());
				statsLocations.add(onBonusStats.get(i).getStatisticName() + cr.formatAsString());
				if(onBonusStats.get(i).getStatisticName().equals("BBHrd"))
				{
					for(int j=1; j<rowsTotal; j++)
					{
						Row bbhRow = statsSheet.getRow(rowStart+j);
						statsCell = bbhRow.getCell(columnStart+i+onStats.size());
						statsCell.setCellStyle(decimalStyle);
					}
				}
			}
		}
	}
	
	public void formatStatistics(String sheetName, int rowStart, int columnStart)
	{
		Sheet statsSheet = workbook.getSheet(sheetName);
		String pp20th = "0";
		String ptuh = "0";
		String tuh = "0";
		String thirties = "0";
		String twenties = "0";
		String powers = "0";
		String tens = "0";
		String negs = "0";
		String pn = "0";
		String gn = "0";
		String bh = "0";
		String bpts = "0";
		String ppb = "0";
		String bbh = "0";
		String bbpts = "0";
		String ppbb = "0";
		
		for(int i=0; i<statsLocations.size(); i++) //statsLocations is composed of stat name + CellReferences formatted as strings 
		{
			if(statsLocations.get(i).contains("P/TUH"))
			{
				ptuh = statsLocations.get(i);
				int dollarIndex = ptuh.indexOf("$");
				ptuh = ptuh.substring(dollarIndex, ptuh.length()); //Cuts out stat name, gets just the cell reference
				System.out.println(ptuh);
			}
			else if(statsLocations.get(i).contains("TUH") && statsLocations.get(i).contains("P/TUH") == false)
			{
				tuh = statsLocations.get(i);
				int dollarIndex = tuh.indexOf("$");
				tuh = tuh.substring(dollarIndex, tuh.length());
				System.out.println(tuh);
			}
			else if(statsLocations.get(i).contains("30's"))
			{
				thirties = statsLocations.get(i);
				int dollarIndex = thirties.indexOf("$");
				thirties = thirties.substring(dollarIndex, thirties.length());
				System.out.println(thirties);
			}
			else if(statsLocations.get(i).contains("20's"))
			{
				twenties = statsLocations.get(i);
				int dollarIndex = twenties.indexOf("$");
				twenties = twenties.substring(dollarIndex, twenties.length());
				System.out.println(twenties);
			}
			else if(statsLocations.get(i).contains("Powers"))
			{
				powers = statsLocations.get(i);
				int dollarIndex = powers.indexOf("$");
				powers = powers.substring(dollarIndex, powers.length());
				System.out.println(powers);
			}
			else if(statsLocations.get(i).contains("Tens"))
			{
				tens = statsLocations.get(i);
				int dollarIndex = tens.indexOf("$");
				tens = tens.substring(dollarIndex, tens.length());
				System.out.println(tens);
			}
			else if(statsLocations.get(i).contains("Negs"))
			{
				negs = statsLocations.get(i);
				int dollarIndex = negs.indexOf("$");
				negs = negs.substring(dollarIndex, negs.length());
				System.out.println(negs);
			}
			else if(statsLocations.get(i).contains("PP20TH"))
			{
				pp20th = statsLocations.get(i);
				int dollarIndex = pp20th.indexOf("$");
				pp20th = pp20th.substring(dollarIndex, pp20th.length());
				System.out.println(pp20th);
			}
			else if(statsLocations.get(i).contains("P/N"))
			{
				pn = statsLocations.get(i);
				int dollarIndex = pn.indexOf("$");
				pn = pn.substring(dollarIndex, pn.length());
				System.out.println(pn);
			}
			else if(statsLocations.get(i).contains("G/N"))
			{
				gn = statsLocations.get(i);
				int dollarIndex = gn.indexOf("$");
				gn = gn.substring(dollarIndex, gn.length());
				System.out.println(gn);
			}
			else if(statsLocations.get(i).contains("BHrd") && statsLocations.get(i).contains("BBHrd") == false)
			{
				bh = statsLocations.get(i);
				int dollarIndex = bh.indexOf("$");
				bh = bh.substring(dollarIndex, bh.length());
				System.out.println("BH at: " + bh);
			}
			else if(statsLocations.get(i).contains("BPts") && statsLocations.get(i).contains("BBPts") == false)
			{
				bpts = statsLocations.get(i);
				int dollarIndex = bpts.indexOf("$");
				bpts = bpts.substring(dollarIndex, bpts.length());
				System.out.println("BPts at: " + bpts);
			}
			else if(statsLocations.get(i).contains("PPB") && statsLocations.get(i).contains("PPBB") == false)
			{
				ppb = statsLocations.get(i);
				int dollarIndex = ppb.indexOf("$");
				ppb = ppb.substring(dollarIndex, ppb.length());
				System.out.println("PPB at: " + ppb);
			}
			else if(statsLocations.get(i).contains("BBHrd"))
			{
				bbh = statsLocations.get(i);
				int dollarIndex = bbh.indexOf("$");
				bbh = bbh.substring(dollarIndex, bbh.length());
				System.out.println("BBH at: " + bbh);
			}
			else if(statsLocations.get(i).contains("BBPts"))
			{
				bbpts = statsLocations.get(i);
				int dollarIndex = bbpts.indexOf("$");
				bbpts = bbpts.substring(dollarIndex, bbpts.length());
				System.out.println("BBPts at: " + bbpts);
			}
			else if(statsLocations.get(i).contains("PPBB"))
			{
				ppbb = statsLocations.get(i);
				int dollarIndex = ppbb.indexOf("$");
				ppbb = ppbb.substring(dollarIndex, ppbb.length());
				System.out.println("PPBB at: " + ppbb);
			}
		}
		
		findMainCategory(onCategoryList);
		if(!ptuh.equals("0"))
			ptuhFormula(sheetName, ptuh, tuh, thirties, twenties, powers, tens, negs);
		if(!pp20th.equals("0"))
			pp20thFormula(sheetName, pp20th, tuh, thirties, twenties, powers, tens, negs);
		if(!pn.equals("0"))
			pnFormula(sheetName, pn, thirties, twenties, powers, negs);
		if(!gn.equals("0"))
			gnFormula(sheetName, gn, thirties, twenties, powers, tens, negs);
		if(!ppb.equals("0"))
			ppbFormula(sheetName, ppb, bh, bpts);
		if(!ppbb.equals("0"))
			ppbbFormula(sheetName, ppbb, bbh, bbpts);
		
		if(!thirties.equals("0"))
			categoryFormula(sheetName, thirties);
		if(!twenties.equals("0"))
			categoryFormula(sheetName, twenties);
		if(!powers.equals("0"))
			categoryFormula(sheetName, powers);
		if(!tens.equals("0"))
			categoryFormula(sheetName, tens);
		if(!negs.equals("0"))
			categoryFormula(sheetName, negs);
		if(!tuh.equals("0"))
			categoryFormula(sheetName, tuh);
		if(!bh.equals("0"))
			categoryFormula(sheetName, bh);
		if(!bpts.equals("0"))
			categoryFormula(sheetName, bpts);
		if(!bbh.equals("0"))
			categoryFormula(sheetName, bbh);
		if(!bbpts.equals("0"))
			categoryFormula(sheetName, bbpts);
	}
	
	public String updateReference(String sheetName, String cellRef)
	{
		Sheet newSheet = workbook.getSheet(sheetName);
		if(!cellRef.equals("0"))
		{
			CellReference cr = new CellReference(cellRef);
			cr = new CellReference(cr.getRow()+1, cr.getCol());
			return cr.formatAsString();	
		}
		else
		{
			return "0";
		}
	}
	
	public void ptuhFormula(String sheetName, String ptuh, String tuh, String thirties, String twenties, String powers, String tens, String negs)
	{
		Sheet statsSheet = workbook.getSheet(sheetName);
		String ptuh1 = ptuh;
		String tuh1 = tuh;
		String thirties1 = thirties;
		String twenties1 = twenties;
		String powers1 = powers;
		String tens1 = tens;
		String negs1 = negs;
		
		CellReference cr = new CellReference(ptuh1);
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		for(int j=0;j<onCategoryList.size();j++)
		{
			Cell ptuhCell = statsSheet.getRow(cr.getRow()+j).getCell(cr.getCol());
			String equation = "";
			equation = ("IF(" + tuh1 + "=0," + "\"\"," + "((" + thirties1 + "*30 +" + twenties1 + "*20 +" + powers1 + "*15 +" + tens1 + "*10 +" + negs1 + "*-5)" + "/" + tuh1 + "))");
			ptuhCell.setCellFormula(equation);
			ptuhCell.setCellStyle(decimalStyle);
			tuh1 = updateReference(sheetName, tuh1);
			thirties1 = updateReference(sheetName, thirties1);
			twenties1 = updateReference(sheetName, twenties1);
			powers1 = updateReference(sheetName, powers1);
			tens1 = updateReference(sheetName, tens1);
			negs1 = updateReference(sheetName, negs1);
		}
	}
	
	public void pp20thFormula(String sheetName, String pp20th, String tuh, String thirties, String twenties, String powers, String tens, String negs)
	{
		Sheet statsSheet = workbook.getSheet(sheetName);
		String pp20th1 = pp20th;
		String tuh1 = tuh;
		String thirties1 = thirties;
		String twenties1 = twenties;
		String powers1 = powers;
		String tens1 = tens;
		String negs1 = negs;
		
		CellReference cr = new CellReference(pp20th1);
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		for(int j=0;j<onCategoryList.size();j++)
		{
			Cell pp20thCell = statsSheet.getRow(cr.getRow()+j).getCell(cr.getCol());
			String equation = "";
			equation = ("IF("+ tuh1 + "=0," + "\"\"," + "20*((" + thirties1 + "*30 +" + twenties1 + "*20 +" + powers1 + "*15 +" + tens1 + "*10 +" + negs1 + "*-5)" + "/" + tuh1 + "))");
			pp20thCell.setCellFormula(equation);
			pp20thCell.setCellStyle(decimalStyle);
			tuh1 = updateReference(sheetName, tuh1);
			thirties1 = updateReference(sheetName, thirties1);
			twenties1 = updateReference(sheetName, twenties1);
			powers1 = updateReference(sheetName, powers1);
			tens1 = updateReference(sheetName, tens1);
			negs1 = updateReference(sheetName, negs1);
		}
	}
	
	public void pnFormula(String sheetName, String pn, String thirties, String twenties, String powers, String negs)
	{
		Sheet statsSheet = workbook.getSheet(sheetName);
		String pn1 = pn;
		String thirties1 = thirties;
		String twenties1 = twenties;
		String powers1 = powers;
		String negs1 = negs;
		
		CellReference cr = new CellReference(pn1);
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		for(int j=0;j<onCategoryList.size();j++)
		{
			Cell pnCell = statsSheet.getRow(cr.getRow()+j).getCell(cr.getCol());
			String equation = "";
			equation = ("IF(AND("+ thirties1 + "=0," + twenties1 + "=0," + negs1 + "=0," + powers1 + "=0)," + "\"\"," + "IF(AND(" + negs1 + 
					"=0,OR(" + thirties1 + "<>0," + twenties1 + "<>0," + powers1 + "<>0))," + "\"inf\"," + "((" + thirties1 + "+" + twenties1 + "+" + powers1 + ")" + "/" + negs1 + ")))");
			pnCell.setCellFormula(equation);
			pnCell.setCellStyle(decimalStyle);
			thirties1 = updateReference(sheetName, thirties1);
			twenties1 = updateReference(sheetName, twenties1);
			powers1 = updateReference(sheetName, powers1);
			negs1 = updateReference(sheetName, negs1);
		}
	}
	
	public void gnFormula(String sheetName, String gn, String thirties, String twenties, String powers, String tens, String negs)
	{
		Sheet statsSheet = workbook.getSheet(sheetName);
		String gn1 = gn;
		String thirties1 = thirties;
		String twenties1 = twenties;
		String powers1 = powers;
		String tens1 = tens;
		String negs1 = negs;
		
		CellReference cr = new CellReference(gn1);
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		for(int j=0;j<onCategoryList.size();j++)
		{
			Cell gnCell = statsSheet.getRow(cr.getRow()+j).getCell(cr.getCol());
			String equation = "";
			equation = ("IF(AND("+ thirties1 + "=0," + twenties1 + "=0," + negs1 + "=0," + powers1 + "=0," + tens1 + "=0)," + "\"\"," + "IF(AND(" + negs1 + 
			"=0,OR(" + thirties1 + "<>0," + twenties1 + "<>0," + powers1 + "<>0," + tens1 + "<>0))," + "\"inf\"," + "((" + thirties1 + "+" + twenties1 + "+" + powers1 + "+" + tens1 + ")" + "/" + negs1 + ")))");
			gnCell.setCellFormula(equation);
			gnCell.setCellStyle(decimalStyle);
			thirties1 = updateReference(sheetName, thirties1);
			twenties1 = updateReference(sheetName, twenties1);
			powers1 = updateReference(sheetName, powers1);
			tens1 = updateReference(sheetName, tens1);
			negs1 = updateReference(sheetName, negs1);
		}
	}
	
	public void ppbFormula(String sheetName, String ppb, String bh, String bpts)
	{
		Sheet statsSheet = workbook.getSheet(sheetName);
		String ppb1 = ppb;
		String bh1 = bh;
		String bpts1 = bpts;
		
		CellReference cr = new CellReference(ppb1);
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		for(int j=0;j<onCategoryList.size();j++)
		{
			Cell ppbCell = statsSheet.getRow(cr.getRow()+j).getCell(cr.getCol());
			String equation = "";
			equation = ("IF(" + bh1 + "=0," + "\"\"," + "(" + bpts1 + "/" + bh1 + "))");
			ppbCell.setCellFormula(equation);
			ppbCell.setCellStyle(decimalStyle);
			bh1 = updateReference(sheetName, bh1);
			bpts1 = updateReference(sheetName, bpts1);
		}
	}
	
	public void ppbbFormula(String sheetName, String ppbb, String bbh, String bbpts)
	{
		Sheet statsSheet = workbook.getSheet(sheetName);
		String ppbb1 = ppbb;
		String bbh1 = bbh;
		String bbpts1 = bbpts;
		
		System.out.println("PPBB CELL REFERENCE IS: " + ppbb1);
		
		CellReference cr = new CellReference(ppbb1);
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		decimalStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		for(int j=0;j<onCategoryList.size();j++)
		{
			Cell ppbbCell = statsSheet.getRow(cr.getRow()+j).getCell(cr.getCol());
			String equation = "";
			equation = ("IF(" + bbh1 + "=0," + "\"\"," + "(" + bbpts1 + "/" + bbh1 + "))");
			ppbbCell.setCellFormula(equation);
			ppbbCell.setCellStyle(decimalStyle);
			bbh1 = updateReference(sheetName, bbh1);
			bbpts1 = updateReference(sheetName, bbpts1);
		}
	}
	
	public void readScore(String sheetName, int startRow, int startColumn, boolean delete)
	{
		Sheet scoreSheet = workbook.getSheet(sheetName);
		boolean endReached = false;
		int tCatColumn = 0, bCatColumn = 0;
		
		if(scoreSheet.getRow(startRow) == null)
		{
			System.out.println("Error, scoresheet row is null");
		}
		
		else
		{
			for(int i=0; endReached == false; i++) //Finds where tossup and bonus category columns are
			{
				if(scoreSheet.getRow(startRow) == null)
				{
					System.out.println("Null row");
				}
				else if(scoreSheet.getRow(startRow).getCell(startColumn+i) == null)
				{
					System.out.println("Null cell");
				}
				else if(scoreSheet.getRow(startRow).getCell(startColumn+i).getCellType() == XSSFCell.CELL_TYPE_BLANK)
				{
					System.out.println("Blank cell");
				}
				else if(scoreSheet.getRow(startRow).getCell(startColumn+i).getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
					Cell categoryCell = scoreSheet.getRow(startRow).getCell(startColumn+i);
					if(categoryCell.getStringCellValue().equals("TCats"))
					{
						System.out.println(categoryCell.getStringCellValue());
						tCatColumn = categoryCell.getColumnIndex();
					}
					else if(categoryCell.getStringCellValue().equals("BCats"))
					{
						System.out.println(categoryCell.getStringCellValue());
						bCatColumn = categoryCell.getColumnIndex();
					}
					else if(categoryCell.getStringCellValue().equals("end"))
					{
						endReached = true;
						System.out.println("REACHED THE END OF THE SHEET - Found scoresheet categories");
					}
				}
			}
			
			endReached = false;
			
			for(int i=0; endReached == false; i++)
			{
				if(scoreSheet.getRow(startRow) == null)
				{
					System.out.println("Null row");
				}
				else if(scoreSheet.getRow(startRow).getCell(startColumn+i) == null)
				{
					System.out.println("Null cell");
				}
				else if(scoreSheet.getRow(startRow).getCell(startColumn+i).getCellType() == XSSFCell.CELL_TYPE_BLANK)
				{
					System.out.println("Blank cell");
				}
				else if(scoreSheet.getRow(startRow).getCell(startColumn+i).getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
					Cell playerCell = scoreSheet.getRow(startRow).getCell(startColumn+i);
					System.out.println(playerCell.getStringCellValue());
					if(playerCell.getStringCellValue().contains("$"))
					{
						readColumn(sheetName, playerCell.getRowIndex(), playerCell.getColumnIndex(), tCatColumn, bCatColumn, delete); 
					}
					else if(playerCell.getStringCellValue().equals("end"))
					{
						endReached = true;
						System.out.println("REACHED THE END OF THE SHEET");
					}
				}
			}
		}
	}
	
	public void readColumn(String sheetName, int startRow, int startColumn, int tCatColumn, int bCatColumn, boolean delete)
	{
		Sheet columnSheet = workbook.getSheet(sheetName);
		String playerCode = columnSheet.getRow(startRow).getCell(startColumn).getStringCellValue(); //Gets the value above player name (TeamName$A$4)
		String playerReference = "";
		String teamReference = "";
		String teamSheetName = "";
		
		int dollarStart = playerCode.indexOf("$"); //Splits string into player's beginning address and player sheet
		String playerSheetName = playerCode.substring(1, dollarStart);
		String playerCodes = playerCode.substring(dollarStart, playerCode.length());
		
		if(playerCodes.contains("!"))
		{
			int exclamationStart = playerCodes.indexOf("!");
			playerReference = playerCodes.substring(0, exclamationStart);
			String teamString = playerCodes.substring(exclamationStart, playerCodes.length());
			int dollar2Start = teamString.indexOf("$");
			teamSheetName = teamString.substring(1, dollar2Start);
			teamReference = teamString.substring(dollar2Start, teamString.length());
		}
		else
		{
			playerReference = playerCodes;
		}
		System.out.println("Player Sheet: " + playerSheetName);
		System.out.println("Player Ref: " + playerReference);
		System.out.println("Team Sheet: " + teamSheetName);
		System.out.println("Team Ref: " + teamReference);
		
		int bonusStatsSize = 0;
		boolean bonusColumn = false;
		boolean bouncebackColumn = false;
		boolean bbHeardColumn = false;
		String playerFirstID = playerCode.substring(0,1);
		if(playerFirstID.equals("P")) //Player ID
		{
			bonusStatsSize = 0;
			bonusColumn = false;
			bouncebackColumn = false;
			bbHeardColumn = false;
		}
		else if(playerFirstID.equals("B")) //Bonus ID
		{
			bonusStatsSize = onBonusStats.size();
			bonusColumn = true;
			bouncebackColumn = false;
			bbHeardColumn = false;
		}
		else if(playerFirstID.equals("C")) //Bounceback ID
		{
			bonusStatsSize = onBonusStats.size();
			bonusColumn = true;
			bouncebackColumn = true;
			bbHeardColumn = false;
		}
		else if(playerFirstID.equals("D")) //Bouncebacks Heard ID
		{
			bonusStatsSize = onBonusStats.size();
			bonusColumn = true;
			bouncebackColumn = true;
			bbHeardColumn = true;
		}
		System.out.println(playerSheetName);
		System.out.println(playerReference);
		
		Sheet playerSheet = workbook.getSheet(playerSheetName); //Finds player's sheet and starts at player's beginning cell
		CellReference playerCellRef = new CellReference(playerReference); //Player starting reference
		readColumn2(sheetName, playerSheetName, bonusStatsSize, startRow, startColumn, tCatColumn, bCatColumn, delete, playerCellRef, bonusColumn, bouncebackColumn, bbHeardColumn);

		Sheet teamSheet = null;
		CellReference teamCellRef = null;
		
		if(!teamReference.equals(""))
		{
			teamSheet = workbook.getSheet(teamSheetName); //Find's player's team's sheet
			teamCellRef = new CellReference(teamReference); //Team starting reference
			readColumn2(sheetName, teamSheetName, bonusStatsSize, startRow, startColumn, tCatColumn, bCatColumn, delete, teamCellRef, bonusColumn, bouncebackColumn, bbHeardColumn);
		}
	
	}
	
	public void readColumn2(String sheetName, String playerSheetName, int bonusStatsSize, int startRow, int startColumn, int tCatColumn, int bCatColumn, boolean delete, CellReference cellRef, boolean bonusColumn, boolean bouncebackColumn, boolean bbHeardColumn)
	{
		Sheet playerSheet = workbook.getSheet(playerSheetName);
		Sheet columnSheet = workbook.getSheet(sheetName);
		Cell playerCell = playerSheet.getRow(cellRef.getRow()).getCell(cellRef.getCol());
		int powersColumn = 0, powersRow=0, tensColumn = 0, tensRow = 0, negsColumn = 0, negsRow = 0, tuhColumn=0, tuhRow=0, statsRow=0;
		int bhColumn = 0, bhRow = 0, bptsColumn = 0, bptsRow = 0, bbhColumn = 0, bbhRow = 0, bbptsColumn =0, bbptsRow =0;
		int thirtiesColumn = 0, thirtiesRow = 0, twentiesColumn = 0, twentiesRow = 0;
		boolean tuhHit = false;
		for(int i=0; i<2+onStats.size()+bonusStatsSize; i++) //This loop records the locations of the categories that will be written to 
		{
			if(playerSheet.getRow(cellRef.getRow()) == null)
			{
				System.out.println("Null stats row");
			}
			if(playerSheet.getRow(cellRef.getRow()).getCell(cellRef.getCol()) == null)
			{
				System.out.println("Player's starting cell is null");
				playerCell = playerSheet.getRow(cellRef.getRow()).createCell(cellRef.getCol());
			}
			else if(playerSheet.getRow(playerCell.getRowIndex()).getCell(playerCell.getColumnIndex()+i) == null)
			{
				System.out.println("Null cell in stats row");
			}
			else if(playerSheet.getRow(playerCell.getRowIndex()).getCell(playerCell.getColumnIndex()+i).getCellType() == XSSFCell.CELL_TYPE_BLANK)
			{
				System.out.println("Blank cell in stats row");
			}
			else if(playerSheet.getRow(playerCell.getRowIndex()).getCell(playerCell.getColumnIndex()+i).getCellType() == XSSFCell.CELL_TYPE_STRING)
			{
				String statsCategory = playerSheet.getRow(playerCell.getRowIndex()).getCell(playerCell.getColumnIndex()+i).getStringCellValue();
				System.out.println(statsCategory);
				statsRow = playerCell.getRowIndex();

				if(statsCategory.equals("30's"))
				{
					thirtiesColumn = playerCell.getColumnIndex()+i;
					thirtiesRow = playerCell.getRowIndex();
				}
				if(statsCategory.equals("20's"))
				{
					twentiesColumn = playerCell.getColumnIndex()+i;
					twentiesRow = playerCell.getRowIndex();
				}
				if(statsCategory.equals("Powers"))
				{
					powersColumn = playerCell.getColumnIndex()+i;
					powersRow = playerCell.getRowIndex();
				}
				else if(statsCategory.equals("Tens"))
				{
					tensColumn = playerCell.getColumnIndex()+i;
					tensRow = playerCell.getRowIndex();
				}
				else if(statsCategory.equals("Negs"))
				{
					negsColumn = playerCell.getColumnIndex() + i;
					negsRow = playerCell.getRowIndex();
				}
				else if(statsCategory.equals("TUH"))
				{
					tuhColumn = playerCell.getColumnIndex()+i;
					tuhRow = playerCell.getRowIndex();
				}
				else if(statsCategory.equals("BHrd"))
				{
					bhColumn = playerCell.getColumnIndex()+i;
					bhRow = playerCell.getRowIndex();
				}
				else if(statsCategory.equals("BPts"))
				{
					bptsColumn = playerCell.getColumnIndex()+i;
					bptsRow = playerCell.getRowIndex();
				}
				else if(statsCategory.equals("BBHrd"))
				{
					bbhColumn = playerCell.getColumnIndex()+i;
					bbhRow = playerCell.getRowIndex();
				}
				else if(statsCategory.equals("BBPts"))
				{
					bbptsColumn = playerCell.getColumnIndex()+i;
					bbptsRow = playerCell.getRowIndex();
				}
			}
		}
		boolean totalTossups = false;
		int tossupsHeard = 0;
		for(int i=0; totalTossups==false; i++)//This loop reads the data in one person's column and writes it to their sheet
		{
			if(columnSheet.getRow(startRow+i) == null)
			{
				System.out.println("Player's scoresheet row is null");
			}
			else if(columnSheet.getRow(startRow+i).getCell(startColumn) == null)
			{
				System.out.println("Player's scoresheet cell is null - did not score");
			}
			else if(columnSheet.getRow(startRow+i).getCell(startColumn).getCellType() == XSSFCell.CELL_TYPE_BLANK)
			{
				System.out.println("Player's scoresheet cell is blank - did not score");
			}
			else if(columnSheet.getRow(startRow+i).getCell(startColumn).getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
			{
				String currCat = "";
				if(!bonusColumn)
				{
					if(columnSheet.getRow(startRow+i).getCell(tCatColumn) != null && columnSheet.getRow(startRow+i).getCell(tCatColumn).getCellType() != XSSFCell.CELL_TYPE_BLANK && columnSheet.getRow(startRow+i).getCell(tCatColumn).getCellType() == XSSFCell.CELL_TYPE_STRING)
					{
						currCat = readCategory(sheetName, startRow+i, tCatColumn); //Reads abbreviated question category
						System.out.println(currCat); 
					}
				}
				else if(bonusColumn)
				{
					currCat = readCategory(sheetName, startRow+i, bCatColumn); //Reads abbreviated question category
					System.out.println(currCat); 
				}
				
				int catRow = setCategory(sheetName, currCat);//Gets categories index to know which row to write to
				
				Cell personCell = columnSheet.getRow(startRow+i).getCell(startColumn);//Increments the cell down the player's score sheet column
				int cellValue = (int) personCell.getNumericCellValue();//Reads each cell in a person's column in the score sheet
				System.out.println("Player scored: " + cellValue);
				
				editData(playerSheetName, cellValue, catRow, statsRow, thirtiesColumn, twentiesColumn, powersColumn, tensColumn, negsColumn, tuhColumn, bhColumn, bptsColumn, bbhColumn, bbptsColumn, delete, bonusColumn, bouncebackColumn, bbHeardColumn);//Where the data is edited
				tossupsHeard++;
			}
			else if(columnSheet.getRow(startRow+i).getCell(startColumn).getCellType() == XSSFCell.CELL_TYPE_STRING)
			{
				Cell personCell = columnSheet.getRow(startRow+i).getCell(startColumn);
				if(personCell.getStringCellValue().equals("x"))
				{
					totalTossups = true;
					System.out.println("X is found");
					System.out.println("End of column reached: " + totalTossups);
					System.out.println("Tossups heard: " + tossupsHeard);
				}
			}
		}
	}
	
	public void editData(String sheet, int cellValue, int catRow, int statsRow2, int thirtiesColumn, int twentiesColumn, int powersColumn, int tensColumn, int negsColumn, int tuhColumn, int bhColumn, int bptsColumn, int bbhColumn, int bbptsColumn, boolean delete, boolean bonusColumn, boolean bouncebackColumn, boolean bbHeardColumn)
	{
		if(bouncebackColumn)
			System.out.println("Bounceback points column");
		if(bbHeardColumn)
			System.out.println("BBHeard column");
		Sheet playerSheet = workbook.getSheet(sheet); //Finds player's sheet and starts at player's beginning cell
		int statsRow = statsRow2+1+catRow;
		int adder = 1;
		
		if(delete) //If mode is set to delete it deletes one from every place where it previously added one
		{
			adder = -1;
		}
		
		if(cellValue == 30 && !bonusColumn)
		{
			double existingValue = playerSheet.getRow(statsRow).getCell(thirtiesColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(thirtiesColumn).setCellValue(existingValue+adder);
		}
		else if(cellValue == 20 && !bonusColumn)
		{
			double existingValue = playerSheet.getRow(statsRow).getCell(twentiesColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(twentiesColumn).setCellValue(existingValue+adder);
		}
		else if(cellValue == 15 && !bonusColumn)
		{
			double existingValue = playerSheet.getRow(statsRow).getCell(powersColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(powersColumn).setCellValue(existingValue+adder);
		}
		else if(cellValue == 10 && !bonusColumn)
		{
			double existingValue = playerSheet.getRow(statsRow).getCell(tensColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(tensColumn).setCellValue(existingValue+adder);
		}
		else if(cellValue == -5 && !bonusColumn)
		{
			double existingValue = playerSheet.getRow(statsRow).getCell(negsColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(negsColumn).setCellValue(existingValue+adder);
		}
		else if(cellValue >= 0 && cellValue <= 30 && bonusColumn && !bouncebackColumn)
		{
			double existingValue = playerSheet.getRow(statsRow).getCell(bptsColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(bptsColumn).setCellValue(existingValue+cellValue*adder);
			double existingValue2 = playerSheet.getRow(statsRow).getCell(bhColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(bhColumn).setCellValue(existingValue2+adder);
		}
		else if(cellValue >= 0 && cellValue <= 30 && bonusColumn && bouncebackColumn && !bbHeardColumn)
		{
			double existingValue = playerSheet.getRow(statsRow).getCell(bbptsColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(bbptsColumn).setCellValue(existingValue+cellValue*adder);
		}
		else if(cellValue >= 0 && cellValue <= 30 && bonusColumn && bouncebackColumn && bbHeardColumn)
		{
			double existingValue2 = playerSheet.getRow(statsRow).getCell(bbhColumn).getNumericCellValue();
			playerSheet.getRow(statsRow).getCell(bbhColumn).setCellValue(existingValue2+cellValue*adder*.333333333333333333);
		}
		
		if(!bonusColumn)
		{
			double tuhValue = playerSheet.getRow(statsRow).getCell(tuhColumn).getNumericCellValue(); //Marks tuh even if person gets a 0
			System.out.println("TUH: " + tuhValue + "New TUH: " + tuhValue+adder);
			playerSheet.getRow(statsRow).getCell(tuhColumn).setCellValue(tuhValue+adder);
		}
	}
	
	public String readCategory(String sheetName, int startRow, int catColumn)
	{
		Sheet catSheet = workbook.getSheet(sheetName);
		String catEntered = catSheet.getRow(startRow).getCell(catColumn).getStringCellValue();
		return catEntered;
	}
	
	public int setCategory(String sheetName, String abCat)
	{
		Sheet sheet = workbook.getSheet(sheetName);
		int catIndex = 0;
		for(Category cat : onCategoryList)
		{
			if(abCat.equals(cat.getCategoryAbbrev()))
			{
				System.out.println(cat.categoryName);
				catIndex = onCategoryList.indexOf(cat);
			}
		}
		return catIndex;
	}
	
	public void findMainCategory(ArrayList<Category> categoryList)
	{
		categoryIndices = new ArrayList<Integer>();
		for(Category category : categoryList)
		{
			if(category.getCategoryName().toUpperCase().equals(category.getCategoryName()) && !category.getCategoryName().equals(("ALL")))
			{
				categoryIndices.add(categoryList.indexOf(category));
			}
		}
		categoryIndices.add(categoryList.size());
	}
	
	public void categoryFormula(String sheetName, String stat)
	{
		Sheet statsSheet = workbook.getSheet(sheetName);
		String stat1 = stat;
		
		XSSFCellStyle decimalStyle = workbook.createCellStyle();
		CellReference crStat = new CellReference(stat1);
		Cell currentCell;
		Cell catCell;
		Cell allCell;
		CellReference currentRef;
		String allEquation = "";
		decimalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		decimalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		decimalStyle.setBorderRight(CellStyle.BORDER_THIN);
		decimalStyle.setBorderTop(CellStyle.BORDER_THIN);
		decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		for(int i=0; i+1<categoryIndices.size(); i++) //Sums the subcategories for the main category
		{
			String categoryEquation = "";
			int startCell = categoryIndices.get(i);
			int endCell = categoryIndices.get(i+1);
			
			for(int j=startCell+1; j<endCell; j++)
			{
				currentCell = statsSheet.getRow(crStat.getRow()+j).getCell(crStat.getCol());
				currentRef = new CellReference(currentCell.getRowIndex(), currentCell.getColumnIndex());
				categoryEquation += "+" + currentRef.formatAsString();
			}
			if(categoryEquation.contains("+"))
			{
				int plusIndex = categoryEquation.indexOf('+');
				categoryEquation = categoryEquation.substring(plusIndex+1, categoryEquation.length());
			}
			//System.out.println("Category Powers " + categoryEquation);
			catCell = statsSheet.getRow(crStat.getRow()+startCell).getCell(crStat.getCol());
			catCell.setCellFormula("SUM(" + categoryEquation + ")");	
		}
		
		for(int i=0; i+1<categoryIndices.size(); i++) //Sums the main categories into All
		{
			currentCell = statsSheet.getRow(crStat.getRow()+categoryIndices.get(i)).getCell(crStat.getCol());
			currentRef = new CellReference(currentCell.getRowIndex(), currentCell.getColumnIndex());
			allEquation += "+" + currentRef.formatAsString();
		}
		if(allEquation.contains("+"))
		{
			int plusIndex = allEquation.indexOf("+");
			allEquation = allEquation.substring(plusIndex+1, allEquation.length());
		}
		//System.out.println(allEquation);
		allCell = statsSheet.getRow(crStat.getRow()).getCell(crStat.getCol());
		allCell.setCellFormula("SUM(" + allEquation + ")");
	}
	
	public void recordGame(String sheetName, Game game, int startRow, int startColumn)
	{
		Sheet gameSheet = workbook.getSheet(sheetName);
		
		CellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
		borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
		borderStyle.setBorderRight(CellStyle.BORDER_THIN);
		borderStyle.setBorderTop(CellStyle.BORDER_THIN);
		
		CellStyle boldStyle = workbook.createCellStyle();
		boldStyle.setBorderBottom(CellStyle.BORDER_THIN);
		boldStyle.setBorderLeft(CellStyle.BORDER_THIN);
		boldStyle.setBorderRight(CellStyle.BORDER_THIN);
		boldStyle.setBorderTop(CellStyle.BORDER_THIN);
		XSSFFont boldFont = workbook.createFont();
		boldFont.setBold(true);
		boldStyle.setFont(boldFont);
		boldStyle.setAlignment(CellStyle.ALIGN_CENTER);
		
		CellStyle nameStyle = workbook.createCellStyle();
		nameStyle.setBorderBottom(CellStyle.BORDER_THIN);
		nameStyle.setBorderLeft(CellStyle.BORDER_THIN);
		nameStyle.setBorderRight(CellStyle.BORDER_THIN);
		nameStyle.setBorderTop(CellStyle.BORDER_THIN);
		XSSFFont nameFont = workbook.createFont();
		nameFont.setBold(true);
		nameFont.setColor(IndexedColors.BLUE.index);
		nameFont.setItalic(true);
		nameStyle.setAlignment(CellStyle.ALIGN_CENTER);
		nameStyle.setFont(nameFont);
		
		CellStyle numberStyle = workbook.createCellStyle();
		numberStyle.setBorderBottom(CellStyle.BORDER_THIN);
		numberStyle.setBorderLeft(CellStyle.BORDER_THIN);
		numberStyle.setBorderRight(CellStyle.BORDER_THIN);
		numberStyle.setBorderTop(CellStyle.BORDER_THIN);
		numberStyle.setFont(boldFont);
		numberStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		CellStyle hiddenStyle = workbook.createCellStyle();
		DataFormat hiddenFormat = workbook.createDataFormat();
		hiddenStyle.setDataFormat(hiddenFormat.getFormat(";;;"));
		
		XSSFCellStyle blackStyle = workbook.createCellStyle();
		blackStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		blackStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		blackStyle.setBorderBottom(CellStyle.BORDER_THIN);
		blackStyle.setBorderLeft(CellStyle.BORDER_THIN);
		blackStyle.setBorderRight(CellStyle.BORDER_THIN);
		blackStyle.setBorderTop(CellStyle.BORDER_THIN);
		
		XSSFCellStyle headingStyle = workbook.createCellStyle();
		headingStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headingStyle.setFont(boldFont);
		
		XSSFCellStyle winnerStyle = workbook.createCellStyle();
		winnerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		XSSFFont winnerFont = workbook.createFont();
		winnerFont.setBold(true);
		winnerFont.setColor(IndexedColors.SEA_GREEN.index);
		winnerStyle.setFont(winnerFont);
		
		XSSFCellStyle loserStyle = workbook.createCellStyle();
		loserStyle.setAlignment(CellStyle.ALIGN_CENTER);
		XSSFFont loserFont = workbook.createFont();
		loserFont.setBold(true);
		loserFont.setColor(IndexedColors.RED.index);
		loserStyle.setFont(loserFont);
		
		XSSFCellStyle tieStyle = workbook.createCellStyle();
		tieStyle.setAlignment(CellStyle.ALIGN_CENTER);
		XSSFFont tieFont = workbook.createFont();
		tieFont.setBold(true);
		tieFont.setColor(IndexedColors.DARK_YELLOW.index);
		tieStyle.setFont(tieFont);

		for(int i=0; i< game.questions.size()+2; i++) //Creates score sheet table
		{
			Row tableRow = gameSheet.createRow(startRow+3+i);
			for(int j=0; j < game.playerInfo.size(); j++)
			{
				Cell currentCell = tableRow.createCell(startRow+j);
				if(i+1== game.questions.size()+2)
				{
					currentCell.setCellStyle(blackStyle);
				}
				else
				{
					currentCell.setCellStyle(borderStyle);
				}
			}
		}
		
		
		gameSheet.addMergedRegion(new CellRangeAddress(startRow, startRow, startColumn+1, startColumn+game.playerInfo.size()-2)); //Sets heading and merges cells
		Row testRow = gameSheet.getRow(startRow);
		testRow.getCell(startColumn+1).setCellValue("NAQT HSNCT Round 7");
		testRow.getCell(startColumn+1).setCellStyle(headingStyle);
		
		int cellRangeAdder = 0; //Adds to cell range address for first team
		int cellRangeAdder2 = 0; //Adds to cell range address for second team
		if(game.isTCatEnabled() && game.isBCatEnabled())
		{
			cellRangeAdder = 2;
			cellRangeAdder2 = 4 + game.team1Players;
		}
		else if(game.isTCatEnabled())
		{
			cellRangeAdder = 1;
			cellRangeAdder2 = 3 + game.team1Players;
		}
		else
		{
			cellRangeAdder = 0;
			cellRangeAdder2 = 2 + game.team1Players;
		}
		
		
		gameSheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, startColumn+1, startColumn+game.team1Players+cellRangeAdder)); //Merges team name cells
		gameSheet.addMergedRegion(new CellRangeAddress(startRow+1, startRow+1, startColumn+cellRangeAdder2, startColumn+game.playerInfo.size()-2));
		
		Row teamRow = gameSheet.getRow(startRow+1);
		teamRow.getCell(startColumn+1).setCellValue(game.team1Name); //Writes team names in cells
		teamRow.getCell(startColumn+cellRangeAdder2).setCellValue(game.team2Name);
		teamRow.getCell(startColumn+cellRangeAdder2-1).setCellValue("vs.");
		teamRow.getCell(startColumn+cellRangeAdder2-1).setCellStyle(headingStyle);
		
		if(game.team1Score > game.team2Score) //Determines which style to color team name cells
		{
			teamRow.getCell(startColumn+1).setCellStyle(winnerStyle);
			teamRow.getCell(startColumn+cellRangeAdder2).setCellStyle(loserStyle);
		}
		else if(game.team1Score < game.team2Score)
		{
			teamRow.getCell(startColumn+1).setCellStyle(loserStyle);
			teamRow.getCell(startColumn+cellRangeAdder2).setCellStyle(winnerStyle);
		}
		else if(game.team1Score == game.team2Score)
		{
			teamRow.getCell(startColumn+1).setCellStyle(tieStyle);
			teamRow.getCell(startColumn+cellRangeAdder2).setCellStyle(tieStyle);
		}
		
		
		Row playersRow = gameSheet.getRow(startRow+3); //Gets the headings row
		Row dataRow = gameSheet.createRow(startRow+2); //Creates row for player sheet data
		Row lastRow = gameSheet.getRow(startRow+3+game.questions.size()+1); //Gets last row to mark the x's
		int playerDataIndex = 0;
		
		for(int i=0; i < game.playerInfo.size(); i++) //Sets column headings
		{
			Cell currentCell = playersRow.createCell(startColumn + i);
			currentCell.setCellValue(game.playerInfo.get(i));
			if(game.playerInfo.get(i).equals("Tossup"))
			{
				Cell dataCell = dataRow.createCell(startColumn+i);
				dataCell.setCellValue("TCats");
			}
			else if(game.playerInfo.get(i).equals("Bonus"))
			{
				Cell dataCell = dataRow.createCell(startColumn+i);
				dataCell.setCellValue("BCats");
			}
			
			if(game.playerIndices.contains(i))
			{
				currentCell.setCellStyle(nameStyle);
				Cell dataCell = dataRow.createCell(startColumn+i);
				Cell lastCell = lastRow.getCell(startColumn+i);
				dataCell.setCellValue(game.playerData.get(playerDataIndex));
				dataCell.setCellStyle(hiddenStyle);
				lastCell.setCellValue("x");
				lastCell.setCellStyle(blackStyle);
				playerDataIndex++;
			}
			else if(i==0)
			{
				currentCell.setCellStyle(numberStyle);
			}
			else if(i+1 == game.playerInfo.size())
			{
				currentCell.setCellStyle(boldStyle);
				Cell endCell = dataRow.createCell(startColumn+i);
				endCell.setCellValue("end");
				endCell.setCellStyle(hiddenStyle);
			}
			else
			{
				currentCell.setCellStyle(boldStyle);
			}
			//currentCell.setCellStyle(borderStyle);
		}
		
		for(int j=0; j< game.questions.size(); j++) //Fills in the question data
		{
			Row questionRow = gameSheet.getRow(startRow+4+j);
			Question currentQuestion = game.questions.get(j);
			questionRow.getCell(startColumn).setCellValue(currentQuestion.qNumber);
			if(game.isTCatEnabled())
			{
				questionRow.getCell(startColumn+1).setCellValue(currentQuestion.tossupAbbrev);
			}
			if(game.isBCatEnabled())
			{
				questionRow.getCell(startColumn+4+game.team1Players).setCellValue(currentQuestion.bonusAbbrev);
			}
			
			for(int i=0; i < currentQuestion.questionData.size(); i++)
			{
				String currentData = currentQuestion.questionData.get(i);
				int dollarIndex = currentData.indexOf("$");
				String columnData = currentData.substring(0, dollarIndex);
				String pointData = currentData.substring(dollarIndex+1, currentData.length());
				Integer column = Integer.parseInt(columnData);
				Integer points = Integer.parseInt(pointData);
				System.out.println("Question: " + j + " Data: " + i + " Column: " + column + " Points: " + points);
				questionRow.getCell(startColumn+column).setCellValue(points);
			}
		}		
	}
}
