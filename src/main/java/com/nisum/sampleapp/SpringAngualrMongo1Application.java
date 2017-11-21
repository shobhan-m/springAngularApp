package com.nisum.sampleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration  // Sprint Boot Auto Configuration
@ComponentScan(basePackages = "com.nisum.sampleapp")
@SpringBootApplication
public class SpringAngualrMongo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngualrMongo1Application.class, args);
	}
}
