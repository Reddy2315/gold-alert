package com.goldalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GoldAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldAlertApplication.class, args);
	}

}
