package com.dev.app.items.models.service;

import java.util.List;

import com.dev.app.items.models.Item;
import com.dev.app.commons.models.Product;

public interface IItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer quantity);
	
	public Product save(Product product);
	
	public Product update(Product product, Long id);
	
	public void delete(Long id);
}
