package com.dev.app.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.dev.app.commons.models"})
public class SpringServiceProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServiceProductsApplication.class, args);
	}

}
