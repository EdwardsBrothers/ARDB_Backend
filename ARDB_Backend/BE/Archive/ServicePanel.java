package Archive;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import be_gui.EditHandler;
import be_gui.ItemHistoryHandler;
import simplified_applications.NightlyReportPanel;

public class ServicePanel extends JPanel {

	private EditHandler editHandler;
	private InvoiceHandler invoiceHandler;
	private NightlyReportPanel nightlyReportEmailer;
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
		
		nightlyReportEmailer = new NightlyReportPanel();
		add(nightlyReportEmailer);

		itemHistoryHandler = new ItemHistoryHandler();
		add(itemHistoryHandler);

	}

}
