package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value={"/","/home"},method = {RequestMethod.GET,RequestMethod.POST})
	public String home(){
		return "index";
	}
	
	@RequestMapping(value="/login",method = {RequestMethod.GET,RequestMethod.POST})
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/registrazione",method = {RequestMethod.GET,RequestMethod.POST})
	public String registrazione(){
		return "registrazione";
	}
	
	@RequestMapping(value={"/spesa","/datiSpesa"},method = RequestMethod.GET)
	public String spesa(){
		return "datiSpesa";
	}
	
	@RequestMapping(value={"/auto","/datiAuto"},method = {RequestMethod.GET,RequestMethod.POST})
	public String auto(){
		return "datiAuto";
	}
	
	@RequestMapping(value={"/lista","/listaSpesa"},method = {RequestMethod.GET,RequestMethod.POST})
	public String listaSpesa(){
		return "datiLista";
	}
}
