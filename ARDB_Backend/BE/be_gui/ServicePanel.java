package be_gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ServicePanel extends JPanel {

	private EditHandler editHandler;
	private InvoiceHandler invoiceHandler;
	private NightlyReportEmailer nightlyReportEmailer;
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

		invoiceHandler = new InvoiceHandler();
		add(invoiceHandler);
		
		nightlyReportEmailer = new NightlyReportEmailer();
		add(nightlyReportEmailer);

		itemHistoryHandler = new ItemHistoryHandler();
		add(itemHistoryHandler);

	}

}
