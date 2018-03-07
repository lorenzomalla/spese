package com.accenture.dsg.backend.controller;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.accenture.dsg.backend.model.RecaptchaResponse;

@Controller
public class VerifyRecaptcha {
	@Value("${proxyHost}")
	private String proxyHost;
	@Value("${proxyPort}")
	private String proxyPort;
	@Value("${nonProxyHosts}")
	private String nonProxyHosts;

	// RECUPERO L'URL NEL FILE DI PROPERTIES
	@Value("${url}")
	private String recaptchaUrl;
	// RECUPERO LA SECRETKEY NEL FILE DI PROPERTIES
	@Value("${secret}")
	private String recaptchaSecretKey;

	@RequestMapping(value = "/validationCheck", method = RequestMethod.POST)
	public @ResponseBody boolean isResponseValid(@RequestParam String response) {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		int port = proxyPort!=null?Integer.parseInt(proxyPort):8080;
		if(!"".equals(proxyHost)) {
			Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, port));
			requestFactory.setProxy(proxy); 
		}

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		// COSTRUISCO L'URL CONCATENANDO LA SECRET E LA RESPONSE
		String request =  recaptchaUrl + "?secret=" + recaptchaSecretKey + "&response=" + response;
		RecaptchaResponse recaptchaResponse = restTemplate.postForEntity(
				request, null, RecaptchaResponse.class).getBody();
		if (recaptchaResponse.isSuccess()) {
			return true;
		} else {
			return false;
		}
	}
}
