package fr.adaming.model;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public class GenererPDF {

	
	public static final String chemin="C:/Users/PDT/testPdf.pdf";
	
	public static void main(String[] args) throws DocumentException, IOException  {
		
		Document document = new Document();
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(chemin));
		}
	}
}
