package com.bude.collectengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CollectEngineApplication {
	public static void main(String[] args) {
		SpringApplication.run(CollectEngineApplication.class, args);
	}

}
