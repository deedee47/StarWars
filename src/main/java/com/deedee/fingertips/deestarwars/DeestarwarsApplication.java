package com.deedee.fingertips.deestarwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.deedee.fingertips.deestarwars")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.deedee.fingertips.deestarwars.repositories")
@EnableCaching
public class DeestarwarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeestarwarsApplication.class, args);
	}

}
