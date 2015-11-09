package be_gui;

import utilities.InvoiceGenerator;

public class InvoiceHandler extends ServicePanelInd {

	private InvoiceGenerator invGen;

	public InvoiceHandler() {
		super("Invoices");
		invGen = new InvoiceGenerator();
		invGen.pdfTester();
	}

}
