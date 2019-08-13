package com.dev.app.users.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SpringServiceOauth2Application implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringServiceOauth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		for (int i = 0; i < 4; i++) {
			String passwordBCrypt = bCryptPasswordEncoder.encode(password);
			System.out.println(passwordBCrypt);
		}
	}
}
