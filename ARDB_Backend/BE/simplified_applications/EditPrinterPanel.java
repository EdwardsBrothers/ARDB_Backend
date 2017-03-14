package simplified_applications;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.Timer;

import be_gui.Edit;
import utilities.FileEmailer;

public class EditPrinterPanel extends JPanel {
	
	private JButton pingButton;
	private int editsPrinted;
	private String dateText;
	private Timer timer;
	private EditPrintListener epl;
	private SimpleDateFormat sdf;
		
	private String editFilePath;
	private File currentEditFile, tempEditFile;
	private long currentEditTimeStamp, tempEditTimeStamp;
	private ArrayList<Edit> currentEdits;
	private JLabel lblEditNumber, lblEditTime;
		
	private FileEmailer editSender;

	/**
	 * Create the panel.
	 */
	public EditPrinterPanel() {
		epl = new EditPrintListener();
		timer = new Timer(24943, epl);
		timer.start();
		
		editFilePath = "Z:\\AAPRINT-EDIT";
		currentEditFile = new File(editFilePath);
		currentEditTimeStamp = currentEditFile.lastModified();
		
		tempEditFile = new File(editFilePath);
		tempEditTimeStamp = tempEditFile.lastModified();
		
		editSender = new FileEmailer();
		
		setLayout(new BorderLayout(0, 0));
		sdf = new SimpleDateFormat("hh:mm");
		
		dateText = sdf.format(new Date());
		
		JPanel TextPanel = new JPanel();
		add(TextPanel, BorderLayout.NORTH);
		TextPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblEditsLastPrinted = new JLabel("Edits Last Printed:");
		lblEditsLastPrinted.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		TextPanel.add(lblEditsLastPrinted);
		
		lblEditTime = new JLabel(dateText);
		lblEditTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextPanel.add(lblEditTime);
		
		JSeparator separator = new JSeparator();
		TextPanel.add(separator);
		
		JLabel lblEditsPrinted = new JLabel("Edits Printed:");
		lblEditsPrinted.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		TextPanel.add(lblEditsPrinted);
		
		lblEditNumber = new JLabel("" + editsPrinted);
		lblEditNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextPanel.add(lblEditNumber);
		
		JPanel ButtonPanel = new JPanel();
		add(ButtonPanel, BorderLayout.SOUTH);
		
		pingButton = new JButton("Print Current");
		pingButton.addActionListener(epl);
		ButtonPanel.add(pingButton);
		
		editSender = new FileEmailer();
		
	}
	
	private void parseEdits(){
		ArrayList<Edit> all, credit, creditCard, changeOrder;
		all = new ArrayList<Edit>();
		credit = new ArrayList<Edit>();
		creditCard = new ArrayList<Edit>();
		changeOrder = new ArrayList<Edit>();

		try {
			Scanner scan = new Scanner(currentEditFile);
			scan.useDelimiter(new String(new char[] { 12 }));
			while (scan.hasNext()) {
				Edit e = new Edit(scan.next());
				all.add(e);
				if (e.inCredit()) {
					credit.add(e);
				}
				if (e.isCreditCard()) {
					creditCard.add(e);
				}
				if (e.isChange()) {
					changeOrder.add(e);
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Edit Init Read Fail");
		}

		// Create all files for Email
		int fileCount = 0;
		File allEdits, creditEdits, creditCardEdits, changeEdits;
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0: // All
				allEdits = createEditFile(all, "all.rtf");
				break;
			case 1: // Credit
				if (!credit.isEmpty()) {
					creditEdits = createEditFile(credit, "credit.rtf");
					fileCount++;
				}
				break;
			case 2: // Credit Card
				if (!creditCard.isEmpty()) {
					creditCardEdits = createEditFile(creditCard, "credit card.rtf");
					fileCount++;
				}
				break;
			case 3: // Change Order
				if (!changeOrder.isEmpty()) {
					changeEdits = createEditFile(changeOrder, "change order.rtf");
					fileCount++;
				}
				break;
			}
		}

		// Email Files and Text
		String subject = "Edits Sent to Credit " + sdf.format(new Date());
		String recipient = "ar@mariettadrapery.com";
		String ccRecipient = "jedwards@mariettadrapery.com";
		String[][] attachments = new String[fileCount][2];

		// Body Constructor
		fileCount = 0;
		String body = "";
		body = body + "Edits Sent to Credit:" + "\n";
		if (credit.isEmpty()) {
			body = body + "none" + "\n";
		} else {
			for (Edit e : credit) {
				String eout = e.getCustomerID() + "     " + e.getBillOne() + "        " + e.getCutNumber();
				body = body + eout + "\n";
			}
			attachments[fileCount][0] = "credit.rtf";
			attachments[fileCount][1] = "credit.rtf";
			fileCount++;
		}
		body = body + "\n";
		body = body + "Credit Card:" + "\n";
		if (creditCard.isEmpty()) {
			body = body + "none" + "\n";
		} else {
			for (Edit e : creditCard) {
				String eout = e.getCustomerID() + "     " + e.getBillOne() + "        " + e.getCutNumber();
				body = body + eout + "\n";
			}
			attachments[fileCount][0] = "credit card.rtf";
			attachments[fileCount][1] = "credit card.rtf";
			fileCount++;
		}
		body = body + "\n";
		body = body + "Change Orders:" + "\n";
		if (changeOrder.isEmpty()) {
			body = body + "none" + "\n";
		} else {
			for (Edit e : changeOrder) {
				String eout = e.getCustomerID() + "     " + e.getBillOne() + "        " + e.getCutNumber();
				body = body + eout + "\n";
			}
			attachments[fileCount][0] = "change order.rtf";
			attachments[fileCount][1] = "change order.rtf";
			fileCount++;
		}
		
		editSender.sendEmailWithAttachment(recipient, ccRecipient, subject, body, attachments);
		
		currentEdits = all;
	}
	
	private void printEdits(){
		
		PrinterJob pj = PrinterJob.getPrinterJob();
		PageFormat pf = pj.defaultPage();
		Paper paper = new Paper();
		paper.setSize(8.5*72, 11*72);
		double margin = 32;
		paper.setImageableArea(margin, margin, paper.getWidth() - (2 * margin), paper.getHeight() - (2 * margin));
				
		pf.setPaper(paper);
		for (Edit e : currentEdits){
			pj.setPrintable(e,  pf);
			try{
				pj.print();
			} catch (PrinterException ex){
				ex.printStackTrace();
			}
		}
	}
	
	
	private File createEditFile(ArrayList<Edit> editList, String name) {
		if (!editList.isEmpty()) {
			File editFile = new File(name);
			try {
				PrintWriter pw = new PrintWriter(editFile);
				for (Edit e : editList) {
					pw.write(e.getEditText() + (char) 12);
				}
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("look in EDIT HANDLER");
			}
			return editFile;
		}
		return null;
	}
	

	
	private class EditPrintListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if((e.getSource().equals(pingButton)) || checkNewEdits()){
				parseEdits();
				printEdits();
				lblEditTime.setText(sdf.format(new Date()));
				lblEditNumber.setText(currentEdits.size() + "");
			}
						
		}
		
		private boolean checkNewEdits(){
			File tempEditFile = new File(editFilePath);
			long tempTimeStamp = tempEditFile.lastModified();
			if(tempTimeStamp != currentEditTimeStamp){
				currentEditFile = tempEditFile;
				currentEditTimeStamp = tempTimeStamp;
				return true;
			}
			else{
				return false;
			}
		}
		
	}

}
