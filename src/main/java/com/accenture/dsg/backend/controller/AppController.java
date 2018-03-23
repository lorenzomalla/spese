package com.accenture.dsg.backend.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;

@Controller("error")
public class AppController implements ErrorController{

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
