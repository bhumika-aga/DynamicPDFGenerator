package com.pdfgenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String itemId;

	@NotBlank(message = "Name cannot be blank")
	private String name;

	@NotBlank(message = "Quantity cannot be blank")
	@Pattern(regexp = "^\\d+$", message = "Quantity should be a number")
	private String quantity;

	@NotNull(message = "Rate cannot be null")
	@DecimalMin(value = "0.01", message = "Rate should be greater than or equal to 0.01")
	private double rate;

	@NotNull(message = "Amount cannot be null")
	@DecimalMin(value = "0.01", message = "Amount should be greater than or equal to 0.01")
	private double amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Item() {
	}

	public Item(@NotBlank(message = "Name cannot be blank") String name,
			@NotBlank(message = "Quantity cannot be blank") @Pattern(regexp = "^\\d+$", message = "Quantity should be a number") String quantity,
			@NotNull(message = "Rate cannot be null") @DecimalMin(value = "0.01", message = "Rate should be greater than or equal to 0.01") double rate,
			@NotNull(message = "Amount cannot be null") @DecimalMin(value = "0.01", message = "Amount should be greater than or equal to 0.01") double amount) {
		this.name = name;
		this.quantity = quantity;
		this.rate = rate;
		this.amount = amount;
	}
}