package com.dev.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dev.app.items.clients.ProductClientRest;
import com.dev.app.items.models.Item;
import com.dev.app.commons.models.Product;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService {
	
	@Autowired
	private ProductClientRest clientFeign;

	@Override
	public List<Item> findAll() {
		return clientFeign.list().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(clientFeign.detail(id), quantity);
	}

	@Override
	public Product save(Product product) {
		return clientFeign.create(product);
	}

	@Override
	public Product update(Product product, Long id) {
		// TODO Auto-generated method stub
		return clientFeign.update(product, id);
	}

	@Override
	public void delete(Long id) {
		clientFeign.delete(id);
	}
}
