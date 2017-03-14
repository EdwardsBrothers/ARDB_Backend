package simplified_applications;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Window.Type;

public class EditPrinterApp {

	private JFrame frmEditPrinter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditPrinterApp window = new EditPrinterApp();
					window.frmEditPrinter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditPrinterApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditPrinter = new JFrame();
		frmEditPrinter.setTitle("Edit Printer & Emailer");
		frmEditPrinter.setBounds(100, 100, 500, 100);
		frmEditPrinter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel editPanel = new EditPrinterPanel();
		frmEditPrinter.setContentPane(editPanel);
		
	}

}
