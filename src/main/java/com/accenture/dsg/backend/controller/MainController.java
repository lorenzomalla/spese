package com.accenture.dsg.backend.controller;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accenture.dsg.backend.dao.AnswersDao;
//import com.accenture.dsg.backend.dao.CatTemplateDao;
import com.accenture.dsg.backend.dao.CatTreeStructureDao;
import com.accenture.dsg.backend.dao.QuestionsDao;
import com.accenture.dsg.backend.dao.ContactDao;
import com.accenture.dsg.backend.dao.TemplateAttributeDao;
import com.accenture.dsg.backend.dao.TemplateDao;
import com.accenture.dsg.backend.dao.TreeStructureDao;
import com.accenture.dsg.backend.dao.UsersDao;
import com.accenture.dsg.backend.model.Answer;
//import com.accenture.dsg.backend.model.CatTemplate;
import com.accenture.dsg.backend.model.CatTreeStructureType;
import com.accenture.dsg.backend.model.Question;
import com.accenture.dsg.backend.model.Contact;
import com.accenture.dsg.backend.model.Template;
import com.accenture.dsg.backend.model.TemplateAttribute;
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
	
//	@Autowired
//	private CatTemplateDao catTemplDao;
	
	@Autowired
	private TemplateDao templDao;
	
	@Autowired
	private TemplateAttributeDao templAttrDao;
	
	@Autowired
	private CatTreeStructureDao catTreeStrDao;

	@Autowired
	private ContactDao contactDao;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value={"/error"}, method = RequestMethod.GET)
	public String errore(){
		return "error";
	}
	
	@RequestMapping(value={"/","/home"},method = RequestMethod.GET)
	public String homepage(){
		return "home";
	}
	
//	@RequestMapping(value="/supporto",method = RequestMethod.GET)
//	public String supporto(){
//		return "supporto";
//	}
	
	
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
		Set<TreeStructure> listaVuota = null;
		tree.setTreeStructures(listaVuota);
		return tree;
	}
	
	@RequestMapping(value="/getNodeById/{id}", method = RequestMethod.GET)
	public @ResponseBody TreeStructure getFindById(@PathVariable("id") int id){
		TreeStructure tree = dao.getFindById(id);
		if(!tree.getTreeStructures().isEmpty()){	
			for (TreeStructure t : tree.getTreeStructures()) {
				t.setTreeStructures(null);	
				t.setTemplate(null);
			}
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
	
//	@RequestMapping(value="/persistCatTempl" , method = RequestMethod.POST)
//	public @ResponseBody String persistCatTemplate(@RequestBody	CatTemplate catTempl){
//		if(catTempl != null){
//			catTemplDao.persistCatTemplate(catTempl);
//			return "salvato";
//		}else
//			return "errore";
//	}
	
	@RequestMapping(value="/persistTemplate" , method = RequestMethod.POST)
	public @ResponseBody String persistTemplate(@RequestBody	Template template){
		if(template != null){
			templDao.persistTemplate(template);
			return "salvato";
		}else
			return "errore";
	}
	
	@RequestMapping(value="/persistTemplAttr" , method = RequestMethod.POST)
	public @ResponseBody String persistTemplAttr(@RequestBody	TemplateAttribute templAttr){
		if(templAttr != null){
			templAttrDao.persistTemplateAttribute(templAttr);
			return "salvato";
		}else
			return "errore";
	}
	
	@RequestMapping(value="/persistCatTreeStructure" , method = RequestMethod.POST)
	public @ResponseBody String persistCatTreeStructure(@RequestBody	CatTreeStructureType catTreeStr){
		if(catTreeStr != null){
			catTreeStrDao.persistCatTreeStructureType(catTreeStr);
			return "salvato";
		}else
			return "errore";
	}
	
	@RequestMapping(value="/persistContact" , method = RequestMethod.POST)
	public @ResponseBody String persistContact(@RequestBody	Contact contact){
		if(contact != null){
			contactDao.persist(contact);
			return "salvato";
		}else
			return "errore";
	}
	
	@RequestMapping(value="/findOptions" , method = RequestMethod.GET)
	public @ResponseBody List<Contact> getFindAll(){
		List<Contact> list = contactDao.getAllList();
		list.toString();
		return list;
	}
	
	@RequestMapping(value="/findOptions/{branch}" , method = RequestMethod.GET)
	public @ResponseBody List<Contact> getFindByBranch(@PathVariable("branch") String branch){
		List<Contact> list = contactDao.getByBranch(branch);
		list.toString();
		return list;
	}
	
	@RequestMapping(value="/getByRef" , method = RequestMethod.POST)
	public @ResponseBody Contact getByRef(@RequestBody	Contact contact){
		Contact contactOut = null;
		try{
			contactOut = contactDao.getByRef(contact.getId());
			}catch(Exception e){
				e.printStackTrace();
		}
		return contactOut;
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
//	@RequestMapping()
//	public @ResponseBody Template getTemplate(@PathVariable("id") int id, @PathVariable("idServizio")int idServizio,
//										@PathVariable("channel")int channel, @PathVariable("branch")int branch){
//		Template template = null;
//		return template;
//	}
//	
	/*
	 * TODO: DA SPOSTARE IN UNA CLASSE BEAN CONFIGURATION
	 */
	 @Bean
	    public JavaMailSender getJavaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);

	        mailSender.setUsername("**EMAIL**");
	        mailSender.setPassword("**PASSWORD**");

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");

	        return mailSender;
	    }
	
}
