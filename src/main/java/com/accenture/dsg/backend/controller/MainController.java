package com.accenture.dsg.backend.controller;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accenture.dsg.backend.dao.AnswersDao;
//import com.accenture.dsg.backend.dao.CatTemplateDao;
import com.accenture.dsg.backend.dao.CatTreeStructureDao;
import com.accenture.dsg.backend.dao.ContactDao;
import com.accenture.dsg.backend.dao.QuestionsDao;
import com.accenture.dsg.backend.dao.TemplateAttributeDao;
import com.accenture.dsg.backend.dao.TemplateDao;
import com.accenture.dsg.backend.dao.TreeStructureDao;
import com.accenture.dsg.backend.dao.UsersDao;
import com.accenture.dsg.backend.model.Answer;
//import com.accenture.dsg.backend.model.CatTemplate;
import com.accenture.dsg.backend.model.CatTreeStructureType;
import com.accenture.dsg.backend.model.Contact;
import com.accenture.dsg.backend.model.Question;
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
	

	// Mail Configuration
	@Value("${spring.mail.from}")
	private String mailFrom;
	@Value("${spring.mail.host}")
	private String mailHost;
	@Value("${spring.mail.username}")
	private String mailUsername;
	@Value("${spring.mail.password}")
	private String mailPassword;
	@Value("${spring.mail.debug}")
	private String mailDebug;
	@Value("${spring.mail.properties.mail.transport.protocol}")
	private String mailTransportProtocol;
	@Value("${spring.mail.properties.mail.smtp.port}")
	private String mailSmtpPort;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String mailSmtpAuth;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String mailSmtpStartTLSEnable;
	@Value("${spring.mail.properties.mail.smtp.starttls.required}")
	private String mailSmtpStartTLSRequired;
    @Value("${secret}")
    private String recaptchaSecretKey;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value={"/error"}, method = RequestMethod.GET)
	public String errore(){
		return "error";
	}
	
	@RequestMapping(value={"/","/home"},method = RequestMethod.GET)
	public String homepage(Model model){
		model.addAttribute("captchakey", recaptchaSecretKey);
		return "home";
	}
	
	@RequestMapping(value ="/persistUser" , method = RequestMethod.GET)
	public @ResponseBody String addNewUser (@RequestParam String email ,
											@RequestParam String password) {
		User user = new User();
		user.setMail(email);
		user.setPassword(password);
		userDao.persist(user);
		return "Salvato";
	}
		
	@RequestMapping(value= "/checkLogin" , method = RequestMethod.POST)
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
	public @ResponseBody String persistAnswer(@RequestBody Answer answer){
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
	public @ResponseBody String persistTemplate(@RequestBody Template template){
		if(template != null){
			templDao.persistTemplate(template);
			return "salvato";
		}else
			return "errore";
	}
	
	@RequestMapping(value="/persistTemplAttr" , method = RequestMethod.POST)
	public @ResponseBody String persistTemplAttr(@RequestBody TemplateAttribute templAttr){
		if(templAttr != null){
			templAttrDao.persistTemplateAttribute(templAttr);
			return "salvato";
		}else
			return "errore";
	}
	
	@RequestMapping(value="/persistCatTreeStructure" , method = RequestMethod.POST)
	public @ResponseBody String persistCatTreeStructure(@RequestBody CatTreeStructureType catTreeStr){
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
	public @ResponseBody Contact getByRef(@RequestBody Contact contact){
		Contact contactOut = null;
		try{
			contactOut = contactDao.getByRef(contact.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return contactOut;
	}
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public @ResponseBody String doSendEmail(HttpServletRequest request) {
		String servizio = request.getParameter("servizio");
		Contact mailContact = null;
		int servizioInt = -1;
		String servizioSub = "";
		if(!"".equals(servizio)) {
			Contact c = new Contact();
			servizioInt = Integer.parseInt(servizio);
			c.setId(servizioInt);
			mailContact = getByRef(c);
			servizioSub = " - servizio "+mailContact.getoOption();
		}
		
		String tipoAssistenza = servizioInt<=107?"TECNICA":servizioInt>=215?"COMMERCIALE":"AMMINISTRATIVA";
		
		try {
			String formType = request.getParameter("form-type");
			String recipientAddress = request.getParameter("emailTo");
			String subject = "Lead TDS – assistenza "+tipoAssistenza+" -  ricontattare via #canale#"+ servizioSub; 
			String bcc = request.getParameter("bcc");
			String body = "QUESTA EMAIL E’ STATA GENERATA A SEGUITO DI UNA RICHIESTA EFFETTUATA DAL CLIENTE SUL PORTALE DI SUPPORTO TIM DIGITAL STORE. SI PREGA DI NON RISPONDERE DIRETTAMENTE A QUESTO INDIRIZZO EMAIL MA DI UTILIZZARE I RIFERIMENTI INDICATI DI SEGUITO .";
			if (formType.equals("form1")) {
				body += "NOME=#nome#\n";
				body += "COGNOME=#cognome#\n";
				body += "P.IVA o C.F. =#codiceFiscale#\n";
				body += "RAGIONE SOCIALE=#ragioneSociale#\n";
				body += "EMAIL=#email#\n";
				body += "TELEFONO=#telefono#\n";
				body += "RICHIESTA=#richiesta#\n";
				subject = subject.replace("#canale#", "EMAIL");
			} else if (formType.equals("form2")) {
				body += "EMAIL=#email#\n";
				body += "RICHIESTA=#richiesta#\n";
				subject = subject.replace("#canale#", "EMAIL");
			} else if (formType.equals("form3")) {
				body += "TELEFONO=#telefono#\n";
				body += "NOME=#nome#\n";
				body += "COGNOME=#cognome#\n";
				body += "P.IVA o C.F. =#codice fiscale o partita iva#\n";
				body += "RAGIONE SOCIALE=#ragione sociale#\n";
				body += "EMAIL=#email#\n";
				body += "FASCIA ORARIA  IN CUI PREFERISCE ESSERE RICONTATTATO=#fasciaOraria#\n";
				body += "RICHIESTA=#richiesta#\n";
				subject = subject.replace("#canale#", "TELEFONO");
				recipientAddress = mailContact.getCallback();
			} else if (formType.equals("form4")) {
				body += "TELEFONO=#telefono#\n";
				body += "FASCIA ORARIA  IN CUI PREFERISCE ESSERE RICONTATTATO=#fasciaOraria#\n";
				body += "RICHIESTA=#richiesta#\n";
				subject = subject.replace("#canale#", "TELEFONO");
				recipientAddress = mailContact.getCallback();
			}
			
			body = body.replace("#nome#", request.getParameter("name"))
					.replace("#cognome#", request.getParameter("surname"))
					.replace("#codiceFiscale#", request.getParameter("codiceFiscale"))
					.replace("#ragioneSociale#", request.getParameter("ragioneSociale"))
					.replace("#email#", request.getParameter("email"))
					.replace("#telefono#", request.getParameter("telefono"))
					.replace("#richiesta#", request.getParameter("richiesta"));
			
			if(request.getParameterValues("fasciaOraria")!= null) {
				body = body.replace("#fasciaOraria#", request.getParameterValues("fasciaOraria").toString());
			}
			
			System.out.println("To: " + recipientAddress);
			System.out.println("Subject: " + subject);
			System.out.println("Message: " + body);

			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom(mailFrom);
			email.setTo(recipientAddress);
			email.setSubject(subject);
			if (!"".equals(bcc)) {
				email.setBcc(bcc);
			}
			email.setText(body);
			mailSender.send(email);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	/*
	 * TODO: DA SPOSTARE IN UNA CLASSE BEAN CONFIGURATION
	 * 
## Mail Configuration
#spring.mail.host=host
#spring.mail.username=username
#spring.mail.password=password
#spring.mail.debug=true
#spring.mail.properties.mail.transport.protocol=smtp
#spring.mail.properties.mail.smtp.port=25
#spring.mail.properties.mail.smtp.auth=true
#spring.mail.properties.mail.smtp.starttls.enable=true
#spring.mail.properties.mail.smtp.starttls.required=true
	 * 
	 */
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mailHost);
		mailSender.setPort(Integer.valueOf(mailSmtpPort));

		mailSender.setUsername(mailUsername);
		mailSender.setPassword(mailPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", mailTransportProtocol);
		props.put("mail.smtp.auth", mailSmtpAuth);
		props.put("mail.smtp.starttls.enable", mailSmtpStartTLSEnable);
		props.put("mail.smtp.starttls.required", mailSmtpStartTLSRequired);
		props.put("mail.debug", mailDebug);

		return mailSender;
	}

}
