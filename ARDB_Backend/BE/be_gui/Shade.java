package be_gui;

import java.util.Scanner;

public class Shade {

	private String type, description, widthxlength, system, fabric, fasciaColor, hemColor, io, control, fascia, hem, comments, fasciaHeight;
	private int quantity, lineLength;
	private boolean sideChannel;
	private double width, length;
	private double fabCutOne, fabCutTwo, fabCutThree;
	private double matTube, matSIB, matTubeSize, matFascia, matDBB;
	private char matNotch;
	private Scanner scan;
	
	
	public Shade(String line){
		sideChannel = false;
		
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
		if(detail.contains("CHANNEL")){
			sideChannel = true;
		}
		comments = comments + detail.replace("*", "") + " ";
	}
	
	public void calculations(){
		int x = widthxlength.indexOf('X');
		width = Double.parseDouble(widthxlength.substring(0, x));
		width = Math.floor(width) + ((width % 1) * 5 / 4);
		length = Double.parseDouble(widthxlength.substring(x+1));
		length = Math.floor(length) + ((length % 1) *5 / 4);
		
		if(io.equalsIgnoreCase("I")){
			if(sideChannel){
				fabCutOne = length + 18;
				fabCutTwo = width + 3;
				fabCutThree = width - 0.125;
			}else{
				fabCutOne = length + 18;
				fabCutTwo = width + 3;
				fabCutThree = width - 0.75;
			}
		}
		if(io.equalsIgnoreCase("O")){
			if(sideChannel){
				fabCutOne = length + 18;
				fabCutTwo = width + 3;
				fabCutThree = width;
			}else{
				fabCutOne = length + 18;
				fabCutTwo = width + 3;
				fabCutThree = width - 0.625;
			}
		}
		
		if(io.equalsIgnoreCase("I")){
			if(hem.equalsIgnoreCase("Y")){
				if(fasciaHeight.equalsIgnoreCase("3")){ //IB, DBB, 3
					matTube = width - 1.5;
					matFascia = width - 0.125;
					matNotch = control.charAt(0);
					matDBB = matTube;
					matSIB = 0;
					matTubeSize = 40;
				}
				if(fasciaHeight.equalsIgnoreCase("3.75")){ //IB, DBB 3.75
					matTube = width - 1.5;
					matFascia = width - 0.125;
					matNotch = control.charAt(0);
					matDBB = matTube;
					matSIB = 0;
					matTubeSize = 40;
				}
				if(fasciaHeight.equalsIgnoreCase("4.25")){ //IB, DBB 4.25
					matTube = width - 1.5;
					matFascia = width - 0.125;
					matNotch = control.charAt(0);
					matDBB = matTube;
					matSIB = 0;
					matTubeSize = 40;
				}
				else{ //IB, DBB, N
					matTube = width - 1.5;
					matFascia = 0;
					matNotch = 'N';
					matDBB = matTube;
					matSIB = 0;
					matTubeSize = 40;
				}
						
			}
			if(hem.equalsIgnoreCase("N")){
				if(fasciaHeight.equalsIgnoreCase("3")){ //IB, SIB, 3
					matTube = width - 1.5;
					matFascia = width - 0.125;
					matNotch = control.charAt(0);
					matDBB = 0;
					matSIB = matTube - 2;
					matTubeSize = 40;
				}
				if(fasciaHeight.equalsIgnoreCase("3.75")){ //IB, SIB, 3.75
					matTube = width - 1.5;
					matFascia = width - 0.125;
					matNotch = control.charAt(0);
					matDBB = 0;
					matSIB = matTube - 2;
					matTubeSize = 40;
				}
				if(fasciaHeight.equalsIgnoreCase("4.25")){ // IB, SIB, 4.25
					matTube = width - 1.5;
					matFascia = width - 0.125;
					matNotch = control.charAt(0);
					matDBB = 0;
					matSIB = matTube - 2;
					matTubeSize = 40;
				}
				else{ // IB, SIB, N
					matTube = width - 1.5;
					matFascia = 0;
					matNotch = 'N';
					matDBB = 0;
					matSIB = matTube - 2;
					matTubeSize = 40;
				}
			}
		}
		
		if(io.equalsIgnoreCase("O")){
			if(hem.equalsIgnoreCase("Y")){
				if(fasciaHeight.equalsIgnoreCase("3")){ //OB, DBB, 3
					matTube = width - 1.375;
					matFascia = width;
					matNotch = control.charAt(0);
					matDBB = matTube;
					matSIB = 0;
					matTubeSize = 40;
				}
				if(fasciaHeight.equalsIgnoreCase("3.75")){ //OB, DBB, 3.75
					matTube = width - 1.375;
					matFascia = width;
					matNotch = control.charAt(0);
					matDBB = matTube;
					matSIB = 0;
					matTubeSize = 40;
				}
				if(fasciaHeight.equalsIgnoreCase("4.25")){ //OB, DBB, 4.25
					matTube = width - 1.375;
					matFascia = width;
					matNotch = control.charAt(0);
					matDBB = matTube;
					matSIB = 0;
					matTubeSize = 40;
				}
				else{ //OB, DBB, N
					matTube = width - 1.375;
					matFascia = 0;
					matNotch = 'N';
					matDBB = matTube;
					matSIB = 0;
					matTubeSize = 40;
				}
						
			}
			if(hem.equalsIgnoreCase("N")){
				if(fasciaHeight.equalsIgnoreCase("3")){ //OB, SIB, 3
					matTube = width - 1.375;
					matFascia = width;
					matNotch = control.charAt(0);
					matDBB = 0;
					matSIB = matTube - 2;
					matTubeSize = 40;
				}
				if(fasciaHeight.equalsIgnoreCase("3.75")){ //OB, SIB, 3.75
					matTube = width - 1.375;
					matFascia = width;
					matNotch = control.charAt(0);
					matDBB = 0;
					matSIB = matTube - 2;
					matTubeSize = 40;
				}
				if(fasciaHeight.equalsIgnoreCase("4.25")){ //OB, SIB, 4.25
					matTube = width - 1.375;
					matFascia = width;
					matNotch = control.charAt(0);
					matDBB = 0;
					matSIB = matTube - 2;
					matTubeSize = 40;
				}
				else{ //OB, SIB, N
					matTube = width - 1.375;
					matFascia = 0;
					matNotch = 'N';
					matDBB = 0;
					matSIB = matTube - 2;
					matTubeSize = 40;
				}
			}
		}

	}
	
	

	public boolean isSideChannel() {
		return sideChannel;
	}

	public void setSideChannel(boolean sideChannel) {
		this.sideChannel = sideChannel;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getFabCutOne() {
		return fabCutOne;
	}

	public void setFabCutOne(double fabCutOne) {
		this.fabCutOne = fabCutOne;
	}

	public double getFabCutTwo() {
		return fabCutTwo;
	}

	public void setFabCutTwo(double fabCutTwo) {
		this.fabCutTwo = fabCutTwo;
	}

	public double getFabCutThree() {
		return fabCutThree;
	}

	public void setFabCutThree(double fabCutThree) {
		this.fabCutThree = fabCutThree;
	}

	public double getMatTube() {
		return matTube;
	}

	public void setMatTube(double matTube) {
		this.matTube = matTube;
	}

	public double getMatSIB() {
		return matSIB;
	}

	public void setMatSIB(double matSIB) {
		this.matSIB = matSIB;
	}

	public double getMatTubeSize() {
		return matTubeSize;
	}

	public void setMatTubeSize(double matTubeSize) {
		this.matTubeSize = matTubeSize;
	}

	public double getMatFascia() {
		return matFascia;
	}

	public void setMatFascia(double matFascia) {
		this.matFascia = matFascia;
	}

	public double getMatDBB() {
		return matDBB;
	}

	public void setMatDBB(double matDBB) {
		this.matDBB = matDBB;
	}

	public char getMatNotch() {
		return matNotch;
	}

	public void setMatNotch(char matNotch) {
		this.matNotch = matNotch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWidthxlength() {
		return widthxlength;
	}

	public void setWidthxlength(String widthxlength) {
		this.widthxlength = widthxlength;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getFabric() {
		return fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

	public String getFasciaColor() {
		return fasciaColor;
	}

	public void setFasciaColor(String fasciaColor) {
		this.fasciaColor = fasciaColor;
	}

	public String getHemColor() {
		return hemColor;
	}

	public void setHemColor(String hemColor) {
		this.hemColor = hemColor;
	}

	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getFascia() {
		return fascia;
	}

	public void setFascia(String fascia) {
		this.fascia = fascia;
	}

	public String getHem() {
		return hem;
	}

	public void setHem(String hem) {
		this.hem = hem;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFasciaHeight() {
		return fasciaHeight;
	}

	public void setFasciaHeight(String fasciaHeight) {
		this.fasciaHeight = fasciaHeight;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
		
}
	
