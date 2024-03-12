package com.pdfgenerator.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.pdfgenerator.model.Invoice;
import com.pdfgenerator.model.Item;

@Service
public class PdfGeneratorService {

	public ByteArrayOutputStream generateArray(Invoice invoice) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			PdfWriter writer = new PdfWriter(output);
			PdfDocument document = new PdfDocument(writer);
			PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);
			Document newDocument = new Document(document).setFont(font);
			float[] widths = { 280F, 280F };
			Table header = new Table(widths);
			String seller = "Seller:\n" + invoice.getSeller() + "\n" + invoice.getSellerAddress() + "\n GSTIN: "
					+ invoice.getSellerGstin();
			header.addCell(new Cell().add(new Paragraph(seller)).setPadding(30));
			String buyer = "Buyer:\n" + invoice.getBuyer() + "\n" + invoice.getBuyerAddress() + "\n GSTIN: "
					+ invoice.getBuyerGstin();
			header.addCell(new Cell().add(new Paragraph(buyer)).setPadding(20));

			float[] infoWidth = { 140, 140, 140, 140 };
			Table product = new Table(infoWidth);
			product.setTextAlignment(TextAlignment.CENTER);
			product.addCell(new Cell().add(new Paragraph("Item")));
			product.addCell(new Cell().add(new Paragraph("Quantity")));
			product.addCell(new Cell().add(new Paragraph("Rate")));
			product.addCell(new Cell().add(new Paragraph("Amount")));

			List<Item> items = invoice.getItems();
			for (Item item : items) {
				product.addCell(new Cell().add(new Paragraph(item.getName())));
				product.addCell(new Cell().add(new Paragraph(item.getQuantity())));
				product.addCell(new Cell().add(new Paragraph(String.valueOf(item.getRate()))));
				product.addCell(new Cell().add(new Paragraph(String.valueOf(item.getAmount()))));
			}

			newDocument.add(header);
			newDocument.add(product);

			writer.close();
			document.close();
			newDocument.close();

		} catch (IOException e) {
			e.getMessage();
		}

		return output;
	}
}