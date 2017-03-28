package Archive;

import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceBatch {
	
	private ArrayList<GInvoice> batch;
	private String wholeBatch;
	
	
	
	public InvoiceBatch(String input){
		wholeBatch = input;
		batch = new ArrayList<GInvoice>();
		
		Scanner scan = new Scanner(input);
		scan.useDelimiter("BEGIN");
		
		boolean outerloop = true;
		
		while(outerloop){
			boolean innerLoop = true;
				while(innerLoop){
					
				}
		}
		
		
	}
	
	private class GInvoice{
		
		private int transactionNumber;
		private String customerID;
		private String billAdd1, billAdd2, billAdd3, billAdd4;
		private String shipAdd1, shipAdd2, shipAdd3, shipAdd4;
		private String dateShipped;
		private String salesmanName;
		private int salesmanNumber;
		private String po;
		private String managementCo;
		private String terms;
		private String invoiceNumber;
		private int cutNumber;
		private ArrayList<String> lineItems;
		private float nontaxable, taxable, tax, freight, labor, total;
		private String caption;
		private boolean electronic;
		
		public GInvoice(){
			
		}

		public int getTransactionNumber() {
			return transactionNumber;
		}

		public void setTransactionNumber(int transactionNumber) {
			this.transactionNumber = transactionNumber;
		}

		public String getCustomerID() {
			return customerID;
		}

		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}

		public String getBillAdd1() {
			return billAdd1;
		}

		public void setBillAdd1(String billAdd1) {
			this.billAdd1 = billAdd1;
		}

		public String getBillAdd2() {
			return billAdd2;
		}

		public void setBillAdd2(String billAdd2) {
			this.billAdd2 = billAdd2;
		}

		public String getBillAdd3() {
			return billAdd3;
		}

		public void setBillAdd3(String billAdd3) {
			this.billAdd3 = billAdd3;
		}

		public String getBillAdd4() {
			return billAdd4;
		}

		public void setBillAdd4(String billAdd4) {
			this.billAdd4 = billAdd4;
		}

		public String getShipAdd1() {
			return shipAdd1;
		}

		public void setShipAdd1(String shipAdd1) {
			this.shipAdd1 = shipAdd1;
		}

		public String getShipAdd2() {
			return shipAdd2;
		}

		public void setShipAdd2(String shipAdd2) {
			this.shipAdd2 = shipAdd2;
		}

		public String getShipAdd3() {
			return shipAdd3;
		}

		public void setShipAdd3(String shipAdd3) {
			this.shipAdd3 = shipAdd3;
		}

		public String getShipAdd4() {
			return shipAdd4;
		}

		public void setShipAdd4(String shipAdd4) {
			this.shipAdd4 = shipAdd4;
		}

		public String getDateShipped() {
			return dateShipped;
		}

		public void setDateShipped(String dateShipped) {
			this.dateShipped = dateShipped;
		}

		public String getSalesmanName() {
			return salesmanName;
		}

		public void setSalesmanName(String salesmanName) {
			this.salesmanName = salesmanName;
		}

		public int getSalesmanNumber() {
			return salesmanNumber;
		}

		public void setSalesmanNumber(int salesmanNumber) {
			this.salesmanNumber = salesmanNumber;
		}

		public String getPo() {
			return po;
		}

		public void setPo(String po) {
			this.po = po;
		}

		public String getManagementCo() {
			return managementCo;
		}

		public void setManagementCo(String managementCo) {
			this.managementCo = managementCo;
		}

		public String getTerms() {
			return terms;
		}

		public void setTerms(String terms) {
			this.terms = terms;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public int getCutNumber() {
			return cutNumber;
		}

		public void setCutNumber(int cutNumber) {
			this.cutNumber = cutNumber;
		}

		public float getNontaxable() {
			return nontaxable;
		}

		public void setNontaxable(float nontaxable) {
			this.nontaxable = nontaxable;
		}

		public float getTaxable() {
			return taxable;
		}

		public void setTaxable(float taxable) {
			this.taxable = taxable;
		}

		public float getTax() {
			return tax;
		}

		public void setTax(float tax) {
			this.tax = tax;
		}

		public float getFreight() {
			return freight;
		}

		public void setFreight(float freight) {
			this.freight = freight;
		}

		public float getLabor() {
			return labor;
		}

		public void setLabor(float labor) {
			this.labor = labor;
		}

		public float getTotal() {
			return total;
		}

		public void setTotal(float total) {
			this.total = total;
		}

		public String getCaption() {
			return caption;
		}

		public void setCaption(String caption) {
			this.caption = caption;
		}

		public boolean isElectronic() {
			return electronic;
		}

		public void setElectronic(boolean electronic) {
			this.electronic = electronic;
		}

		public ArrayList<String> getLineItems() {
			return lineItems;
		}

		public void setLineItems(ArrayList<String> lineItems) {
			this.lineItems = lineItems;
		}
		
		
	}
}
