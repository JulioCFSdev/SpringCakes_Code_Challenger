package com.marketplace.cake;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Helena's Delightful Treats Swagger OpenApi",
		version = "1.0",
		description = "API for controlling users and purchases made on the marketplace"))
public class CakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CakeApplication.class, args);
	}

}
