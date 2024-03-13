package com.pdfgenerator.controller;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.pdfgenerator.model.Invoice;
import com.pdfgenerator.model.Item;
import com.pdfgenerator.services.PdfGeneratorService;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerMockMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private Invoice invoice;

	@Mock
	private PdfGeneratorService pdfService;

	@InjectMocks
	private PdfController pdfController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(pdfController).build();

		invoice = new Invoice();
		invoice.setSeller("XYZ Pvt. Ltd.");
		invoice.setSellerGstin("29AABBCCDD121ZD");
		invoice.setSellerAddress("New Delhi, India");
		invoice.setBuyer("Vedant Computers");
		invoice.setBuyerGstin("29AABBCCDD131ZD");
		invoice.setBuyerAddress("New Delhi, India");

		List<Item> items = new ArrayList<>();
		Item item = new Item();
		item.setName("Product 1");
		item.setQuantity("12 Nos");
		item.setRate(123.00);
		item.setAmount(1476.00);
		items.add(item);
		invoice.setItems(items);
	}

	@Test
	void testGeneratePdf() throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		output.assignBytes(new ObjectMapper().writeValueAsBytes(invoice));

		Mockito.when(pdfService.generateArray(any(Invoice.class))).thenReturn(output);
		mockMvc.perform(MockMvcRequestBuilders.post("/pdf/generate").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(invoice)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_PDF))
				.andExpect(MockMvcResultMatchers.content().bytes(output.toByteArray()));
	}
}