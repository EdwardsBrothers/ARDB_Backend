package be_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
	private Pinger pinger;
	
	
	public InvoiceHandler() {
		super("Invoices");
		sdf = new SimpleDateFormat("MM-dd-yy kk:mm");
		invoiceEmailer = new FileEmailer();
		il = new InvoiceListener();
		pinger = new Pinger();
		timer = new Timer(27777, il);
		invGen = new InvoiceGenerator();
		
		btnPing.addActionListener(pinger);
		
		invoiceFilePath = "Z:\\CCINV";
		currentInvoiceFile = new File(invoiceFilePath);
		currentInvoiceTimeStamp = currentInvoiceFile.lastModified();
		
		timer.start();
		lblStatus.setText("Running");
		lblLastUpdate.setText(sdf.format(new Date()));
		
	}
	
	private void parseInvoices(File file){
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		ArrayList<Invoice> electronic = new ArrayList<Invoice>();
		try {
			Scanner scan = new Scanner(file);
			scan.useDelimiter("BEGIN");
			if(!scan.hasNext()){
				scan.close();
				return;
			}
			scan.next();
			while(scan.hasNext()){
				Invoice i = new Invoice(scan.next());
				if(i.checkValid()){
					invoices.add(i);		
					if(i.isElectronic()){
						electronic.add(i);
					}
				}
						
			}
			scan.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		processElectronic(electronic);		
		eSupply(invoices);
	}
	
	
	private void processElectronic(ArrayList<Invoice> electronic) {
		

		
		//goldmark(electronic);
		
	}


	private void eSupply(ArrayList<Invoice> electronic) {
		
		String qs = "";
		
		try{
			String dbConnect = "jdbc:mysql://10.36.40.250:3306/ardb?autoReconnect=true&useSSL=false&user=jedwards&password=terran";
			Connection con = null;
			Statement stmt = null;
			//
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbConnect);
			System.out.println("connected");
			
		
			for(Invoice i : electronic){
				if(i.getManagementCode().equals("ES")){
					qs = "UPDATE ardb.esupply SET INVOICE_NUMBER= \'" + i.getInvoiceNumber() + "\' , INVOICE_DATE= \'" + i.getsqlOrderDate() + "\' , SHIP_DATE= \'"
							+ i.getsqlOrderDate() + "\' WHERE ORDER_NUMBER= \'" + i.getCutNumber() + "\';";
					stmt = con.createStatement();
					stmt.executeUpdate(qs);
					qs = "";

				}
			}
			
		}
			
		catch(Exception e){
			lblStatus.setText("BROKEN!!!!!!!");
		}
		
	}


	private class InvoiceListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			File tempFile = new File(invoiceFilePath);
			long tempTimeStamp = tempFile.lastModified();
			if(tempTimeStamp != currentInvoiceTimeStamp){
				currentInvoiceFile = tempFile;
				currentInvoiceTimeStamp = currentInvoiceFile.lastModified();
				updateTime();
				parseInvoices(currentInvoiceFile);
			}
			
		}
	}
		
	private class Pinger implements ActionListener{
			@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(btnPing)){
				parseInvoices(currentInvoiceFile);
			}
		}
	}
}
