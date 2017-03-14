package simplified_applications;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class NightlyReportApp {

	private JFrame frmNightlyReports;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NightlyReportApp window = new NightlyReportApp();
					window.frmNightlyReports.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NightlyReportApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNightlyReports = new JFrame();
		frmNightlyReports.setTitle("Nightly Reports");
		frmNightlyReports.setBounds(100, 100, 500, 125);
		frmNightlyReports.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNightlyReports.setContentPane(new NightlyReportPanel());
	}

}
