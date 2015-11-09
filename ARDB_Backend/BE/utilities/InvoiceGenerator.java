package utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class InvoiceGenerator {

	private PDDocument pdfTemplate;
	private PDDocumentCatalog docCatalog;
	private PDAcroForm acroForm;
	private List<PDField> fieldList;
	private String[] fieldArray;

	public InvoiceGenerator() {

	}

	public boolean pdfTester() {

		/* public boolean generateInvoice(Invoice invoice, String location){ */
		try {
			pdfTemplate = PDDocument.load(new File("ReferenceFiles\\MDC Invoice Form.pdf"));
			docCatalog = pdfTemplate.getDocumentCatalog();
			acroForm = docCatalog.getAcroForm();

			fieldList = acroForm.getFields();
			fieldArray = new String[fieldList.size()];

			int i = 0;
			for (PDField sField : fieldList) {
				fieldArray[i] = sField.getFullyQualifiedName();
				i++;
			}

			for (String f : fieldArray) {
				PDField field = acroForm.getField(f);
				System.out.println("f is: " + f);
				// field.setValue("12345");
			}
			
			pdfTemplate.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
