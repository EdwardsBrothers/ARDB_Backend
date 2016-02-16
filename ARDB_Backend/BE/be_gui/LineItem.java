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
	private char inOutMount;
	private char tilter;
	private char lift;
	private char valance;
	private char holdDown;
	private char stock;
	private char wallCeilMount;
	private String line;
	
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
			length = Float.parseFloat(item.substring(39,44).trim());
		} catch(NumberFormatException nfe){
			length = -1;
		}
		
		
		
		
	}

	private String fixNumber(double n) {
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
