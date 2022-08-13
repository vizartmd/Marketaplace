package com.marketplace;

import com.marketplace.model.dto.MySQLCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.marketplace")
@EnableJpaAuditing
public class MarketplaceRestApiApplication {

	public static void main(String[] args) {
//		if (args[0] != null) {
//			MySQLCredentials.setUserName(args[0]);
//		}
//		if (args[1].length() == 0) {
//			MySQLCredentials.setPassword("");
//		}
//		if (args[1] == null) {
//			MySQLCredentials.setPassword("");
//		} else {
//			MySQLCredentials.setPassword(args[1]);
//		}
		SpringApplication.run(MarketplaceRestApiApplication.class, args);
	}
}
