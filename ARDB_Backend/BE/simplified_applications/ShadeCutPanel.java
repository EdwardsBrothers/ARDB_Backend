package simplified_applications;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
		timer.start();
		
		customCutFilePath = "jotfile";     //"Z:\\AAA-P2CSTM";
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
		
		for(Cut c: shades){
			if(c.isShadeCut()){
				XSSFWorkbook newBook = null;					
				FileOutputStream fileOut;	
				int sheets;
				try{
					newBook = new XSSFWorkbook();
					sheets = 1;
				}
				catch(java.lang.IllegalArgumentException jli){
					
				}
			
				//Master Sheet Header
				
				Sheet newSheet = newBook.createSheet("Main");
				Row currentRow = newSheet.createRow(1);
				Cell currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getCustomerName());
			
				currentRow = newSheet.createRow(2);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddTwo());
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("Cut");
				currentCell = currentRow.createCell(7);
				currentCell.setCellValue(c.getCutNumber());
			
				currentRow = newSheet.createRow(3);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddThree());
			
				currentRow = newSheet.createRow(4);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddFour());
				
				currentRow = newSheet.createRow(6);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Type");
				currentCell = currentRow.createCell(2);
				currentCell.setCellValue("Description");
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Quantity");
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue("IO");
				currentCell = currentRow.createCell(5);
				currentCell.setCellValue("Width");
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("Length");
				currentCell = currentRow.createCell(7);
				currentCell.setCellValue("System");
				currentCell = currentRow.createCell(8);
				currentCell.setCellValue("Control");
				currentCell = currentRow.createCell(9);
				currentCell.setCellValue("Fabric");
				currentCell = currentRow.createCell(10);
				currentCell.setCellValue("Fascia");
				currentCell = currentRow.createCell(11);
				currentCell.setCellValue("Fascia Height");
				currentCell = currentRow.createCell(12);
				currentCell.setCellValue("Fascia Color");
				currentCell = currentRow.createCell(13);
				currentCell.setCellValue("Hem");
				currentCell = currentRow.createCell(14);
				currentCell.setCellValue("Hem Color");
				currentCell = currentRow.createCell(15);
				currentCell.setCellValue("Comments");
				
				//Master Sheet Body
				
				int rowNumber = 7;
				for(Shade s: c.getShades()){
					currentRow = newSheet.createRow(rowNumber);
					
					currentCell = currentRow.createCell(1);
					currentCell.setCellValue(s.getType());
					currentCell = currentRow.createCell(2);
					currentCell.setCellValue(s.getDescription());
					currentCell = currentRow.createCell(3);
					currentCell.setCellValue(s.getQuantity());
					currentCell = currentRow.createCell(4);
					currentCell.setCellValue(s.getIo());
					String wxl = s.getWidthxlength();
					int index = wxl.indexOf("X");
					String w = wxl.substring(0, index).trim();
					String l = wxl.substring(index+1).trim();
					currentCell = currentRow.createCell(5);
					currentCell.setCellValue(w);
					currentCell = currentRow.createCell(6);
					currentCell.setCellValue(l);
					currentCell = currentRow.createCell(7);
					currentCell.setCellValue(s.getSystem());
					currentCell = currentRow.createCell(8);
					currentCell.setCellValue(s.getControl());
					currentCell = currentRow.createCell(9);
					currentCell.setCellValue(s.getFabric());
					currentCell = currentRow.createCell(10);
					currentCell.setCellValue(s.getFascia());
					currentCell = currentRow.createCell(11);
					currentCell.setCellValue(s.getFasciaHeight());
					currentCell = currentRow.createCell(12);
					currentCell.setCellValue(s.getFasciaColor());
					currentCell = currentRow.createCell(13);
					currentCell.setCellValue(s.getHem());
					currentCell = currentRow.createCell(14);
					currentCell.setCellValue(s.getHemColor());
					currentCell = currentRow.createCell(15);
					currentCell.setCellValue(s.getComments());
					
					rowNumber++;
				}
				
				// Fabric Cut Sheet Header
				
				newSheet = newBook.createSheet("Fabric Cut Sheet");
				currentRow = newSheet.createRow(1);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getCustomerName());
			
				currentRow = newSheet.createRow(2);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddTwo());
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("Cut");
				currentCell = currentRow.createCell(7);
				currentCell.setCellValue(c.getCutNumber());
			
				currentRow = newSheet.createRow(3);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddThree());
			
				currentRow = newSheet.createRow(4);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddFour());
				
				// Fabric Cut Sheet Body
				// Header
				rowNumber = 5;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue("1st Cut");
				currentCell = currentRow.createCell(5);
				currentCell.setCellValue("Trim (2nd Cut)");
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("3rd Cut");
				
				rowNumber++;
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(0);
				currentCell.setCellValue("Window");
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue("Qty");
				currentCell = currentRow.createCell(2);
				currentCell.setCellValue("Width");
				currentCell = currentRow.createCell(3);
				currentCell.setCellValue("Length");
				currentCell = currentRow.createCell(4);
				currentCell.setCellValue("Shade Length");
				currentCell = currentRow.createCell(5);
				currentCell.setCellValue("Shade Width");
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("Shade Width");
				currentCell = currentRow.createCell(7);
				currentCell.setCellValue("Notes");
				
				// Body
				rowNumber++;
				int windowNumber = 1;
				for(Shade s: c.getShades()){
					currentRow = newSheet.createRow(rowNumber);
					currentCell = currentRow.createCell(0);
					currentCell.setCellValue(c.getCutNumber() + " -" + windowNumber);
					currentCell = currentRow.createCell(1);
					currentCell.setCellValue(s.getQuantity());
					currentCell = currentRow.createCell(2);
					currentCell.setCellValue(s.getWidth());
					currentCell = currentRow.createCell(3);
					currentCell.setCellValue(s.getLength());
					currentCell = currentRow.createCell(4);
					currentCell.setCellValue(s.getFabCutOne());
					currentCell = currentRow.createCell(5);
					currentCell.setCellValue(s.getFabCutTwo());
					currentCell = currentRow.createCell(6);
					currentCell.setCellValue(s.getFabCutThree());
					currentCell = currentRow.createCell(7);
					currentCell.setCellValue(s.getComments());
					windowNumber++;
					rowNumber++;					
				}
				
				//Material Cut Sheet
				newSheet = newBook.createSheet("Material Cut Sheet");
				currentRow = newSheet.createRow(1);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getCustomerName());
			
				currentRow = newSheet.createRow(2);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddTwo());
				currentCell = currentRow.createCell(6);
				currentCell.setCellValue("Cut");
				currentCell = currentRow.createCell(7);
				currentCell.setCellValue(c.getCutNumber());
			
				currentRow = newSheet.createRow(3);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddThree());
			
				currentRow = newSheet.createRow(4);
				currentCell = currentRow.createCell(1);
				currentCell.setCellValue(c.getShippingAddFour());
				
				rowNumber = 6;
				int colNumber = 0;
				
				currentRow = newSheet.createRow(rowNumber);
				currentCell = currentRow.createCell(colNumber);
				currentCell.setCellValue("Window");
				colNumber++;
				currentCell = currentRow.createCell(colNumber);
				currentCell.setCellValue("Qty");
				colNumber++;
				
				
				

				try{
					fileOut = new FileOutputStream("Cut Excel Sheets/" + c.getCutNumber() + ".xlsx");
					newBook.write(fileOut);
					fileOut.close();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
		
		
	private class ShadeCutPanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnPinger)){
			parseCuts(currentCustomCutFile);
			}
			
		}
		
	}
		
}
