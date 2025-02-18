package com.my.basicCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BasicCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicCrudApplication.class, args);
	}

}
