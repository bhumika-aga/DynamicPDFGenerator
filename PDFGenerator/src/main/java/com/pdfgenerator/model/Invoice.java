package com.pdfgenerator.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String invoiceId;

	@NotBlank(message = "Seller name cannot be blank")
	private String seller;

	@NotBlank(message = "Seller GST No. cannot be blank")
	private String sellerGstin;

	@NotBlank(message = "Seller address cannot be blank")
	private String sellerAddress;

	@NotBlank(message = "Buyer name cannot be blank")
	private String buyer;

	@NotBlank(message = "Buyer GST No. cannot be blank")
	private String buyerGstin;

	@NotBlank(message = "Buyer address cannot be blank")
	private String buyerAddress;

	@NotNull(message = "List of items cannot be null")
	@Size(min = 1, message = "At least 1 item must be present in the list")
	@Valid
	@ManyToMany
	private List<Item> items;

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getSellerGstin() {
		return sellerGstin;
	}

	public void setSellerGstin(String sellerGstin) {
		this.sellerGstin = sellerGstin;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getBuyerGstin() {
		return buyerGstin;
	}

	public void setBuyerGstin(String buyerGstin) {
		this.buyerGstin = buyerGstin;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Invoice() {
	}

	public Invoice(@NotBlank(message = "Seller name cannot be blank") String seller,
			@NotBlank(message = "Seller GST No. cannot be blank") String sellerGstin,
			@NotBlank(message = "Seller address cannot be blank") String sellerAddress,
			@NotBlank(message = "Buyer name cannot be blank") String buyer,
			@NotBlank(message = "Buyer GST No. cannot be blank") String buyerGstin,
			@NotBlank(message = "Buyer address cannot be blank") String buyerAddress,
			@NotNull(message = "List of items cannot be null") @Size(min = 1, message = "At least 1 item must be present in the list") @Valid List<Item> items) {
		this.seller = seller;
		this.sellerGstin = sellerGstin;
		this.sellerAddress = sellerAddress;
		this.buyer = buyer;
		this.buyerGstin = buyerGstin;
		this.buyerAddress = buyerAddress;
		this.items = items;
	}
}