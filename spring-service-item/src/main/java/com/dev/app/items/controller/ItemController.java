package com.dev.app.items.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.items.models.Item;
import com.dev.app.commons.models.Product;
import com.dev.app.items.models.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment enviroment;

	@Autowired
	@Qualifier("serviceFeign")
	private IItemService itemService;
	
	@Value("${configuration.text}")
	private String text;
	
	@GetMapping("/list")
	public List<Item> list() {
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "altMethodItemDetail")
	@GetMapping("/view/{id}/cantidad/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}
	
	public Item altMethodItemDetail(Long id, Integer quantity) {
		Item item = new Item();
		Product product = new Product();
		
		item.setQuantity(quantity);
		product.setId(id);
		product.setName("Camara Sony");
		product.setPrice(500.00);
		item.setProduct(product);
		return item;
	}
	
	@GetMapping("/config")
	public ResponseEntity<?> obtConfig(@Value("${server.port}") String port) {
		log.info(text);
		
		Map<String, String> json = new HashMap<>();
		json.put("text", text);
		json.put("port", port);
		
		if(enviroment.getActiveProfiles().length > 0 && enviroment.getActiveProfiles()[0].equals("dev")) {
			json.put("author.name", enviroment.getProperty("configuration.author.name"));
			json.put("author.email", enviroment.getProperty("configuration.author.email"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return itemService.save(product);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product product, @PathVariable Long id) {
		return itemService.update(product, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.delete(id);
	}
}
