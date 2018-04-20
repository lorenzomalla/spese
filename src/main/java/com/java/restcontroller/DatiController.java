package com.java.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.dao.AutoDao;
import com.java.dao.SpesaDao;
import com.java.model.Auto;
import com.java.model.Spesa;

@RestController
public class DatiController {

	@Autowired
	private AutoDao dao;
	
	@Autowired
	private SpesaDao spesaDao;
	
	@RequestMapping(value="/persistAuto" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void persistAuto(@RequestParam String modello , @RequestParam String cilindrata,
							@RequestParam String colore){
		Auto a = new Auto();
		a.setCilindrata(cilindrata);
		a.setColore(colore);
		a.setModello(modello);
 		dao.persistAuto(a);
	}
	
	@RequestMapping(value="/persistSpesa" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void persistSpesa(@RequestParam String descrizione,
							 @RequestParam String data,
							 @RequestParam double importo){
		Spesa spesa = new Spesa();
		spesa.setDescrizione(descrizione);
		spesa.setData(data);
		spesa.setImporto(importo);	
		spesaDao.persistSpesa(spesa);
	}
	
	@RequestMapping(value="/listSpesa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Spesa> getAllList(Model model){
		List<Spesa> listaSpesa = spesaDao.getAllSpesa();
		model.addAttribute("spesas", listaSpesa);
		return listaSpesa;
	}
	
	
}
