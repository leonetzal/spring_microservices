package com.dev.app.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dev.app.items.models.Item;
import com.dev.app.commons.models.Product;

@Service("serviceRestTemplate")
public class ItemService implements IItemService {

	@Autowired
	private RestTemplate clientRest;

	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays.asList(clientRest.getForObject("http://service-products/list", Product[].class));
		return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		Product product = clientRest.getForObject("http://service-products/view/{id}", Product.class, pathVariable);
		return new Item(product, quantity);
	}

	@Override
	public Product save(Product product) {
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		ResponseEntity<Product> response = clientRest.exchange("http://service-products/create", HttpMethod.POST, body, Product.class);
		return response.getBody();
	}

	@Override
	public Product update(Product product, Long id) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		ResponseEntity<Product> response = clientRest.exchange("http://service-products/edit/{id}", HttpMethod.PUT, body, Product.class, pathVariable);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		clientRest.delete("http://service-products/delete/{id}", pathVariable);
	}
}
