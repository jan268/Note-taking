package com.example.NoteTaking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NoteTakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteTakingApplication.class, args);
	}

}
