package com.accenture.dsg.backend.dao;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;

@Service
public class ReCaptchaService {


    @Autowired
    private RestOperations restTemplate;

    @Autowired
    private CaptchaSettings captchaSettings;

    @Autowired
    private HttpServletRequest request;
    
    @RequestMapping(value="/validateCaptcha" , method = RequestMethod.POST)
    public boolean validate(String reCaptchaResponse){
        URI verifyUri = URI.create(String.format(
                captchaSettings.getUrl() + "?secret=%s&response=%s&remoteip=%s",
                captchaSettings.getSecret(),
                reCaptchaResponse,
                request.getRemoteAddr()
        ));

        try {
            ReCaptchaResponse response = restTemplate.getForObject(verifyUri, ReCaptchaResponse.class);
            return response.isSuccess();
        } catch (Exception ignored){
            // ignore when google services are not available
            // maybe add some sort of logging or trigger that'll alert the administrator
        }

        return true;
    }

}