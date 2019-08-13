package com.dev.app.products.models.service;

import java.util.List;

import com.dev.app.commons.models.Product;

public interface IProductService {

	public List<Product> findAll();
	public Product findById(Long id);
	
	public Product save(Product product);
	
	public void deleteById(Long id);
}
