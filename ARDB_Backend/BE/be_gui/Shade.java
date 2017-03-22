package be_gui;

import java.util.Scanner;

public class Shade {

	private String type, description, widthxlength, system, fabric, fasciaColor, hemColor, io, control, fascia, hem, comments;
	private int quantity;
	private float fasciaHeight;
	private Scanner scan;
	
	
	public Shade(String line){
			
		int lineLength = line.length();
		
		if(lineLength > 7){
			type = line.substring(0,7).trim();
		}
		
		if(lineLength > 21){
			description = line.substring(8,21);
		}
		
		if(lineLength > 26){
			quantity = Integer.parseInt(line.substring(22, 26).trim());
		}
		
		if(lineLength > 28){
			io = line.substring(27,28).trim();
		}
		
		if(lineLength > 44){
			widthxlength = line.substring(29, 44).trim();
		}
		
		if(lineLength > 50){
			system = line.substring(45, 50).trim();
		}
		
		if(lineLength > 56){
			control = line.substring(51,56).trim();
		}
		
		if(lineLength > 62){
			fabric = line.substring(57, 62).trim();
		}
		
		if(lineLength > 67){
			fascia = line.substring(63, 67).trim();
		}
		
		if(lineLength > 73){
			fasciaHeight = Float.parseFloat(line.substring(68,73).trim());
		}
		
		if(lineLength > 80){
			fasciaColor = line.substring(74,80);
		}
		
		if(lineLength > 85){
			hem = line.substring(81,85).trim();
		}
		
		if(lineLength > 86){
			hemColor = line.substring(86);
		}

		
	}
	
}
