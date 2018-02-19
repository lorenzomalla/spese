package com.accenture.dsg.backend.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
@Controller
public class VerifyRecaptcha {
	
	private static final String URL = "https://www.google.com/recaptcha/api/siteverify";
	
	@Value("${key}")
	private String secretKey;
	
	@RequestMapping(value="/validationCheck" , method=RequestMethod.POST)
	public void checkValidate(@RequestParam String g_recaptcha_response) throws UnirestException{
		HttpResponse<String> response = Unirest.post(URL).header("content-type", "application/json").header("cache-control", "no-cache")
   				.body("{ secret: '"+secretKey+"', response: '"+g_recaptcha_response+"'}")
				  .asString(); 
	}

}