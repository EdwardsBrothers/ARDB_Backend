package be_gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class BE_GUI_ManagerPanel extends JPanel {
	
	private MenuPanel menuPanel;
	protected DisplayPanel displayPanel;
	
	/**
	 * Create the panel.
	 */
	public BE_GUI_ManagerPanel() {
	
		
		displayPanel = new DisplayPanel();
		
		menuPanel = new MenuPanel();
		
		
		BE_GUI_Listener bgl = new BE_GUI_Listener();
		menuPanel.getBtnServices().addActionListener(bgl);			
		add(menuPanel);
		add(displayPanel);

	}

	private class BE_GUI_Listener implements ActionListener{
		
		CardLayout cL = (CardLayout) displayPanel.getLayout();
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == menuPanel.getBtnServices()){
				cL.show(displayPanel, "service");
			}
			
		}
		
	}
}
