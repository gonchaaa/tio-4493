package com.example.appeal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AppealServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppealServiceApplication.class, args);
	}

}
