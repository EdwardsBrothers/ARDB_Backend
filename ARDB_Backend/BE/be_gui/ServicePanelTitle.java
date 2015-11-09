package be_gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class ServicePanelTitle extends JPanel {

	/**
	 * Create the panel.
	 */
	public ServicePanelTitle() {
		setBorder(new MatteBorder(0, 1, 1, 1, new Color(0, 0, 0)));
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
