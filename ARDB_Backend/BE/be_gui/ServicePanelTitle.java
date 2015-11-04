package be_gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;

public class ServicePanelTitle extends JPanel {

	/**
	 * Create the panel.
	 */
	public ServicePanelTitle() {
		setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setPreferredSize(new Dimension(600, 50));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setAlignmentY(Component.TOP_ALIGNMENT);
		
		JLabel lblServicePanelTitle = new JLabel("Services");
		lblServicePanelTitle.setFont(new Font("Arial", Font.PLAIN, 18));
		lblServicePanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblServicePanelTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		lblServicePanelTitle.setPreferredSize(new Dimension(600, 25));
		add(lblServicePanelTitle);
		
		JLabel lblService = new JLabel("Service Name");
		lblService.setFont(new Font("Arial", Font.PLAIN, 12));
		lblService.setPreferredSize(new Dimension(200, 25));
		add(lblService);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setPreferredSize(new Dimension(75, 25));
		add(lblStatus);
		
		JLabel lblLastUpdate = new JLabel("Last Update");
		lblLastUpdate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLastUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastUpdate.setPreferredSize(new Dimension(150, 25));
		add(lblLastUpdate);

	}

}
