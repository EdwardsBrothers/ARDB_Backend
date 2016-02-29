package be_gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Invoice {
	
	private int cutNumber;
	private Date invoiceDate;
	private String invoiceNumber;
	private String managementCode;
	private String managementCompanyName;
	private boolean electronic;
	private boolean valid;

	public Invoice(String invoice){
		String currentLine = "";
		int lineNum = 0;
		electronic = false;
		boolean running = true;
		valid = true;
		invoiceDate = new Date();
		
		Scanner scan = new Scanner(invoice);
		while(running){
			try{
							currentLine = scan.nextLine();
			}catch(NoSuchElementException nsee){
				running = false;
				valid = false;
			}

			if(currentLine.contains("$") || currentLine.contains("%")){
				lineNum--;
			}
			
			if(lineNum == 9){//mgt code
				managementCode = currentLine.trim();
			}
			if(lineNum == 11){//date & invoice number
				invoiceNumber = currentLine.substring(75);
			}
			
			if(currentLine.contains("NONTAXABLE")){
				currentLine = scan.nextLine();
				cutNumber = Integer.parseInt(currentLine.substring(1,8));
				running = false;
			}
			
			if(currentLine.contains("S E N D    E L E C T R O N I C A L L Y")){
				electronic = true;
			}
			
			lineNum++;
		}

		
	}

	public boolean isElectronic() {
		return electronic;
	}

	public void setElectronic(boolean electronic) {
		this.electronic = electronic;
	}

	public String getManagementCode() {
		return managementCode;
	}

	public void setManagementCode(String managementCode) {
		this.managementCode = managementCode;
	}

	public int getCutNumber() {
		return cutNumber;
	}

	public void setCutNumber(int cutNumber) {
		this.cutNumber = cutNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public boolean checkValid(){
		return valid;
	}
	
	public java.sql.Date getsqlOrderDate() {
		return new java.sql.Date(invoiceDate.getTime());
	}

	
}
