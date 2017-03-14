package simplified_applications;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ItemHistoryApp {

	private JFrame frmItemHistory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemHistoryApp window = new ItemHistoryApp();
					window.frmItemHistory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ItemHistoryApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmItemHistory = new JFrame();
		frmItemHistory.setTitle("Item History");
		frmItemHistory.setBounds(100, 100, 450, 113);
		frmItemHistory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmItemHistory.setContentPane(new ItemHistoryPanel());
	}

}
