package com.dev.app.products.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.app.commons.models.Product;
import com.dev.app.products.models.repository.ProductRepository;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
}
