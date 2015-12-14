package be_gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class MenuPanel extends JPanel {

	private JButton btnServices, btnESupply, btnGoldmark, btnNewButton_1;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {

		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(15);
		setPreferredSize(new Dimension(150, 550));
		setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Arial", Font.BOLD, 18));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setPreferredSize(new Dimension(150, 25));
		add(lblMenu);

		btnServices = new JButton("Services");
		btnServices.setPreferredSize(new Dimension(100, 25));
		add(btnServices);

		btnESupply = new JButton("E Supply");
		btnESupply.setPreferredSize(new Dimension(100, 25));
		add(btnESupply);

		btnGoldmark = new JButton("Goldmark");
		btnGoldmark.setPreferredSize(new Dimension(100, 25));
		add(btnGoldmark);

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

	

}
