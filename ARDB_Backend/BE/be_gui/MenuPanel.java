package be_gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

public class MenuPanel extends JPanel {

	private JButton btnServices, btnNewButton, btnNewButton_2, btnNewButton_1;
	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(15);
		setPreferredSize(new Dimension(150, 550));
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Arial", Font.BOLD, 18));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setPreferredSize(new Dimension(150, 25));
		add(lblMenu);
		
		btnServices = new JButton("Services");
		btnServices.setPreferredSize(new Dimension(100, 25));
		add(btnServices);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setPreferredSize(new Dimension(100, 25));
		add(btnNewButton);
		
		btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setPreferredSize(new Dimension(100, 25));
		add(btnNewButton_2);
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setPreferredSize(new Dimension(100, 25));
		add(btnNewButton_1);

	}
	public JButton getBtnServices() {
		return btnServices;
	}
	public void setBtnServices(JButton btnServices) {
		this.btnServices = btnServices;
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}
	public void setBtnNewButton_2(JButton btnNewButton_2) {
		this.btnNewButton_2 = btnNewButton_2;
	}
	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}
	public void setBtnNewButton_1(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}

}
