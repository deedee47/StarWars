package com.deedee.fingertips.deestarwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication (scanBasePackages = { "com.deedee.fingertips.deestarwars.*" })
@EnableJpaRepositories(basePackages = {"com.deedee.fingertips.deestarwars.repositories"})
public class DeestarwarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeestarwarsApplication.class, args);
	}

}
