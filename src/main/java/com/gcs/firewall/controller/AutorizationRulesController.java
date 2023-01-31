package com.gcs.firewall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gcs.firewall.model.AutorizationRules;
import com.gcs.firewall.service.AutorizationRulesService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AutorizationRulesController {
	
	@Autowired
	private AutorizationRulesService autorizationRulesService;
	
	@GetMapping("/autorizationrules")
	public List <AutorizationRules> getAllAutorizationRules(){
		return autorizationRulesService.getAllAutorizationRules();
	}
	
	@GetMapping("/autorizationrules/{id}")
	public ResponseEntity<AutorizationRules> getAutorizationRulesById(@PathVariable(name="id") Long id){
		AutorizationRules autorizationRules = autorizationRulesService.findAutorizationRulesById(id).get();
		return ResponseEntity.ok(autorizationRules);
	}
	
	@PostMapping("/autorizationrules")
	public AutorizationRules addAutorizationRules(@Validated @RequestBody AutorizationRules autorizationRules){
		return autorizationRulesService.addAutorizationRules(autorizationRules);
	}
	
	@PutMapping("/autorizationrules/{id}")
	public ResponseEntity<AutorizationRules> updateAutorizationRuless(@RequestBody AutorizationRules autorizationRules){
		AutorizationRules updateAutorizationRules = autorizationRulesService.updateAutorizationRules(autorizationRules);
		return new ResponseEntity<AutorizationRules>(updateAutorizationRules,HttpStatus.OK);
	}
	
	@DeleteMapping("/autorizationrules/{id}")
	public ResponseEntity<?> deleteAutorizationRules(@PathVariable("id") Long id){
		autorizationRulesService.deleteAutorizationRules(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
