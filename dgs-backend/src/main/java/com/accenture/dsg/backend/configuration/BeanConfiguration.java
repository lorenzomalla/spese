package com.accenture.dsg.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accenture.dsg.backend.repository.UsersDao;
import com.accenture.dsg.backend.repository.impl.UsersDaoImpl;

@Configuration
public class BeanConfiguration {

	//Spring Configuration Bean
	@Bean
    public UsersDao accountDaoImpl() {
        return new UsersDaoImpl();
    }
}