package com.accenture.dsg.backend.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
	// Mail Configuration
	@Value("${spring.mail.from}")
	private String mailFrom;
	@Value("${sitekey}")
	private String recaptchaSiteKey;
	@Value("${privacy.pdf}")
	private String privacyPdf;
	@Value("${email.accesso}")
	private String emailAccesso;
	@Value("${email.commerciali.no}")
	private String emailCommercialiNo;
	@Value("${email.accesso.bcc}")
	private String emailAccessoBcc;
	@Value("${email.commerciali.no.bcc}")
	private String emailCommercialiNoBcc;
	@Value("${google.analytics.enabled}")
	private boolean googleAnalyticsEnabled;
	@Value("${google.analytics.code}")
	private String googleAnalyticsCode;

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

	// @Autowired
	// private CatTemplateDao catTemplDao;

	@Autowired
	private TemplateDao templDao;

	@Autowired
	private TemplateAttributeDao templAttrDao;

	@Autowired
	private CatTreeStructureDao catTreeStrDao;

	@Autowired
	private ContactDao contactDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = { "/error" }, method = RequestMethod.GET)
	public String errore() {
		return "error";
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homepage(Model model) {
		model.addAttribute("captchakey", recaptchaSiteKey);
		model.addAttribute("privacyPdf", privacyPdf);
		model.addAttribute("googleAnalyticsEnabled", googleAnalyticsEnabled);
		model.addAttribute("googleAnalyticsCode", googleAnalyticsCode);
		return "home";
	}

	@RequestMapping(value = "/persistUser", method = RequestMethod.GET)
	public @ResponseBody String addNewUser(@RequestParam String email,
			@RequestParam String password) {
		User user = new User();
		user.setMail(email);
		user.setPassword(password);
		userDao.persist(user);
		return "Salvato";
	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public @ResponseBody String getAllUsers(@RequestParam String email,
			@RequestParam String password) {
		User user = userDao.checkLogin(email, password);
		if (user != null) {
			return "login";
		} else
			return "errato";
	}

	@RequestMapping(value = "/getAllList", method = RequestMethod.GET)
	public @ResponseBody List<TreeStructure> getAllList() {
		List<TreeStructure> list = dao.getAllLista();
		list.toString();
		return list;
	}

	@RequestMapping(value = "/getTree", method = RequestMethod.GET)
	public @ResponseBody TreeStructure getTree() {
		TreeStructure tree = dao.getTree();
		Set<TreeStructure> listaVuota = null;
		tree.setTreeStructures(listaVuota);
		return tree;
	}

	@RequestMapping(value = "/getNodeById/{id}", method = RequestMethod.GET)
	public @ResponseBody TreeStructure getFindById(@PathVariable("id") int id) {
		TreeStructure tree = dao.getFindById(id);
		if (!tree.getTreeStructures().isEmpty()) {
			for (TreeStructure t : tree.getTreeStructures()) {
				t.setTreeStructures(null);
				t.setTemplate(null);
			}
		}
		return tree;
	}

	@RequestMapping(value = "/addTree", method = RequestMethod.POST)
	public @ResponseBody String persistTreeStructure(
			@RequestBody TreeStructure tree) {
		if (tree != null) {
			dao.persistTreeStructure(tree);
			return "Salvato";
		}
		return "Errore";
	}

	@RequestMapping(value = "/persistAnswer", method = RequestMethod.POST)
	public @ResponseBody String persistAnswer(@RequestBody Answer answer) {
		if (answer != null) {
			answerDao.persistAnswers(answer);
			return "salvato";
		} else
			return "errore";
	}

	@RequestMapping(value = "/persistQuestion", method = RequestMethod.POST)
	public @ResponseBody String persistQuestion(@RequestBody Question question) {
		if (question != null) {
			questionDao.persistQuestions(question);
			return "Salvato";
		} else
			return "Errore";
	}

	// @RequestMapping(value="/persistCatTempl" , method = RequestMethod.POST)
	// public @ResponseBody String persistCatTemplate(@RequestBody CatTemplate
	// catTempl){
	// if(catTempl != null){
	// catTemplDao.persistCatTemplate(catTempl);
	// return "salvato";
	// }else
	// return "errore";
	// }

	@RequestMapping(value = "/persistTemplate", method = RequestMethod.POST)
	public @ResponseBody String persistTemplate(@RequestBody Template template) {
		if (template != null) {
			templDao.persistTemplate(template);
			return "salvato";
		} else
			return "errore";
	}

	@RequestMapping(value = "/persistTemplAttr", method = RequestMethod.POST)
	public @ResponseBody String persistTemplAttr(
			@RequestBody TemplateAttribute templAttr) {
		if (templAttr != null) {
			templAttrDao.persistTemplateAttribute(templAttr);
			return "salvato";
		} else
			return "errore";
	}

	@RequestMapping(value = "/persistCatTreeStructure", method = RequestMethod.POST)
	public @ResponseBody String persistCatTreeStructure(
			@RequestBody CatTreeStructureType catTreeStr) {
		if (catTreeStr != null) {
			catTreeStrDao.persistCatTreeStructureType(catTreeStr);
			return "salvato";
		} else
			return "errore";
	}

	@RequestMapping(value = "/persistContact", method = RequestMethod.POST)
	public @ResponseBody String persistContact(@RequestBody Contact contact) {
		if (contact != null) {
			contactDao.persist(contact);
			return "salvato";
		} else
			return "errore";
	}

	@RequestMapping(value = "/findOptions", method = RequestMethod.GET)
	public @ResponseBody List<Contact> getFindAll() {
		List<Contact> list = contactDao.getAllList();
		list.toString();
		return list;
	}

	@RequestMapping(value = "/findOptions/{branch}", method = RequestMethod.GET)
	public @ResponseBody List<Contact> getFindByBranch(
			@PathVariable("branch") String branch) {
		List<Contact> list = contactDao.getByBranch(branch);
		list.toString();
		return list;
	}

	@RequestMapping(value = "/getByRef", method = RequestMethod.POST)
	public @ResponseBody Contact getByRef(@RequestBody Contact contact) {
		Contact contactOut = null;
		try {
			contactOut = contactDao.getByRef(contact.getId());
		} catch (Exception e) {
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
		if (servizio != null && !"".equals(servizio)) {
			Contact c = new Contact();
			servizioInt = Integer.parseInt(servizio);
			c.setId(servizioInt);
			mailContact = getByRef(c);
			servizioSub = " - servizio " + mailContact.getoOption();
		}

		String formType = request.getParameter("form-type");
		String tipoAssistenza = servizioInt <= 107 ? "TECNICA"
				: servizioInt >= 215 ? "COMMERCIALE" : "AMMINISTRATIVA";
		String recipientAddress = request.getParameter("emailTo");
		String bcc = request.getParameter("bcc");
		boolean emailSetted = false;
		if (servizioInt == -1) {
			tipoAssistenza = "COMMERCIALE";
			if (formType.equals("form1")) {
				// problema accesso
				recipientAddress = emailAccesso;
				bcc = emailAccessoBcc;
				tipoAssistenza = "ACCESSO";
			} else if (formType.equals("form2") || formType.equals("form4")) {
				recipientAddress = emailCommercialiNo;
				bcc = emailCommercialiNoBcc;
				emailSetted = true;
			}
		}

		try {
			String subject = "Lead TDS – assistenza " + tipoAssistenza
					+ " - ricontattare via #canale#" + servizioSub;
			String body = "QUESTA EMAIL E’ STATA GENERATA A SEGUITO DI UNA RICHIESTA EFFETTUATA DAL CLIENTE SUL PORTALE DI SUPPORTO TIM DIGITAL STORE. SI PREGA DI NON RISPONDERE DIRETTAMENTE A QUESTO INDIRIZZO EMAIL MA DI UTILIZZARE I RIFERIMENTI INDICATI DI SEGUITO.\n";
			if (formType.equals("form1")) {
				body += "NOME=#nome#\n";
				body += "COGNOME=#cognome#\n";
				body += "P.IVA o C.F.=#codiceFiscale#\n";
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
				body += "P.IVA o C.F.=#codiceFiscale#\n";
				body += "RAGIONE SOCIALE=#ragioneSociale#\n";
				body += "EMAIL=#email#\n";
				body += "FASCIA ORARIA IN CUI PREFERISCE ESSERE RICONTATTATO=#fasciaOraria#\n";
				body += "RICHIESTA=#richiesta#\n";
				subject = subject.replace("#canale#", "TELEFONO");
				recipientAddress = mailContact.getCallback();
			} else if (formType.equals("form4")) {
				body += "TELEFONO=#telefono#\n";
				body += "FASCIA ORARIA IN CUI PREFERISCE ESSERE RICONTATTATO=#fasciaOraria#\n";
				body += "RICHIESTA=#richiesta#\n";
				subject = subject.replace("#canale#", "TELEFONO");
				if (!emailSetted) {
					recipientAddress = mailContact.getCallback();
				}
			}

			body = body
					.replace(
							"#nome#",
							request.getParameter("name") != null ? request
									.getParameter("name") : "")
					.replace(
							"#cognome#",
							request.getParameter("surname") != null ? request
									.getParameter("surname") : "")
					.replace(
							"#codiceFiscale#",
							request.getParameter("codiceFiscale") != null ? request
									.getParameter("codiceFiscale") : "")
					.replace(
							"#ragioneSociale#",
							request.getParameter("ragioneSociale") != null ? request
									.getParameter("ragioneSociale") : "")
					.replace(
							"#email#",
							request.getParameter("email") != null ? request
									.getParameter("email") : "")
					.replace(
							"#telefono#",
							request.getParameter("telefono") != null ? request
									.getParameter("telefono") : "")
					.replace(
							"#richiesta#",
							request.getParameter("richiesta") != null ? request
									.getParameter("richiesta") : "");

			if (request.getParameterValues("fasciaOraria") != null) {
				String str = String.join(", ",
						request.getParameterValues("fasciaOraria"));
				body = body.replace("#fasciaOraria#", str);
			}
			SimpleMailMessage email = new SimpleMailMessage();

			email.setFrom(mailFrom);
			email.setTo(recipientAddress);
			email.setSubject(subject);
			if (bcc != null && !bcc.equals("undefined")) {
				email.setBcc(bcc.split(";"));
			}

			email.setText(body);
			mailSender.send(email);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

}
