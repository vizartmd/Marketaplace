package com.marketplace;

import com.marketplace.model.dto.DatabaseWithCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "com.marketplace")
@EnableJpaAuditing
public class MarketplaceRestApiApplication {

	public static void main(String[] args) {
		if (args[0] == null || args[1] == null) return;
		DatabaseWithCredentials.setTypeOfDatabase(args[0]);
		DatabaseWithCredentials.setUserName(args[1]);
		if (args[2].length() == 0 || args[2] == null) {
			DatabaseWithCredentials.setPassword("");
		} else {
			DatabaseWithCredentials.setPassword(args[2]);
		}
		SpringApplication.run(MarketplaceRestApiApplication.class, args);
	}
}
