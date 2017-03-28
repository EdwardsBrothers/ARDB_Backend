package Archive;

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
	private int quantity;
	private float unitPrice;
	private float extendedPrice;
	private String line;
	private String sku;
	private String uom;
	
	private char io, mt, val, stk, ctl, ch, tlt, lft, hd;

	
	public LineItem(String item) {
		line = item;
		
		try{
			depCode = Integer.parseInt(item.substring(0,2));
		} catch(NumberFormatException nfe){
			depCode = -1;
		} catch(StringIndexOutOfBoundsException siob){
			depCode = -1;
		}
		
		try{
			prodCode = item.substring(2,10).trim();
		} catch(StringIndexOutOfBoundsException siob){
			prodCode = "";
		}
		
		try{
			procommText = item.substring(10,30);
		} catch(StringIndexOutOfBoundsException siob){
			procommText = "";
		}
		
		try{
			width = Float.parseFloat(item.substring(30,37).trim());
		} catch(NumberFormatException nfe){
			width = -1;
		} catch(StringIndexOutOfBoundsException siob){
			width = -1;
		}
		
		try{
			length = Float.parseFloat(item.substring(37,43).trim());
		} catch(NumberFormatException nfe){
			length = -1;
		} catch(StringIndexOutOfBoundsException siob){
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
			infoSt = item.charAt(63);
		} catch(Exception e){
			infoSt= '*';
		}
		
		try{
			quantity = Integer.parseInt(item.substring(65,69).trim());
		} catch(NumberFormatException nfe){
			quantity = 0;
		} catch(StringIndexOutOfBoundsException siob){
			quantity = 0;
		}
		
		try{
			unitPrice = Float.parseFloat(item.substring(70,76).trim());
		} catch(NumberFormatException nfe){
			unitPrice = 0;
		} catch(StringIndexOutOfBoundsException siob){
			unitPrice = 0;
		}
		
		try{
			extendedPrice = Float.parseFloat(item.substring(76).trim());
		} catch(NumberFormatException nfe){
			extendedPrice = 0;
		} catch(StringIndexOutOfBoundsException siob){
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
			sku = "Enter Manually";
		}
		
		//Classic Blinds
		else if(prodCode.equals("BCL")){
			fullDescription = "Classic Blinds";
			sku = "Enter Manually";
		}
		
		//ValuePlus Verticals
		else if(prodCode.equals("BV-P")){
			io = info1;
			mt = info2;
			val = info6;
			
			if(colorCode.equals("001")){ //White
				if(val == 'C'){ //Channel Panel
					fullDescription = "Value Plus Channel Panel Valance " + fixNumber(width);
					sku = "BV-P" + fixNumber(width) + "CPW";
				}
				else if(val == 'D'){ //Dust Cover
					fullDescription = "Value Plus Dust Cover Valence " + fixNumber(width);
					sku = "BV-P" + fixNumber(width) + "DCW";
				}
				else{ //None
					fullDescription = "Value Plus Verticals";
					sku = "BV-P" + fixNumber(width) + "X" + fixNumber(length) + "W";
				}
			}
			else if(colorCode.equals("002")){ //OffWhite
				if(val == 'C'){ //Channel Panel
					fullDescription = "Value Plus Channel Panel Valance " + fixNumber(width);
					sku = "BV-P" + fixNumber(width) + "CPOF";
				}
				else if(val == 'D'){ //Dust Cover
					fullDescription = "Value Plus Dust Cover Valence " + fixNumber(width);
					sku = "BV-P" + width + "DOF";
				}
				else{ //None
					fullDescription = "Value Plus Verticals";
					sku = "BV-P" + width + "X" + length + "OF";
				}
			}
			else{
				unknownProduct();
			}
			
			
		}
		
		//ValuePlus Vertical HeadRail
		else if(prodCode.equals("BV-PHR")){
			io = info1;
			mt = info2;
			val = info6;
			
			if(colorCode.equals("001")){ //White
				if(val == 'C'){ //Channel Panel
					fullDescription = "Value Plus Channel Panel Valence";
					sku = "BV-PHR" + fixNumber(width) + "CPW";
				}
				else if(val == 'D'){ //Dust Cover
					fullDescription = "Value Plus Dust Cover Valence";
					sku = "BV-PHR" + fixNumber(width) + "DCW";
				}
				else{ //None
					fullDescription = "Value Plus Verticals";
					sku = "BV-PHR" + fixNumber(width) + "X" + fixNumber(length) + "W";
				}
			}
			else if(colorCode.equals("002")){
				if(val == 'C'){ //Channel Panel
					fullDescription = "Value Plus Channel Panel Valence";
					sku = "BV-PHR" + fixNumber(width) + "CPOF";
				}
				else if(val == 'D'){ //Dust Cover
					fullDescription = "Value Plus Dust Cover Valence";
					sku = "BV-PHR" + fixNumber(width) + "DCOF";
				}
				else{ //None
					fullDescription = "Value Plus Verticals";
					sku = "BV-PHR" + fixNumber(width) + "X" + fixNumber(length) + "OF";
				}
			}
		}
		
		//ValuePlus Vertical Louver
		else if(prodCode.equals("BV-PL")){
			if(colorCode.equals("001")){
				fullDescription = "Value Plus Vertical Louvers";
				sku = "BV-PL" + fixNumber(length) + "W";
			}
			else if(colorCode.equals("002")){
				fullDescription = "Value Plus Vertical Louvers";
				sku = "BV-PL" + fixNumber(length) + "OF";
			}
			else{
				unknownProduct();
			}
		}
		
		else{
			unknownProduct();
		}
		
		uom = "ea";
	}

	private String getColor(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	private String fixNumber(float n) {
		DecimalFormat df = new DecimalFormat("###.##");
		double r = n;
		if(n % 1 != 0){
			double d = n % 1;
			double m = Math.floor(n);
			r = m + (d*5./4);
		}
		return df.format(r) + "";
	}

	private void unknownProduct() {
		sku = prodCode + "-ERROR";
		fullDescription = "Look up SKU";
	}
	
	public int getNumCode(){
		return depCode;
	}

	
	  public String generateSQLQuery(){
	 
		String qry = "";
		
		qry = qry + "'" + fullDescription + "'" + ", "+ "'"  + sku + "'" + ", " + quantity + ", " + "'" + uom + "'" + ", " + unitPrice + ", " + extendedPrice + ")";

		return qry;
	}
	

}
