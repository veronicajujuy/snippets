package com.backend.snippets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.backend.snippets"})
public class SnippetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippetsApplication.class, args);
	}

}
