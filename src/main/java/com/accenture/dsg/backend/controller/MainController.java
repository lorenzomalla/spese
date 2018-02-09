package com.accenture.dsg.backend.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accenture.dsg.backend.dao.AnswersDao;
import com.accenture.dsg.backend.dao.QuestionsDao;
import com.accenture.dsg.backend.dao.TreeStructureDao;
import com.accenture.dsg.backend.dao.UsersDao;
import com.accenture.dsg.backend.model.Answer;
import com.accenture.dsg.backend.model.Question;
import com.accenture.dsg.backend.model.TreeStructure;
import com.accenture.dsg.backend.model.User;

@Controller 
public class MainController {
	@Autowired 
	private UsersDao userDao;
	
	@Autowired
	private TreeStructureDao dao;
	
	@Autowired
	private QuestionsDao questionDao;
	
	@Autowired
	private AnswersDao answerDao;
	
	@Autowired
	private JavaMailSender mailSender;
	


	
	@RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
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
		User user = new User();
		user.setMail(email);
		user.setPassword(password);
		userDao.persist(user);
		return "Salvato";
	}
		
	@GetMapping(path="/checkLogin")
	public @ResponseBody String getAllUsers(@RequestParam String email,
											@RequestParam String password){
		User user = userDao.checkLogin(email, password);
		if(user != null){
			return "login";
		}else
			return "errato";
	}
	
	@RequestMapping(value="/getAllList", method = RequestMethod.GET)
	public @ResponseBody List<TreeStructure> getAllList(){
		List<TreeStructure> list = dao.getAllLista();
		list.toString();
		return list;
	}
	
	@RequestMapping(value="/getTree", method = RequestMethod.GET)
	public @ResponseBody TreeStructure getTree(){
		TreeStructure tree = dao.getTree();
		List<TreeStructure> listaVuota = new ArrayList<>();
		tree.setTreeStructures(listaVuota);
		return tree;
	}
	
	@RequestMapping(value="/getNodeById/{id}", method = RequestMethod.GET)
	public @ResponseBody TreeStructure getFindById(@PathVariable("id") int id){
		TreeStructure tree = dao.getFindById(id);
		for (TreeStructure t : tree.getTreeStructures()) {
				t.setTreeStructures(null);				
			}
		return tree;
	}
	
	@RequestMapping(value="/addTree" , method = RequestMethod.POST)
	public @ResponseBody String persistTreeStructure(@RequestBody TreeStructure tree){
		if(tree != null){
			dao.persistTreeStructure(tree);
			return "Salvato";
		}
			return "Errore";
	}

	@RequestMapping(value="/persistAnswer" , method = RequestMethod.POST)
	public @ResponseBody String persistAnswer(@RequestBody	Answer answer){
		if(answer != null){
			answerDao.persistAnswers(answer);
			return "salvato";
		}else
			return "errore";
	}
	
	@RequestMapping(value="/persistQuestion" , method = RequestMethod.POST)
	public @ResponseBody String persistQuestion(@RequestBody Question question){
		if(question != null){
			questionDao.persistQuestions(question);
			return "Salvato";
		}else
			return "Errore";
	}
	
	@RequestMapping(value="/sendEmail",method = RequestMethod.POST)
	public String doSendEmail(HttpServletRequest request) {
		try{
		    String recipientAddress = request.getParameter("recipient");
		    String subject = request.getParameter("subject");
		    String message = request.getParameter("message");
		     
		    System.out.println("To: " + recipientAddress);
		    System.out.println("Subject: " + subject);
		    System.out.println("Message: " + message);
		     
		    SimpleMailMessage email = new SimpleMailMessage();
		    email.setTo(recipientAddress);
		    email.setSubject(subject);
		    email.setText(message);
		    mailSender.send(email);
			}catch(Exception e){
				e.printStackTrace();
		}
	    	return "Inviata Correttamente";
	}
}
