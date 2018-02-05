package com.accenture.dsg.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accenture.dsg.backend.repository.AccountDao;
import com.accenture.dsg.backend.repository.impl.AccountDaoImpl;

@Configuration
public class BeanConfiguration {

	//Spring Configuration Bean
	@Bean
    public AccountDao accountDaoImpl() {
        return new AccountDaoImpl();
    }
}