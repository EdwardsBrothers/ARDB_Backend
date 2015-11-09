package be_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class BE_GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BE_GUI window = new BE_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BE_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		BE_GUI_ManagerPanel e_GUI_ManagerPanel = new BE_GUI_ManagerPanel();
		frame.setContentPane(e_GUI_ManagerPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
