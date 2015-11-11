package be_gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Invoice {
	
	private Date invoiceDate;
	private String customerID, billOne, billTwo, billThree, billFour, shipOne, shipTwo, shipThree, shipFour;
	private String phone, dateShipped, dateOrdered, salesNumber, salesName, po, managementCo, adBlock;
	private char pricing;
	private String invoiceNumber, invoiceText;
	private int cutNumber, pageNumber;
	private float nonTaxable, taxable, tax, handling, labor, total;
	private ArrayList<String> lineItems;
	boolean creditMemo;
	private boolean sendElectronically;
	
	
	public Invoice(String invoice){
		invoiceText = invoice;
		Scanner scan = new Scanner(invoice);
		invoiceDate = new Date();
		lineItems = new ArrayList<String>();
		creditMemo = false;
		int lineNumber = 1;
		
		while(scan.hasNextLine()){
			String data = scan.nextLine();
			switch(lineNumber){
				case 3:
					shipOne = data.substring(10).trim();
					break;
				case 4:
					customerID = data.substring(40,46);
					data.replace(customerID,"");
					shipTwo = data.substring(10).trim();
					break;
				case 5:
					if(data.length() > 90){
						shipThree = data.substring(10,41).trim();
					} else{
						shipThree = data.substring(10);
					}
					break;
				case 6:
					if(data.contains("$")){
						shipFour = data.substring(10).trim();
						billOne = scan.nextLine().substring(10).trim();
					} else{
						shipFour = "";
						billOne = data.substring(10).trim();
					}
					break;
				case 7:
					billTwo = data.substring(10).trim();
					break;
				case 8:
					billThree = data.substring(10).trim();
					break;
				case 9:
					if(data.contains("%")){
						billFour = data.substring(10).trim();
						data = scan.nextLine();
					}
					else{
						billFour = "";
					}
					
					if(data.contains("CREDIT")){
						creditMemo = true;
					}
					if(data.contains("SHIP TO")){
						data = scan.nextLine();
					}
					phone = data.trim();
					break;
				case 11:
					salesName = data.trim();
					break;
				case 12:
					dateShipped = data.substring(1,11).trim();
					salesNumber = data.substring(15,19).trim();
					pricing = data.charAt(19);
					dateOrdered = data.substring(32,32).trim();
					managementCo = data.substring(42,64).trim();
					invoiceNumber = data.substring(75).trim();
					break;
				case 13:
					while(!data.contains("*******************************************************")){
						lineItems.add(data);
						if(scan.hasNextLine()){
							data = scan.nextLine();
						}
						else{
							break;
						}
					}
					if(data.contains("*******************************************************")){
						adBlock = data.trim();
						break;
					}
					else{
						break;
					}
					
				case 14:
					adBlock = adBlock + "\n" + data.trim();
					if(data.contains("S E N D   E L E C T R O N I C A L L Y")){
						sendElectronically = true;
					}
					break;
				case 15:
					adBlock = adBlock + "\n" + data.trim();
					break;
				case 16:
					adBlock = adBlock + "\n" + data.trim();
					break;
				case 17:
					adBlock = adBlock + "\n" + data.trim();
					break;
				case 18:
					adBlock = adBlock + "\n" + data.trim();
					break;
				case 20:
					try{
						cutNumber = Integer.parseInt(data.substring(1,8).trim());
						nonTaxable = Float.parseFloat(data.substring(8,22).trim());
						taxable = Float.parseFloat(data.substring(22,34).trim());
						tax = Float.parseFloat(data.substring(34,44).trim());
						handling = Float.parseFloat(data.substring(44,54).trim());
						labor = Float.parseFloat(data.substring(54,64).trim());
						total = Float.parseFloat(data.substring(64).trim());
					} catch(NumberFormatException nfe){
						System.out.println("Look at invoice dollar amounts");
						nfe.printStackTrace();
					}
					break;
				}
			lineNumber++;		
		}
	}

	public boolean isElectronic() {
		return sendElectronically;
	}

}
