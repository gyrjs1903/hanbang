package com.green.hanbang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class HanbangApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanbangApplication.class, args);
	}

}
