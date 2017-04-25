package simplified_applications;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import be_gui.Cut;
import be_gui.Shade;
import utilities.FileEmailer;
import javax.swing.JButton;

public class ShadeCutPanel extends JPanel {

	private int shadeCutsPrinted;
	private String dateText;
	private Timer timer;
	private SimpleDateFormat sdf;
	private ShadeCutPanelListener cpl;
	
	private String customCutFilePath;
	private File currentCustomCutFile, tempCustomCutFile;
	private long currentCustomCutTimeStamp, tempCustomCutTimeStamp;
	private ArrayList<Cut> currentCustomCuts, currentShadeCuts;
	private XSSFWorkbook currentWorkbook;
	private Sheet currentSheet;
	private JLabel lblCustomCutTime, lblShadeCuts;
	private ArrayList<Cut> all, shades;
	
	private FileEmailer cutSender;
	private JButton btnPinger;
	
	/**
	 * Create the panel.
	 */
	public ShadeCutPanel() {
		cpl = new ShadeCutPanelListener();
		timer = new Timer(3769, cpl);
		timer.addActionListener(cpl);
		timer.start();
		
		customCutFilePath = "test5";//"Z:\\AAA-P2CSTM";
		currentCustomCutFile = new File(customCutFilePath);
		currentCustomCutTimeStamp = currentCustomCutFile.lastModified();
		
		tempCustomCutFile = new File(customCutFilePath);
		tempCustomCutTimeStamp = tempCustomCutFile.lastModified();
		
		cutSender = new FileEmailer();
		
		currentWorkbook = new XSSFWorkbook();
		currentSheet = currentWorkbook.createSheet();
		
		setLayout(new BorderLayout(0,0));
		sdf = new SimpleDateFormat("hh:mm");
		
		dateText = sdf.format(new Date());
		
		JPanel TextPanel = new JPanel();
		add(TextPanel, BorderLayout.NORTH);
		TextPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCustomCutsLastPrinted = new JLabel("Custom Cuts Last Printed:");
		lblCustomCutsLastPrinted.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		TextPanel.add(lblCustomCutsLastPrinted);
		
		lblCustomCutTime = new JLabel(dateText);
		lblCustomCutTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextPanel.add(lblCustomCutTime);
		
		JLabel lblShadeCutsPrinted = new JLabel("Shade Cuts Printed:");
		lblShadeCutsPrinted.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		TextPanel.add(lblShadeCutsPrinted);
		
		lblShadeCuts = new JLabel("" + shadeCutsPrinted );
		lblShadeCuts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextPanel.add(lblShadeCuts);
		
		btnPinger = new JButton("Pinger");
		btnPinger.addActionListener(cpl);
		add(btnPinger, BorderLayout.SOUTH);

	}
	
	
	
	private void parseCuts(File cutFile){
		all = new ArrayList<Cut>();
		shades = new ArrayList<Cut>();
				
		try {
			Scanner scan = new Scanner(cutFile);
			scan.useDelimiter(new String(new char[] { 12 }));
			while (scan.hasNext()){
				Cut c = new Cut(scan.next());
				if(c.isCut()){
					all.add(c);
				}
				if(c.isShadeCut()){
					shades.add(c);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!shades.isEmpty()){
			generateExcel();
		}
	}
	
	private void generateExcel(){
		ArrayList<File> excelFiles = new ArrayList<File>();
		
		for(Cut c: shades){
			if(c.isShadeCut()){
				XSSFWorkbook newBook = null;					
				FileOutputStream fileOut;	
				try{
					newBook = new XSSFWorkbook();
				}
				catch(java.lang.IllegalArgumentException jli){
					
				}
				
				XSSFCellStyle hcs = newBook.createCellStyle();
				XSSFFont hf = newBook.createFont();
				hf.setFontHeightInPoints((short) 14);
				hf.setFontName("Calibri");
				hf.setBold(true);
				hcs.setFont(hf);

				XSSFCellStyle cs = newBook.createCellStyle();
				XSSFFont f = newBook.createFont();
				f.setFontHeightInPoints((short) 12);
				f.setFontName("Arial");
				cs.setFont(f);
				cs.setBorderBottom(CellStyle.BORDER_THIN);
				cs.setBorderTop(CellStyle.BORDER_THIN);
				cs.setBorderLeft(CellStyle.BORDER_THIN);
				cs.setBorderRight(CellStyle.BORDER_THIN);
				cs.setAlignment(CellStyle.ALIGN_CENTER);
				
				XSSFCellStyle ul = (XSSFCellStyle) hcs.clone();
				ul.setBorderBottom(CellStyle.BORDER_THICK);
				
				PrintSetup ps;
				
				//Master Sheet Header
				
				Sheet newSheet = newBook.createSheet("Main");
				Row currentRow = newSheet.createRow(1);
				Cell currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getCustomerName());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(2);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddTwo());
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Cut");
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue(c.getCutNumber());
				currentCell.setCellStyle(hcs);			
				currentRow = newSheet.createRow(3);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddThree());
				currentCell.setCellStyle(hcs);			
				currentRow = newSheet.createRow(4);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddFour());
				currentCell.setCellStyle(hcs);
						
				currentRow = newSheet.createRow(6);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue("Notes");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Type");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(2);
				currentCell.setCellValue("Description");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Quantity");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue("IO");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(5);
				currentCell.setCellValue("Width");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("Length");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(7);
				currentCell.setCellValue("System");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(8);
				currentCell.setCellValue("Control");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(9);
				currentCell.setCellValue("Fabric");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(10);
				currentCell.setCellValue("Fascia");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(11);
				currentCell.setCellValue("Fascia Height");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(12);
				currentCell.setCellValue("Fascia Color");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(13);
				currentCell.setCellValue("Hem");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(14);
				currentCell.setCellValue("Hem Color");
				currentCell.setCellStyle(cs);
				
				//Master Sheet Body
				
				int rowNumber = 7;
				int qtyTotal = 0;
				for(Shade s: c.getShades()){
					currentRow = newSheet.createRow(rowNumber);
					
					currentCell = currentRow.createCell(0);
					currentCell.setCellValue(s.getComments());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(1);
					currentCell.setCellValue(s.getType());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(2);
					currentCell.setCellValue(s.getDescription());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(3);
					currentCell.setCellValue(s.getQuantity());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(4);
					currentCell.setCellValue(s.getIo());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(5);
					currentCell.setCellValue(mdc(s.getWidth()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(6);
					currentCell.setCellValue(mdc(s.getLength()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(7);
					currentCell.setCellValue(s.getSystem());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(8);
					currentCell.setCellValue(s.getControl());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(9);
					currentCell.setCellValue(s.getFabric());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(10);
					currentCell.setCellValue(s.getFascia());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(11);
					currentCell.setCellValue(s.getFasciaHeight());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(12);
					currentCell.setCellValue(s.getFasciaColor());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(13);
					currentCell.setCellValue(s.getHem());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(14);
					currentCell.setCellValue(s.getHemColor());
					currentCell.setCellStyle(cs);
					
					rowNumber++;
					qtyTotal = qtyTotal + s.getQuantity();
				}
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(2);
				currentCell.setCellValue("Total");
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue(qtyTotal);
				currentCell.setCellStyle(hcs);
				
				newSheet.autoSizeColumn(0);
				newSheet.autoSizeColumn(1);
				newSheet.autoSizeColumn(2);
				newSheet.autoSizeColumn(3);
				newSheet.autoSizeColumn(4);
				newSheet.autoSizeColumn(5);
				newSheet.autoSizeColumn(6);
				newSheet.autoSizeColumn(7);
				newSheet.autoSizeColumn(8);
				newSheet.autoSizeColumn(9);
				newSheet.autoSizeColumn(10);
				newSheet.autoSizeColumn(11);
				newSheet.autoSizeColumn(12);
				newSheet.autoSizeColumn(13);
				newSheet.autoSizeColumn(14);
				
				ps = newSheet.getPrintSetup();
				newSheet.setAutobreaks(true);
				ps.setFitWidth((short) 1);
				ps.setLandscape(true);
				
				// Fabric Cut Sheet Header
				
				newSheet = newBook.createSheet("Fabric Cut Sheet");
				currentRow = newSheet.createRow(1);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getCustomerName());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(2);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddTwo());
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Cut");
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue(c.getCutNumber());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(3);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddThree());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(4);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddFour());
				currentCell.setCellStyle(hcs);
				
				// Fabric Cut Sheet Body
				// Header
				rowNumber = 5;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue("1st Cut");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(5);
				currentCell.setCellValue("Trim (2nd Cut)");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("3rd Cut");
				currentCell.setCellStyle(cs);
				
				rowNumber++;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue("Notes");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Qty");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(2);
				currentCell.setCellValue("Width");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Length");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue("Shade Length");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(5);
				currentCell.setCellValue("Shade Width");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("Shade Width");
				currentCell.setCellStyle(cs);
				
				// Body
				rowNumber++;
				for(Shade s: c.getShades()){
					currentRow = newSheet.createRow(rowNumber);
					currentCell = currentRow.createCell(0);
					currentCell.setCellValue(s.getComments());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(1);
					currentCell.setCellValue(s.getQuantity());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(2);
					currentCell.setCellValue(mdc(s.getWidth()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(3);
					currentCell.setCellValue(mdc(s.getLength()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(4);
					currentCell.setCellValue(mdc(s.getFabCutOne()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(5);
					currentCell.setCellValue(mdc(s.getFabCutTwo()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(6);
					currentCell.setCellValue(mdc(s.getFabCutThree()));
					currentCell.setCellStyle(cs);

					rowNumber++;					
				}
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue("Total");
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(qtyTotal);
				currentCell.setCellStyle(hcs);
				
				rowNumber++; rowNumber++;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Cut By:");
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(2);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(3);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(4);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(5);
				currentCell.setCellStyle(ul);
				
				rowNumber++; rowNumber++;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Cut Date:");
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(2);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(3);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(4);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(5);
				currentCell.setCellStyle(ul);
				
				newSheet.autoSizeColumn(0);
				newSheet.autoSizeColumn(1);
				newSheet.autoSizeColumn(2);
				newSheet.autoSizeColumn(3);
				newSheet.autoSizeColumn(4);
				newSheet.autoSizeColumn(5);
				newSheet.autoSizeColumn(6);
				
				ps = newSheet.getPrintSetup();
				newSheet.setAutobreaks(true);
				ps.setFitWidth((short) 1);
				ps.setLandscape(true);
				
				//Material Cut Sheet
				newSheet = newBook.createSheet("Material Cut Sheet");
				currentRow = newSheet.createRow(1);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getCustomerName());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(2);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddTwo());
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Cut");
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue(c.getCutNumber());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(3);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddThree());
				currentCell.setCellStyle(hcs);			
				currentRow = newSheet.createRow(4);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddFour());
				currentCell.setCellStyle(hcs);
				
				rowNumber = 6;
				
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue("Notes");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Qty");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(2);
				currentCell.setCellValue("Shade Width");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Tube Size");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue("Tube Cut Width");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(5);
				currentCell.setCellValue("Fascia Size");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("Fascia Color");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(7);
				currentCell.setCellValue("Fascia Cut Width");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(8);
				currentCell.setCellValue("Notch");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(9);
				currentCell.setCellValue("SIB/DBB");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(10);
				currentCell.setCellValue("Hem Color");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(11);
				currentCell.setCellValue("Bar Cut Width");
				currentCell.setCellStyle(cs);

				rowNumber++;
				
				for(Shade s : c.getShades()){
					currentRow = newSheet.createRow(rowNumber);
					currentCell = currentRow.createCell(0);
					currentCell.setCellValue(s.getComments());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(1);
					currentCell.setCellValue(s.getQuantity());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(2);
					currentCell.setCellValue(mdc(s.getWidth()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(3);
					currentCell.setCellValue(s.getMatTubeSize());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(4);
					currentCell.setCellValue(mdc(s.getMatTube()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(5);
					currentCell.setCellValue(s.getFasciaHeight());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(6);
					currentCell.setCellValue(s.getFasciaColor());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(7);
					currentCell.setCellValue(mdc(s.getMatFascia()));
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(8);
					currentCell.setCellValue(s.getMatNotch());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(9);
					if(s.getMatDBB() == 0){
						currentCell.setCellValue("SIB");
						currentCell.setCellStyle(cs);
						currentCell = currentRow.createCell(11);
						currentCell.setCellValue(mdc(s.getMatSIB()));
						currentCell.setCellStyle(cs);
					}
					if(s.getMatSIB() == 0){
						currentCell.setCellValue("DBB");
						currentCell.setCellStyle(cs);
						currentCell = currentRow.createCell(11);
						currentCell.setCellValue(mdc(s.getMatDBB()));
						currentCell.setCellStyle(cs);
					}
					currentCell = currentRow.createCell(10);
					currentCell.setCellValue(s.getHemColor());
					currentCell.setCellStyle(cs);
					
					rowNumber++;
				}
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue("Total");
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(qtyTotal);
				currentCell.setCellStyle(hcs);
				
				rowNumber++; rowNumber++;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Cut By:");
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(2);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(3);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(4);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(5);
				currentCell.setCellStyle(ul);
				
				rowNumber++; rowNumber++;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Cut Date:");
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(2);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(3);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(4);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(5);
				currentCell.setCellStyle(ul);
				
				newSheet.autoSizeColumn(0);
				newSheet.autoSizeColumn(1);
				newSheet.autoSizeColumn(2);
				newSheet.autoSizeColumn(3);
				newSheet.autoSizeColumn(4);
				newSheet.autoSizeColumn(5);
				newSheet.autoSizeColumn(6);
				newSheet.autoSizeColumn(7);
				newSheet.autoSizeColumn(8);
				newSheet.autoSizeColumn(9);
				newSheet.autoSizeColumn(10);
				newSheet.autoSizeColumn(11);
				
				ps = newSheet.getPrintSetup();
				newSheet.setAutobreaks(true);
				ps.setFitWidth((short) 1);
				ps.setLandscape(true);
				
				//Clutch Cut Sheet
				
				newSheet = newBook.createSheet("Clutch Cut Sheet");
				currentRow = newSheet.createRow(1);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getCustomerName());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(2);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddTwo());
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Cut");
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue(c.getCutNumber());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(3);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddThree());
				currentCell.setCellStyle(hcs);
				currentRow = newSheet.createRow(4);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue(c.getShippingAddFour());
				currentCell.setCellStyle(hcs);
				
				rowNumber = 6;
				
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue("Notes");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Qty");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(2);
				currentCell.setCellValue("Chain Length");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Clutch Size");
				currentCell.setCellStyle(cs);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue("Control");
				currentCell.setCellStyle(cs);
				
				rowNumber++;
							
				for(Shade s: c.getShades()){
					currentRow = newSheet.createRow(rowNumber);
					currentCell = currentRow.createCell(0);
					currentCell.setCellValue(s.getComments());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(1);
					currentCell.setCellValue(s.getQuantity());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(2);
					currentCell.setCellValue(s.getClutchChainLength());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(3);
					currentCell.setCellValue(s.getClutchSize());
					currentCell.setCellStyle(cs);
					currentCell = currentRow.createCell(4);
					currentCell.setCellValue(s.getControl());
					currentCell.setCellStyle(cs);

					rowNumber++;
				}
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue("Total");
				currentCell.setCellStyle(hcs);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(qtyTotal);
				currentCell.setCellStyle(hcs);
				
				
				rowNumber++; rowNumber++;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Assembled By:");
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(2);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(3);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(4);
				currentCell.setCellStyle(ul);
				
				rowNumber++; rowNumber++;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Assembled Date:");
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(2);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(3);
				currentCell.setCellStyle(ul);
				currentCell = currentRow.createCell(4);
				currentCell.setCellStyle(ul);

				
				newSheet.autoSizeColumn(0);
				newSheet.autoSizeColumn(1);
				newSheet.autoSizeColumn(2);
				newSheet.autoSizeColumn(3);
				newSheet.autoSizeColumn(4);
				
				ps = newSheet.getPrintSetup();
				newSheet.setAutobreaks(true);
				ps.setFitWidth((short) 1);
				ps.setLandscape(true);

				try{
					fileOut = new FileOutputStream("Cut Excel Sheets/" + c.getCutNumber() + ".xlsx");
					newBook.write(fileOut);
					fileOut.close();
				} catch(Exception e){
					e.printStackTrace();
				}
				
				cutSender.sendEmailWithAttachment("jedwards@mariettadrapery.com", "Shade Cut " + c.getCutNumber(), "Test Body", "Cut Excel Sheets/" + c.getCutNumber() + 
						".xlsx", "Cut " + c.getCutNumber() + ".xlsx");
				//cutSender.sendEmailWithAttachment("msolomon@mariettadrapery.com", "Shade Cut " + c.getCutNumber(), "Test Body", "Cut Excel Sheets/" + c.getCutNumber() + 
				//		".xlsx", "Cut " + c.getCutNumber() + ".xlsx");
				//cutSender.sendEmailWithAttachment("jtregoning@mariettadrapery.com", "Shade Cut " + c.getCutNumber(), "Test Body", "Cut Excel Sheets/" + c.getCutNumber() + 
				//		".xlsx", "Cut " + c.getCutNumber() + ".xlsx");
			}
		}
		
	}
	
	public double mdc(double in){
		double rem = in % 1;
		return Math.floor(in) + (rem * 0.8);
	}
	
		
	private class ShadeCutPanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnPinger)){
			parseCuts(currentCustomCutFile);
			}
			if(e.getSource().equals(timer)){
				if(checkUpdate()){
					parseCuts(currentCustomCutFile);
				}
			}
			
		}

		private boolean checkUpdate() {
			tempCustomCutFile = new File(customCutFilePath);
			tempCustomCutTimeStamp = tempCustomCutFile.lastModified();
			if(tempCustomCutTimeStamp != currentCustomCutTimeStamp){
				currentCustomCutFile = tempCustomCutFile;
				currentCustomCutTimeStamp = tempCustomCutTimeStamp;
				updateTime();
				return true;
			}
			return false;
		}

		private void updateTime() {
			Date currentTime = new Date();
			lblCustomCutTime.setText(sdf.format(currentTime));
		}
		
	}
		
}
