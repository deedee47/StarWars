package com.deedee.fingertips.deestarwars;

import com.deedee.fingertips.deestarwars.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.deedee.fingertips.deestarwars")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.deedee.fingertips.deestarwars.repositories")
public class DeestarwarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeestarwarsApplication.class, args);
	}

}
