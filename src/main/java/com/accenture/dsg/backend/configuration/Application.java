package com.accenture.dsg.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EntityScan( basePackages = {"com.accenture.dsg.backend.model"})
//@ComponentScan(basePackages ={"com.accenture.dsg.backend.controller"})
@EnableJpaRepositories("com.accenture.dsg.backend.dao")
@SpringBootApplication(scanBasePackages={"com.accenture.dsg.backend.controller","com.accenture.dsg.backend.dao"},
exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @EnableWebSecurity
    class MultiHttpSecurityConfig {
      @Autowired
      public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
          auth 
              .inMemoryAuthentication()
//              INIZIALIZZO IL RUOLO A USER E SCEGLO UN USERNAME E PASSWORD
              //DA CONTROLLARE COME RECUPERARE QUESTI DATI DA DB
                  .withUser("user").password("password").roles("USER").and();
          //NEL CASO SI VUOLE ACCEDERE COME ADMIN
//                  .withUser("admin").password("password").roles("USER", "ADMIN");
      }
    }
    @Configuration
    @Order(1)                                                        
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
            /*
             * MAPPO TUTTI I SERVIZI CHE RICHIEDONO L'AUTENTICAZIONE
             * E QUINDI RITORNANDO IL FORM DI LOGIN
             */
       
                .antMatcher("/api/**")                          
                .authorizeRequests()
                    .anyRequest().hasRole("USER")
                    .and().formLogin().loginPage("/login")
                    .and().httpBasic();
        }
    }   
    
    @Configuration                                                   
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
//            AUTORIZZO LA RICHIESTA AFFINCHE' SI E' AUTENTICATI
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .formLogin();
        }
    }
}
  
