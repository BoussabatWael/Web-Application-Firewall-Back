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
import com.gcs.firewall.model.CredentialsChange;
import com.gcs.firewall.service.CredentialsChangeService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CredentialsChangeController {
	
	@Autowired
	private CredentialsChangeService credentialsChangeService;
	
	@GetMapping("/credentialschange")
	public List <CredentialsChange> getAllCredentialsChange(){
		return credentialsChangeService.getAllCredentialsChange();
	}
	
	@PostMapping("/credentialschange")
	public CredentialsChange addCredentialsChange(@Validated @RequestBody CredentialsChange credentialsChange){
		return credentialsChangeService.addCredentialsChange(credentialsChange);
	}
	
	@GetMapping("/credentialschange/{id}")
	public ResponseEntity<CredentialsChange> getCredentialsChangeById(@PathVariable(name="id") Long id){
		CredentialsChange credentialsChange = credentialsChangeService.findCredentialsChangeById(id).get();
		return ResponseEntity.ok(credentialsChange);
	}
	
	@PutMapping("/credentialschange/{id}")
	public ResponseEntity<CredentialsChange> updateCredentialsChange(@RequestBody CredentialsChange credentialsChange){
		CredentialsChange updateCredentialsChange = credentialsChangeService.updateCredentialsChange(credentialsChange);
		return new ResponseEntity<CredentialsChange>(updateCredentialsChange,HttpStatus.OK);
	}
	
	@DeleteMapping("/credentialschange/{id}")
	public ResponseEntity<?> deleteCredentialsChange(@PathVariable("id") Long id){
		credentialsChangeService.deleteCredentialsChange(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
