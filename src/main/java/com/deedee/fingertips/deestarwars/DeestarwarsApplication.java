package com.deedee.fingertips.deestarwars;

import com.deedee.fingertips.deestarwars.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.deedee.fingertips.deestarwars"})
@EnableJpaRepositories(basePackages = {"com.deedee.fingertips.deestarwars.repositories"})
@EntityScan(basePackages = {"com.deedee.fingertips.deestarwars.models"})
@EnableConfigurationProperties({ApplicationConfig.class})
public class DeestarwarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeestarwarsApplication.class, args);
	}

}
