package com.accenture.dsg.backend.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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