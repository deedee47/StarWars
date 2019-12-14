package com.deedee.fingertips.deestarwars.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.Serializable;

@Configuration
@PropertySource("classpath:resources/application.properties")
public class PropsConfig implements Serializable
{
    @Value("${swapi.base.url}")
    public String swapiBaseUrl;

    @Value("${header.value}")
    public String headerValue;

    @Bean
    public static PropertiesFactoryBean mapper() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("resources/application.properties"));
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }
}
