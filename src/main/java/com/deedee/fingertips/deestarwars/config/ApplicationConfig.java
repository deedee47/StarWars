package com.deedee.fingertips.deestarwars.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Serializable;

@Configuration
@PropertySource("classpath:resources/application.properties")
@ConfigurationProperties("")
public class ApplicationConfig implements Serializable {

    @Bean
    public static PropertiesFactoryBean mapper() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("resources/application.properties"));
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
//        lef.setDataSource(dataSource);
//        lef.setJpaVendorAdapter(jpaVendorAdapter);
//        lef.setPackagesToScan("com.deedee.fingertips.deestarwars.models");
//        return lef;
//    }
}
