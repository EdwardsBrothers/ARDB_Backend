package be_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.Timer;

import utilities.FileEmailer;
import utilities.InvoiceGenerator;

public class InvoiceHandler extends ServicePanelInd {

	private String invoiceFilePath;
	private File currentInvoiceFile;
	private long currentInvoiceTimeStamp;
	private FileEmailer invoiceEmailer;
	private InvoiceGenerator invGen;
	private SimpleDateFormat sdf;
	private InvoiceListener il;
	
	
	public InvoiceHandler() {
		super("Invoices");
		sdf = new SimpleDateFormat("MM-dd-yy kk:mm");
		invoiceEmailer = new FileEmailer();
		il = new InvoiceListener();
		timer = new Timer(27777, il);
		invGen = new InvoiceGenerator();
		
		//invoiceFilePath = "Z:\\CCINV";
		invoiceFilePath = "CCINV";
		currentInvoiceFile = new File(invoiceFilePath);
		currentInvoiceTimeStamp = currentInvoiceFile.lastModified();
		
		timer.start();
		lblStatus.setText("Running");
		lblLastUpdate.setText(sdf.format(new Date()));
		
	}
	
	private boolean parseInvoices(File file){
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		ArrayList<Invoice> electronic = new ArrayList<Invoice>();
		try {
			Scanner scan = new Scanner(file);
			scan.useDelimiter("BEGIN");
			if(!scan.hasNext()){
				scan.close();
				return false;
			}
			scan.next();
			while(scan.hasNext()){
				Invoice i = new Invoice(scan.next());
				invoices.add(i);				
				
			}
			scan.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	private class InvoiceListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			File tempFile = new File(invoiceFilePath);
			long tempTimeStamp = tempFile.lastModified();
			if(tempTimeStamp != currentInvoiceTimeStamp){
				currentInvoiceFile = tempFile;
				currentInvoiceTimeStamp = currentInvoiceFile.lastModified();
				parseInvoices(currentInvoiceFile);
			}
			
		}
		
	}

}
