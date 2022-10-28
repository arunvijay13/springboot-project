package com.project.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories (basePackages = {"com.project.repository"})
@EntityScan (basePackages = {"com.project.entity"})
@ComponentScan (basePackages = {"com.project.service", "com.project.controller", "com.project.finance",
					"com.project.exceptions", "com.project.JwtUtils", "com.project.mapper","com.project.config"})
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

}
