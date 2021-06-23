package com.example.Beta.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BetaProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(BetaProjectApplication.class, args);
		System.out.println("Conectados a la DB");
	}

}
