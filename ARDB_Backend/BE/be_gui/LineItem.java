package be_gui;

import java.text.DecimalFormat;

public class LineItem {
	
		

		
	
	
	public LineItem(String l) {
		// TODO Auto-generated constructor stub
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
