package com.example.m8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example*")
public class M8Application {

	public static void main(String[] args) {
		SpringApplication.run(M8Application.class, args);
	}

}
