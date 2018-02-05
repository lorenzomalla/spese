package com.accenture.dsg.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.AccountDao;
import hello.AccountDaoImpl;

@Configuration
public class BeanConfiguration {

	//Spring Configuration Bean
	@Bean
    public AccountDao accountDaoImpl() {
        return new AccountDaoImpl();
    }
}