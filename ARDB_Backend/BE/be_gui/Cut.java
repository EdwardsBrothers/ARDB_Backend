package be_gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Cut {
	
	private String cutDate, customerID, po, customerName, shippingMethod, shippingAddTwo, 
		shippingAddThree, shippingAddFour, headerLine, cutType;
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
			switch(lineNumber){
			
			case 2:
				//date, customerid, po	
				if(stringLength < 44){
					cutType = "N";
					break;
				}
				cutDate = line.substring(0, 12).trim();
				customerID = line.substring(26, 34).trim();
				po = line.substring(44).trim();
				
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
			String shadeHeader = " TYP    DESCRIPTION  QTY  IO  WDTH X LGTH  SYSTM CNTRL FABRI FASICA F-HGT F-CLR HEM H-CLR";
			scan.nextLine();
			line = scan.nextLine();
			while(scan.hasNextLine()){
				if(line.equals(shadeHeader)){
					line = scan.nextLine();
					Shade s = new Shade(line);
					line = scan.nextLine();
					while(!line.equals(shadeHeader) && scan.hasNextLine()){
						s.addDetail(line);
						line = scan.nextLine();
					}
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
	

}
