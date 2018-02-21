package com.accenture.dsg.backend.controller;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
@Controller
public class VerifyRecaptcha {
	
	    private static class RecaptchaResponse {
	        @JsonProperty("success")
	        private boolean success;
	        @JsonProperty("error-codes")
	        private Collection<String> errorCodes;
	    }
	    // RECUPERO L'URL NEL FILE DI PROPERTIES
	    @Value("${url}")
	    private String recaptchaUrl;
//	    RECUPERO LA SECRETKEY NEL FILE DI PROPERTIES
	    @Value("${secret}")
	    private String recaptchaSecretKey;
	    
	    @RequestMapping(value="/validationCheck" , method=RequestMethod.POST)
	    public @ResponseBody boolean isResponseValid(@RequestParam String response) {
	        RestTemplate restTemplate = new RestTemplate();
	        //COSTRUISCO L'URL CONCATENANDO LA SECRET E LA RESPONSE
	        recaptchaUrl = new StringBuilder()
	                .append(recaptchaUrl)
	                .append("?secret=")
	                .append(recaptchaSecretKey)
	                .append("&response=")
	                .append(response)
	                .toString();

	        RecaptchaResponse recaptchaResponse = restTemplate.postForEntity(recaptchaUrl, null, RecaptchaResponse.class).getBody();
	        if(recaptchaResponse.success == true){
	        	return true;
	        }else{
	        	return false;
	        }
	    }
	}
