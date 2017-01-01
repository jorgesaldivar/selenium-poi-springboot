package com.jorgesaldivar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SeleniumApplication {

	public static void main(String[] args) {
		 SpringApplication.run(SeleniumApplication.class, args);
	}
}
