package be_gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Invoice {
	
	private int cutNumber;
	private String invoiceDate;
	private String invoiceNumber;
	private String managementCode;
	private String managementCompanyName;
	private boolean electronic;

	public Invoice(String invoice){
		String currentLine = "";
		int lineNum = 1;
		electronic = false;
		boolean running = true;
		
		Scanner scan = new Scanner(invoice);
		while(running){
			currentLine = scan.nextLine();
			if(currentLine.contains("$") || currentLine.contains("%")){
				lineNum--;
			}
			
			if(lineNum == 9){//mgt code
				managementCode = currentLine.trim();
			}
			if(lineNum == 11){//date & invoice number
				invoiceDate = currentLine.substring(33,42).trim();
				//convert to date
				invoiceNumber = currentLine.substring(77,84);
			}
			
			if(currentLine.contains("NONTAXABLE")){
				currentLine = scan.nextLine();
				cutNumber = Integer.parseInt(currentLine.substring(3,9));
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

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	
}
