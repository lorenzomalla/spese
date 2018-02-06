package com.accenture.dsg.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.dsg.backend.dao.UsersCrudRepository;
import com.accenture.dsg.backend.dao.UsersDao;
import com.accenture.dsg.backend.model.Users;



@Controller    // This means that this class is a Controller // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UsersDao dao;
	
	@Autowired
	private UsersCrudRepository repo;
	
	@RequestMapping(value={"/home"}, method = RequestMethod.GET)
	public String home(){
		return "login";
	}
	
	@RequestMapping(value={"/error"}, method = RequestMethod.GET)
	public String errore(){
		return "error";
	}
	
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String email ,
											@RequestParam String password) {
		Users user = new Users();
		user.setMail(email);
		user.setPassword(password);
		dao.persist(user);
		return "Salvato";
	}
	
	@GetMapping(path="/checkLogin")
	public @ResponseBody String getAllUsers(@RequestParam String email,
											@RequestParam String password){
		Users user = dao.checkLogin(email, password);
		if(user != null){
			return "login";
		}else
			return "errato";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Users> getAllUsers() {
		// This returns a JSON or XML with the users
		return repo.findAll();
	}
	
}
