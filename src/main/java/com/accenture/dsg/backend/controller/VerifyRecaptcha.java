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
	
	private static final String URL = "https://www.google.com/recaptcha/api/siteverify";
//	
//	@Value("${secret}")
//	private String secretKey;
//	
//	@Value("${key}")
//	private String key;
//	
//	@RequestMapping(value="/validationCheck" , method=RequestMethod.POST)
//	public void checkValidate(@RequestParam String g_recaptcha_response) throws UnirestException{
//		try{
//		HttpResponse<JsonNode> response = Unirest.post(URL).header("content-type", "application/json").header("cache-control", "no-cache")
//   				.body("{ secret: '"+key+"', response: '"+g_recaptcha_response+"'}")
//					 .asJson(); 
//		response.toString();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
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
