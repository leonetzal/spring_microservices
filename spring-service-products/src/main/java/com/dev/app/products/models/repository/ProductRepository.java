package com.dev.app.products.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.dev.app.commons.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
