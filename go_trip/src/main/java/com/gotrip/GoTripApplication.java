package com.gotrip;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.gotrip.service.StorageService;
import com.gotrip.storage.StorageProperties;



@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GoTripApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GoTripApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GoTripApplication.class);
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
        };
    }
}
