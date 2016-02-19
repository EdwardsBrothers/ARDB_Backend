package be_gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Invoice {
	
	private int cutNumber;
	private String invoiceDate;
	private String invoiceNumber;
	private String managementCode;

	public Invoice(String invoice){
		String currentLine = "";
		int lineNum = 1;
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
			
			lineNum++;
		}
		
		
		
		
	}
	
	
}
