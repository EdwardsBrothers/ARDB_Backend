package be_gui;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class DisplayPanel extends JPanel {

	ServicePanel servicePanel;

	/**
	 * Create the panel.
	 */
	public DisplayPanel() {
		setLayout(new CardLayout());

		JPanel defaultPanel = new JPanel();
		defaultPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		add(defaultPanel, "default");

		servicePanel = new ServicePanel();
		add(servicePanel, "service");

	}

}
