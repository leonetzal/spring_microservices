package com.dev.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.dev.app.users.commons.models"})
@SpringBootApplication
public class SpringServiceUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServiceUsersApplication.class, args);
	}

}
