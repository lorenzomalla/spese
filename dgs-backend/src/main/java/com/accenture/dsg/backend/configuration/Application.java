package com.accenture.dsg.backend.configuration;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan( basePackages = {"com.accenture.dsg.backend.model"})
//@ComponentScan(basePackages ={"com.accenture.dsg.backend.controller"})
@EnableJpaRepositories("com.accenture.dsg.backend.dao")
@SpringBootApplication(scanBasePackages={"com.accenture.dsg.backend.controller","com.accenture.dsg.backend.dao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
