package be_gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ServicePanel extends JPanel {

	private EditHandler editHandler;
	private CutHandler cutHandler;
	private InvoiceHandler invoiceHandler;
	private GoldmarkHandler goldmarkHandler;
	private ESupplyHandler eSupplyHandler;
	private CreditReportEmailer creditReportEmailer;
	private ItemHistoryHandler itemHistoryHandler;
	
	

	/**
	 * Create the panel.
	 */
	public ServicePanel() {
		setPreferredSize(new Dimension(600, 550));
		setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));

		ServicePanelTitle titlePanel = new ServicePanelTitle();
		add(titlePanel);

		editHandler = new EditHandler();
		add(editHandler);

		cutHandler = new CutHandler();
		add(cutHandler);

		//invoiceHandler = new InvoiceHandler();
		//add(invoiceHandler);
		
		goldmarkHandler = new GoldmarkHandler();
		add(goldmarkHandler);
		
		eSupplyHandler = new ESupplyHandler();
		add(eSupplyHandler);

		creditReportEmailer = new CreditReportEmailer();
		add(creditReportEmailer);

		itemHistoryHandler = new ItemHistoryHandler();
		add(itemHistoryHandler);

	}

}
