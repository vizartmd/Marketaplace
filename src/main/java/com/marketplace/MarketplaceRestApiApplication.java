package com.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.marketplace")
@EnableJpaAuditing
public class MarketplaceRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceRestApiApplication.class, args);
	}

}
