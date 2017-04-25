package be_gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Cut {
	
	private String cutDate, customerID, po, customerName, shippingMethod, shippingAddTwo, 
		shippingAddThree, shippingAddFour, headerLine, cutType, notes;
	private int cutNumber;
	private Scanner scan;
	private ArrayList<Shade> shades;
	
	
	public Cut(String cutString){
		scan = new Scanner(cutString);
		shades = new ArrayList<Shade>();
		
		if(cutString.contains("SOLAR")){
			cutType = "shade";
		} else {
			cutType = "misc";
		}
		parseLines();

	}
	


	private void parseLines(){
			
		int lineNumber = 0;
		int stringLength = 0;
		String line = "";
		
		//HEADER
		while(scan.hasNextLine() && lineNumber < 9){
			line = scan.nextLine();
			stringLength = line.length();
			if(line.contains("* WEST *")){
				line = scan.nextLine();
				stringLength = line.length();
			}
			
			switch(lineNumber){
			
			case 2:
				//date, customerid, po	
				if(stringLength < 26){
					cutType = "N";
					break;
				}
				cutDate = line.substring(0, 12).trim();
				customerID = line.substring(26, 32).trim();
				if(stringLength > 43){
					po = line.substring(44).trim();
				}

				
				break;
			
			case 4:
				//customername
				if(stringLength < 53){
					cutType = "N";
					break;
				}
				customerName = line.substring(2,34).trim();
				shippingMethod = line.substring(53).trim();				
				break;
								
			case 6:
				//addresslinetwo
				if(stringLength < 3){
					cutType = "N";
					break;
				}
				shippingAddTwo = line.trim();
				break;
				
			case 7:
				//addresslinethree
				if(stringLength < 3){
					cutType = "N";
					break;
				}
				shippingAddThree = line.trim();
				break;
				
			case 8:
				//addresslinefour, cutnumber
				stringLength = line.length();
				if(stringLength < 82){
					cutType = "N";
					break;
				}
				shippingAddFour = line.substring(2, 34);
				cutNumber = Integer.parseInt(line.substring(84).trim());			
				break;
			}	
			lineNumber++;
		}
		
		if(cutType.equals("shade")){
			lineNumber = 0;
			boolean running = true;
			String shadeHeader = " TYP    DESCRIPTION  QTY  IO  WDTH X LGTH  SYSTM CNTRL FABRI FASICA F-HGT F-CLR HEM H-CLR";
			scan.nextLine();
			line = scan.nextLine();
			while(scan.hasNextLine() && running){
				if(line.equals(shadeHeader)){
					line = scan.nextLine();
				}
				if(line.contains("SOLAR")){
					Shade s = new Shade(line);
					line = scan.nextLine();
					while(!line.contains("SOLAR") && scan.hasNextLine() && running){
						if(line.equals("")){
							running = false;					
						} else{
							s.addDetail(line.trim());
							line = scan.nextLine();
						} //Fix stuff
					}
					s.calculations();
					shades.add(s);
				}
			}
		}
	}
	
	public boolean isCut() {
		if(cutType.equals("N")){
			return false;
		}
		return true;
	}
	
	public boolean isShadeCut(){
		if(cutType.equals("shade")){
			return true;
		}
		return false;
	}

	public String getCutDate() {
		return cutDate;
	}

	public void setCutDate(String cutDate) {
		this.cutDate = cutDate;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getShippingAddTwo() {
		return shippingAddTwo;
	}

	public void setShippingAddTwo(String shippingAddTwo) {
		this.shippingAddTwo = shippingAddTwo;
	}

	public String getShippingAddThree() {
		return shippingAddThree;
	}

	public void setShippingAddThree(String shippingAddThree) {
		this.shippingAddThree = shippingAddThree;
	}

	public String getShippingAddFour() {
		return shippingAddFour;
	}

	public void setShippingAddFour(String shippingAddFour) {
		this.shippingAddFour = shippingAddFour;
	}

	public String getHeaderLine() {
		return headerLine;
	}

	public void setHeaderLine(String headerLine) {
		this.headerLine = headerLine;
	}

	public String getCutType() {
		return cutType;
	}

	public void setCutType(String cutType) {
		this.cutType = cutType;
	}

	public int getCutNumber() {
		return cutNumber;
	}

	public void setCutNumber(int cutNumber) {
		this.cutNumber = cutNumber;
	}

	public ArrayList<Shade> getShades() {
		return shades;
	}

	public void setShades(ArrayList<Shade> shades) {
		this.shades = shades;
	}
	
	
	

}
