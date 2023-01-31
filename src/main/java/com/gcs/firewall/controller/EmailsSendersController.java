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
import com.gcs.firewall.model.EmailsSenders;
import com.gcs.firewall.service.EmailsSendersService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class EmailsSendersController {
	
	@Autowired
	private EmailsSendersService emailsSendersService;
	
	@GetMapping("/emailssenders")
	public List <EmailsSenders> getAllEmailsSenders(){
		return emailsSendersService.getAllEmailsSenders();
	}
	
	@GetMapping("/emailssenders/{id}")
	public ResponseEntity<EmailsSenders> getEmailsSendersById(@PathVariable(name="id") Long id){
		EmailsSenders emailsSenders = emailsSendersService.findEmailsSendersById(id).get();
		return ResponseEntity.ok(emailsSenders);
	}
	
	@PostMapping("/emailssenders")
	public EmailsSenders addEmailsSenders(@Validated @RequestBody EmailsSenders emailsSenders){
		return emailsSendersService.addEmailsSenders(emailsSenders);
	}
	
	@PutMapping("/emailssenders/{id}")
	public ResponseEntity<EmailsSenders> updateEmailsSenders(@RequestBody EmailsSenders emailsSenders){
		EmailsSenders updateEmailsSenders = emailsSendersService.updateEmailsSenders(emailsSenders);
		return new ResponseEntity<EmailsSenders>(updateEmailsSenders,HttpStatus.OK);
	}
	
	@DeleteMapping("/emailssenders/{id}")
	public ResponseEntity<?> deleteEmailsSenders(@PathVariable("id") Long id){
		emailsSendersService.deleteEmailsSenders(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
