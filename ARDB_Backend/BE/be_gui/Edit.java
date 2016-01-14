package be_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.text.JTextComponent;

public class Edit implements Printable{

	private int cutNumber;
	private Date orderDate;
	private String customerID, po, managementCo, contact;
	private String billOne, billTwo, billThree, billFour;
	private String shipOne, shipTwo, shipThree, shipFour;
	private float taxable, nonTaxable, tax, freight, labor, surcharge, misc, gross;
	private ArrayList<String> lineItems;
	private boolean inCredit, creditCard, orderChange;
	private String editText;
	
	private int cutoff = 52;

	public Edit(String editText) {
		this.editText = editText;
		lineItems = new ArrayList<String>();
		inCredit = false;
		creditCard = false;
		orderChange = false;
		Scanner scan = new Scanner(editText);
		orderDate = new Date();
		int lineNumber = 0;
		int dLength;

		while (scan.hasNext()) {
			String data = scan.nextLine();

			while (lineNumber < 11) {
				if (data.contains("****") && !data.contains("PREPAID")) {
					if (data.contains("ORDER CHANGED")) {
						orderChange = true;
					}
					if (scan.nextLine().contains("S E N D   T O   C R E D I T")) {
						inCredit = true;
					}
				}
				if (data.contains("CUST ID")) {
					lineNumber = 11;
					break;
				}
				data = scan.nextLine();
			}

			switch (lineNumber) {
			case 11:
				customerID = data.substring(10, 16);
				billOne = data.substring(21, 45);
				shipOne = data.substring(46);
				break;
			case 12:
				dLength = data.length();
				if (dLength > 47) {
					managementCo = data.substring(6, 9);
					billTwo = data.substring(21, 45);
					shipTwo = data.substring(46);
					break;
				}
				if (dLength > 21) {
					managementCo = data.substring(6, 9);
					billTwo = data.substring(21, dLength);
					shipTwo = "";
					break;
				}
				if (dLength > 6) {
					managementCo = data.substring(6);
					billTwo = "";
					shipTwo = "";
					break;
				}
				managementCo = "";
				billTwo = "";
				shipTwo = "";
				break;
			case 13:
				dLength = data.length();
				if (dLength > 47) {
					billThree = data.substring(21, 45);
					shipThree = data.substring(46);
					break;
				}
				if (dLength > 21) {
					billThree = data.substring(21);
					shipThree = "";
					break;
				}
				billThree = "";
				shipThree = "";
				break;
			case 14:
				dLength = data.length();
				if (dLength > 47) {
					billFour = data.substring(21, 45);
					shipFour = data.substring(46);
					break;
				}
				if (dLength > 21) {
					billFour = data.substring(21);
					shipFour = "";
					break;
				}
				billFour = "";
				shipFour = "";
				break;
			case 18:
				try {
					cutNumber = Integer.parseInt(data.substring(5, 11));
				} catch (NumberFormatException e) {
					cutNumber = 0;
				}
				break;
			case 19:
				contact = data.substring(9, 24);
				break;
			case 21:
				boolean running = true;
				while (running) {
					if (data.equals("")) {
						scan.nextLine();
						running = false;
					} else {
						lineItems.add(data);
						data = scan.nextLine();
					}
				}
				break;
			case 24:
				try {
					taxable = Float.parseFloat(data.substring(54, 65));
				} catch (Exception e) {
					taxable = 0;
				}
				break;
			case 25:
				try {
					nonTaxable = Float.parseFloat(data.substring(54, 65));
				} catch (Exception e) {
					nonTaxable = 0;
				}
				break;
			case 26:
				try {
					tax = Float.parseFloat(data.substring(54, 65));
				} catch (Exception e) {
					tax = 0;
				}
				break;
			case 27:
				try {
					freight = Float.parseFloat(data.substring(54, 65));
				} catch (Exception e) {
					freight = 0;
				}
				break;
			case 28:
				try {
					labor = Float.parseFloat(data.substring(54, 65));
				} catch (Exception e) {
					labor = 0;
				}
				break;
			case 29:
				try {
					surcharge = Float.parseFloat(data.substring(54, 65));
				} catch (Exception e) {
					surcharge = 0;
				}
				break;
			case 30:
				try {
					misc = Float.parseFloat(data.substring(54, 65));
				} catch (Exception e) {
					misc = 0;
				}
				break;
			case 31:
				try {
					gross = Float.parseFloat(data.substring(54, 65));
				} catch (Exception e) {
					gross = 0;
				}
				break;
			}
			if (data.contains("CREDIT CARD")) {
				creditCard = true;
			}
			lineNumber++;
		}
		scan.close();
	}

	public boolean inCredit() {
		return inCredit;
	}

	public boolean isCreditCard() {
		return creditCard;
	}

	public boolean isChange() {
		return orderChange;
	}

	public String getEditText() {
		return editText;
	}

	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////

	public int getCutNumber() {
		return cutNumber;
	}

	public void setCutNumber(int cutNumber) {
		this.cutNumber = cutNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getBillOne() {
		return billOne;
	}

	public void setBillOne(String billOne) {
		this.billOne = billOne;
	}

	@Override
	public int print(Graphics g, PageFormat pf, int i)
			throws PrinterException {
		String[] lines = editText.split("\n");
		
		Font font = new Font("Lucida Sans Typewriter", Font.PLAIN, 10);
		FontMetrics metrics = g.getFontMetrics(font);
		int lineHeight = metrics.getHeight();
		double pageHeight = pf.getImageableHeight();
		int linesPerPage = ((int)pageHeight/lineHeight);
		int numBreaks = (lines.length-1)/linesPerPage;
		int[] pageBreaks = new int[numBreaks];
		for(int b = 0; b < numBreaks; b++){
			pageBreaks[b] = (b+1)*linesPerPage;
		}
		
		if(i > pageBreaks.length){
			return NO_SUCH_PAGE;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pf.getImageableX(), pf.getImageableY());
		g2.setFont(font);
		g2.setPaint(Color.black);
		
		

		
		int y = 0;
		int start = (i == 0) ? 0 : pageBreaks[i-1];
		int end = (i == pageBreaks.length) ? lines.length : pageBreaks[i];
		
		for(int line = start;  line < end ; line++){
			y += lineHeight;
			g2.drawString(lines[line],0,y);
		}
		
		return PAGE_EXISTS;
	}




}
