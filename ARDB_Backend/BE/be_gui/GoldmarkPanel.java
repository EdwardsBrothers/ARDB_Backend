package be_gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class GoldmarkPanel extends JPanel {

	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public GoldmarkPanel() {
		setPreferredSize(new Dimension(600, 550));
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

	}

}
