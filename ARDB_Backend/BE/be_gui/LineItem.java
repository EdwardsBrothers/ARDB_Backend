package be_gui;

import java.text.DecimalFormat;

public class LineItem {
	
	private int depCode;
	private String prodCode;
	private String procommText;
	private String fullDescription;
	private float width, length;
	private String colorCode;
	private String colorName;
	private char info1, info2, info3, info4, info5, info6, infoSt;
	private char inOutMount;
	private char tilter;
	private char lift;
	private char valance;
	private char holdDown;
	private char stock;
	private char wallCeilMount;
	private int quantity;
	private float unitPrice;
	private float extendedPrice;
	private String line;
	String sku;
	
	
	public LineItem(String item) {
		line = item;
		
		try{
			depCode = Integer.parseInt(item.substring(0,2));
		} catch(NumberFormatException nfe){
			depCode = -1;
		}
		
		try{
			prodCode = item.substring(2,11).trim();
		} catch(StringIndexOutOfBoundsException siob){
			prodCode = null;
		}
		
		try{
			procommText = item.substring(12,30);
		} catch(StringIndexOutOfBoundsException siob){
			procommText = "******";
		}
		
		try{
			width = Float.parseFloat(item.substring(32,38).trim());
		} catch(NumberFormatException nfe){
			width = -1;
		}
		
		
		try{
			length = Float.parseFloat(item.substring(39,45).trim());
		} catch(NumberFormatException nfe){
			length = -1;
		}
		
		try{ 
			colorCode = item.substring(45,50).trim();
			colorName = getColor(colorCode);
		} catch(StringIndexOutOfBoundsException siob){
			colorCode = "";
			colorName = "";
		}
		
		try{
			info1 = item.charAt(51);
		} catch(Exception e){
			info1 = '*';
		}
		
		try{
			info2 = item.charAt(53);
		} catch(Exception e){
			info2 = '*';
		}
		
		try{
			info3 = item.charAt(55);
		} catch(Exception e){
			info3 = '*';
		}
		
		try{
			info4 = item.charAt(57);
		} catch(Exception e){
			info4 = '*';
		}
		
		try{
			info5 = item.charAt(59);
		} catch(Exception e){
			info5 = '*';
		}
		
		try{
			info6 = item.charAt(61);
		} catch(Exception e){
			info6 = '*';
		}
		
		try{
			infoSt = item.charAt(65);
		} catch(Exception e){
			infoSt= '*';
		}
		
		try{
			quantity = Integer.parseInt(item.substring(67,72).trim());
		} catch(NumberFormatException nfe){
			quantity = 0;
		}
		
		try{
			unitPrice = Float.parseFloat(item.substring(71,78).trim());
		} catch(NumberFormatException nfe){
			unitPrice = 0;
		}
		
		try{
			extendedPrice = Float.parseFloat(item.substring(78).trim());
		} catch(NumberFormatException nfe){
			extendedPrice = 0;
		}
		
		createSKU();
	}

	private void createSKU() {
		
		//White Aluminum
		if(prodCode.equals("BCW")){
			if(depCode == 30){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " Aluminum Stock Mini Blinds";
				colorName = "White";
				sku = "BCW" + fixNumber(width) + "X" + fixNumber(length) + "S";
			}
			else if(depCode == 31){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " Aluminum Mini Blinds Custom";
				colorName = "White";
				sku = "BCW" + fixNumber(width) + "X" + fixNumber(length) + "C";
			}
			else {
				unknownProduct();
			}
		}
		
		//Alabaster Aluminum
		else if(prodCode.equals("BCV")){
			if(depCode == 30){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " Aluminum Stock Mini Blinds";
				colorName = "Alabaster";
				sku = "BCV" + fixNumber(width) + "X" + fixNumber(length) + "S";
			}
			else if(depCode == 31){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " Aluminum Mini Blinds Custom";
				colorName = "Alabaster";
				sku = "BCV" + fixNumber(width) + "X" + fixNumber(length) + "C";
			}
			else {
				unknownProduct();
			}
		}
		
		//Fauxwood
		else if(prodCode.equals("B-2W")){
			if(depCode == 30){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " 2\" Faux Wood Blinds";
				colorName = "White";
				sku = "B-2W" + fixNumber(width) + "X" + fixNumber(length) + "S";
			}
			else if(depCode == 31){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " 2\" Faux Wood Blinds";
				colorName = "White";
				sku = "B-2W" + fixNumber(width) + "X" + fixNumber(length) + "C";
			}
			else {
				unknownProduct();
			}
		}
		
		//2in Flex
		else if(prodCode.equals("B-2F")){
			if(depCode == 30){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " 2\" FlexPlus Blinds";
				colorName = "White";
				sku = "B-2F" + fixNumber(width) + "X" + fixNumber(length) + "S";
			}
			else if(depCode == 31){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " 2\" FlexPlus Blinds";
				colorName = "White";
				sku = "B-2F" + fixNumber(width) + "X" + fixNumber(length) + "C";
			}
			else {
				unknownProduct();
			}
		}
		
		//White Vinyl
		else if(prodCode.equals("B-WN")){
			if(depCode == 30){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " Vinyl Stock Mini Blinds";
				colorName = "White";
				sku = "B-WN" + fixNumber(width) + "X" + fixNumber(length) + "S";
			}
			else if(depCode == 31){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " Vinyl Mini Blinds Custom";
				colorName = "White";
				sku = "B-WN" + fixNumber(width) + "X" + fixNumber(length) + "C";
			}
			else {
				unknownProduct();
			}
		}
		
		//Alabaster Vinyl
		else if(prodCode.equals("B-AN")){
			if(depCode == 30){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " Vinyl Stock Mini Blinds";
				colorName = "Alabaster";
				sku = "B-AN" + fixNumber(width) + "X" + fixNumber(length) + "S";
			}
			else if(depCode == 31){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " Vinyl Mini Blinds Custom";
				colorName = "Alabaster";
				sku = "B-AN" + fixNumber(width) + "X" + fixNumber(length) + "C";
			}
			else {
				unknownProduct();
			}
		}
		
		//Alabaster Flex
		else if(prodCode.equals("B-FN")){
			if(depCode == 30){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " FlexPlus Stock Mini Blinds";
				colorName = "Alabaster";
				sku = "B-FN" + fixNumber(width) + "X" + fixNumber(length) + "S";
			}
			else if(depCode == 31){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " FlexPlus Mini Blinds Custom";
				colorName = "Alabaster";
				sku = "B-FN" + fixNumber(width) + "X" + fixNumber(length) + "C";
			}
			else {
				unknownProduct();
			}
		}
		
		//White Flex
		else if(prodCode.equals("B-FW")){
			if(depCode == 30){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " FlexPlus Stock Mini Blinds";
				colorName = "White";
				sku = "B-FW" + fixNumber(width) + "X" + fixNumber(length) + "S";
			}
			else if(depCode == 31){
				fullDescription = fixNumber(width) + "x" + fixNumber(length) + " FlexPlus Mini Blinds Custom";
				colorName = "White";
				sku = "B-FW" + fixNumber(width) + "X" + fixNumber(length) + "C";
			}
			else {
				unknownProduct();
			}
		}
		
		//Econoblinds
		else if(prodCode.equals("BEB")){
			fullDescription = "Econoblinds";
		}
		
		//Classic Blinds
		else if(prodCode.equals("BCL")){
			fullDescription = "Classic Blinds";
		}
		
		//ValuePlus Verticals
		else if(prodCode.equals("BV-P")){
			fullDescription = "ValuePlus Verticals";
			//
		}
		
		//ValuePlus Vertical Louver
		else if(prodCode.equals("BV-PL")){
			
		}
		
		//ValuePlus Vertical HeadRail
		else if(prodCode.equals("BV-PHR")){
			
		}
		
	}

	private String getColor(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	private String fixNumber(float n) {
		DecimalFormat df = new DecimalFormat("###");
		double r = n;
		if(n % 1 != 0){
			double d = n % 1;
			double m = Math.floor(n);
			r = m + (d*5./4);
		}
		return r + "";
	}

	private void unknownProduct() {
		
	}
	
	public int getNumCode(){
		return 0;
	}

	/*
	  public String generateSQLQuery(){
	 
		String qry = "";
		
		qry = qry + "'" + productStyleName + "'" + ", "+ "'"  + sku + "'" + ", " + quantity + ", " + "'" + uom + "'" + ", " + unitPrice + ", " + extendedPrice + ")";

		return qry;
	}
	*/

}
