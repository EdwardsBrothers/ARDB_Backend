package simplified_applications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.Timer;

import utilities.FileEmailer;
import javax.swing.JLabel;

public class NightlyReportPanel extends JPanel {

	private FileEmailer reportFileEmailer;
	private String creditReportPath, creditListPath;
	private File currentCreditReport, currentCreditList;
	private long currentCreditReportTimeStamp, currentCreditListTimeStamp;
	private SimpleDateFormat sdf, statusSdf;
	private JLabel lblTime;
	private ReportListener rl;
	
	private String currentEditCheckPath;
	private File currentEditCheckFile;
	private long currentEditCheckTimeStamp;
	private Timer timer;
	
	/**
	 * Create the panel.
	 */
	public NightlyReportPanel() {
		
		JLabel lblNightlyListRun = new JLabel("Nightly List Run: ");
		add(lblNightlyListRun);
		
	
		
		sdf = new SimpleDateFormat("MM-dd-yy");
		statusSdf = new SimpleDateFormat("MM-dd-yy kk:mm");
		reportFileEmailer = new FileEmailer();
		rl = new ReportListener();
		timer = new Timer(19854, rl);
		lblTime = new JLabel(sdf.format(new Date()));
		add(lblTime);

		creditReportPath = "Z:\\AAA-LATECR";
		creditListPath = "Z:\\AAA-CHKCRD";
		currentCreditReport = new File(creditReportPath);
		currentCreditList = new File(creditListPath);
		currentCreditReportTimeStamp = currentCreditReport.lastModified();
		currentCreditListTimeStamp = currentCreditList.lastModified();
		
		currentEditCheckPath = "Z:\\PRINT-CHKMST";
		currentEditCheckFile = new File(currentEditCheckPath);
		currentEditCheckTimeStamp = currentEditCheckFile.lastModified();

		timer.start();
		
	}
	
	private void emailCreditHold(){
		
		currentCreditReport = new File(creditReportPath);
		currentCreditList = new File(creditListPath);
		currentCreditReportTimeStamp = currentCreditReport.lastModified();
		currentCreditListTimeStamp = currentCreditList.lastModified();
		String[][] attachmentFiles = new String[2][2];
		attachmentFiles[0][0] = creditReportPath;
		attachmentFiles[0][1] = "Orders Still in Credit " + sdf.format(new Date()) + ".txt";
		attachmentFiles[1][0] = creditListPath;
		attachmentFiles[1][1] = "Credit List " + sdf.format(new Date()) + ".txt";
		reportFileEmailer.sendEmailWithAttachment("ar@mariettadrapery.com",
				"Hold List " + sdf.format(new Date()), "", attachmentFiles); // UPDATE!
	}
	
	private void emailNewInactive(){
		
		reportFileEmailer.sendEmailWithAttachment("igarcia@mariettadrapery.com", "New and Inactives for "+ sdf.format(new Date()), "Automated email from AR",
				"Z:\\PRINT-DYINAC", "NEW AND INACTIVES " + sdf.format(new Date()) + ".txt");
		reportFileEmailer.sendEmailWithAttachment("tpaige@mariettadrapery.com", "New and Inactives for "+ sdf.format(new Date()), "Automated email from AR",
				"Z:\\PRINT-DYINAC", "NEW AND INACTIVES " + sdf.format(new Date()) + ".txt");	
	}
	
	private void emailCutChecks(){
		reportFileEmailer.sendEmailWithAttachment("blinds@mariettadrapery.com", "Cuts Shipped but not Billed", "Auto-generated report", currentEditCheckPath, "MissedCuts.txt");
		
		reportFileEmailer.sendEmailWithAttachment("jedwards@mariettadrapery.com", "Cuts Shipped but not Billed", "Auto-generated report.", currentEditCheckPath, "MissedCuts.txt");
		
	}
	
	private class ReportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File tempCreditReport = new File(creditReportPath);
			long tempTimeStamp = tempCreditReport.lastModified();
			if (tempTimeStamp != currentCreditReportTimeStamp) {
				try {
					Thread.sleep(60000*30);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				emailCreditHold();
				emailNewInactive();
				emailCutChecks();
				
			}
		}
	}
}
