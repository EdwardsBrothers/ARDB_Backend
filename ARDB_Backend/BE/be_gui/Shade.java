package be_gui;

import java.util.Scanner;

public class Shade {

	private String type, description, widthxlength, system, fabric, fasciaColor, hemColor, io, control, fascia, hem, comments, fasciaHeight;
	private int quantity, lineLength;
	private Scanner scan;
	
	
	public Shade(String line){
		
		comments = "";
		
		try{
			lineLength = line.length();
		
			type = line.substring(0,8).trim();
		
			description = line.substring(8,21).trim();
		
			quantity = Integer.parseInt(line.substring(21, 24).trim());
		
			io = line.substring(26,28).trim();
		
			widthxlength = line.substring(29, 44).trim();
		
			system = line.substring(44, 50).trim();
		
			control = line.substring(50,56).trim();
		
			fabric = line.substring(56, 62).trim();
		
			fascia = line.substring(62, 67).trim();
		
			fasciaHeight = line.substring(67,73).trim();
		
			fasciaColor = line.substring(73,80);
		
			hem = line.substring(80,83).trim();
		
			hemColor = line.substring(83).trim();
		}
		catch (Exception e){
				
		}
		
		switch(lineLength){
		case 82:
			hem = line.substring(81);			
			break;
				
		case 64:
			fascia = line.substring(63);
			break;
		}
	}
	
	public void addDetail(String detail){
		comments = comments + detail + "/n";
	}
		
}
	
