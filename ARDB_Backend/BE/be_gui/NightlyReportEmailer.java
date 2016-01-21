package be_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Timer;

import utilities.FileEmailer;

class NightlyReportEmailer extends ServicePanelInd {
	/*
	 * 
	 */

	private FileEmailer creditReportFileEmailer;
	private String creditReportPath, creditListPath;
	private File currentCreditReport, currentCreditList;
	private long currentCreditReportTimeStamp, currentCreditListTimeStamp;
	private SimpleDateFormat sdf, statusSdf;
	private CreditReportListener crl;

	public NightlyReportEmailer() {
		super("Credit Report Emailer");
		sdf = new SimpleDateFormat("MM-dd-yy");
		statusSdf = new SimpleDateFormat("MM-dd-yy kk:mm");
		creditReportFileEmailer = new FileEmailer();
		crl = new CreditReportListener();
		timer = new Timer(19854, crl);

		creditReportPath = "Z:\\AAA-LATECR";
		creditListPath = "Z:\\AAA-CHKCRD";
		currentCreditReport = new File(creditReportPath);
		currentCreditList = new File(creditListPath);
		currentCreditReportTimeStamp = currentCreditReport.lastModified();
		currentCreditListTimeStamp = currentCreditList.lastModified();

		timer.start();
		lblStatus.setText("Running");
		lblLastUpdate.setText(statusSdf.format(new Date()));

	}

	private class CreditReportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File tempCreditReport = new File(creditReportPath);
			long tempTimeStamp = tempCreditReport.lastModified();
			if (tempTimeStamp != currentCreditReportTimeStamp) {
				currentCreditReport = new File(creditReportPath);
				currentCreditList = new File(creditListPath);
				currentCreditReportTimeStamp = currentCreditReport.lastModified();
				currentCreditListTimeStamp = currentCreditList.lastModified();
				String[][] attachmentFiles = new String[2][2];
				attachmentFiles[0][0] = creditReportPath;
				attachmentFiles[0][1] = "Orders Still in Credit " + sdf.format(new Date()) + ".txt";
				attachmentFiles[1][0] = creditListPath;
				attachmentFiles[1][1] = "Credit List " + sdf.format(new Date()) + ".txt";
				creditReportFileEmailer.sendEmailWithAttachment("ar@mariettadrapery.com",
						"Hold List " + sdf.format(new Date()), "", attachmentFiles); // UPDATE!

				lblLastUpdate.setText(statusSdf.format(new Date()));
				
				
				//Send new/inactive
				creditReportFileEmailer.sendEmailWithAttachment("igarcia@mariettadrapery.com", "New and Inactives for "+ sdf.format(new Date()), "Automated email from AR",
						"Z:\\PRINT-DYINAC", "NEW AND INACTIVES " + sdf.format(new Date()) + ".txt");
				
				creditReportFileEmailer.sendEmailWithAttachment("tpaige@mariettadrapery.com", "New and Inactives for "+ sdf.format(new Date()), "Automated email from AR",
						"Z:\\PRINT-DYINAC", "NEW AND INACTIVES " + sdf.format(new Date()) + ".txt");
			}

		}

	}

}
