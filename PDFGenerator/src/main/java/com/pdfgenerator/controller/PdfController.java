package com.pdfgenerator.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdfgenerator.model.Invoice;
import com.pdfgenerator.services.PdfGeneratorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/pdf")
public class PdfController {

	@Autowired
	private PdfGeneratorService service;

	@PostMapping("/generate")
	public ResponseEntity<byte[]> generatePdf(@RequestBody Invoice invoice) throws Exception {
		if (invoice.equals(null) || invoice.getItems().equals(null)) {
			throw new Exception("Invalid Request!");
		}

		ByteArrayOutputStream output = service.generateArray(invoice);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDisposition(ContentDisposition.builder("attachment")
				.filename("invoice" + invoice.getSeller() + "-" + invoice.getBuyer() + ".pdf").build());

		return new ResponseEntity<>(output.toByteArray(), headers, HttpStatus.CREATED);
	}
}