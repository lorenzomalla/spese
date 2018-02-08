package com.accenture.dsg.backend.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.dsg.backend.dao.TreeCrudRepository;
import com.accenture.dsg.backend.dao.UsersCrudRepository;
import com.accenture.dsg.backend.dao.UsersDao;
import com.accenture.dsg.backend.model.TreeStructure;
import com.accenture.dsg.backend.model.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller 
public class MainController {
	@Autowired 
	private UsersDao dao;
	@Autowired
	private UsersCrudRepository repo;
	@Autowired
	private TreeCrudRepository treeC;

	
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
	
	@GetMapping("/allTree")
	public @ResponseBody List<TreeStructure> retrieveAllTree() {
		return treeC.findAll();
	}

	@GetMapping("/treeStructure/{id}")
	public @ResponseBody TreeStructure retrieveTree(@PathVariable long id) throws Exception {
		Optional<TreeStructure> tree = treeC.findById(id);

		if (!tree.isPresent())
			throw new Exception("id-" + id);

		return tree.get();
	}

	@DeleteMapping("/treeStructure/{id}")
	public void deleteTree(@PathVariable long id) {
		treeC.deleteById(id);
	}

	@PostMapping("/treeStructure")
	public @ResponseBody ResponseEntity<TreeStructure> createTree(@RequestBody TreeStructure tree) {
		TreeStructure savedTree = treeC.save(tree);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedTree.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/treeStructure/{id}")
	public @ResponseBody ResponseEntity<TreeStructure> updateTree(@RequestBody TreeStructure tree, @PathVariable long id) {

		Optional<TreeStructure> treeOptional = treeC.findById(id);

		if (!treeOptional.isPresent())
			return ResponseEntity.notFound().build();

		tree.setId(id);
		
		treeC.save(tree);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path="/getNode")
	public @ResponseBody String getNextNode(@RequestParam Long treeId) {
		// This returns a JSON or XML with the users
		JsonObject respObj = new JsonObject();
		Gson gson = new Gson();  
		System.out.println("---------------------------> START");
		List<TreeStructure> Trees = treeC.findByParentId(treeId);
		System.out.println("---------------------------> JSON");
		respObj.add("Tree", gson.toJsonTree(Trees));
		System.out.println("---------------------------> TOSTRING");
		String response = respObj.toString();
		System.out.println("--------------------------->"+response);
		return response;
	}
	
}
