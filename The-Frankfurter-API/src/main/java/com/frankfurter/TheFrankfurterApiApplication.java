package com.frankfurter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableFeignClients
public class TheFrankfurterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheFrankfurterApiApplication.class, args);
	}

}
