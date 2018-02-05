package com.accenture.dsg.backend.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.accenture.dsg.backend.configuration","com.accenture.dsg.backend.model","com.accenture.dsg.backend.controller","com.accenture.dsg.backend.repository","com.accenture.dsg.backend.repository.impl"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
