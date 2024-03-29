package com.dev.app.items.models;

import com.dev.app.commons.models.Product;

public class Item {

	private Product product;
	private Integer quantity;

	public Item() {}

	public Item(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Double getTotal() {
		return product.getPrice() * quantity.doubleValue();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
