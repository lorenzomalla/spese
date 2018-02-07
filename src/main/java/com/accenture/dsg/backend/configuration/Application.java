package com.accenture.dsg.backend.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan( basePackages = {"com.accenture.dsg.backend.model"})
//@ComponentScan(basePackages ={"com.accenture.dsg.backend.controller"})
@EnableJpaRepositories("com.accenture.dsg.backend.dao")
@SpringBootApplication(scanBasePackages={"com.accenture.dsg.backend.controller","com.accenture.dsg.backend.dao"})
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
