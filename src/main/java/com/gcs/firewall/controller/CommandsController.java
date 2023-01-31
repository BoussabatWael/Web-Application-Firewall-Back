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
import com.gcs.firewall.model.Commands;
import com.gcs.firewall.service.CommandsService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CommandsController {
	
	@Autowired
	private CommandsService commandsService;
	
	@GetMapping("/commands")
	public List <Commands> getAllCommands(){
		return commandsService.getAllCommands();
	}
	
	@GetMapping("/commands/{id}")
	public ResponseEntity<Commands> getCommandsById(@PathVariable(name="id") Long id){
		Commands commands = commandsService.findCommandsById(id).get();
		return ResponseEntity.ok(commands);
	}
	
	@PostMapping("/commands")
	public Commands addCommands(@Validated @RequestBody Commands commands){
		return commandsService.addCommands(commands);
	}
	
	@PutMapping("/commands/{id}")
	public ResponseEntity<Commands> updateCommands(@RequestBody Commands commands){
		Commands updateCommands = commandsService.updateCommands(commands);
		return new ResponseEntity<Commands>(updateCommands,HttpStatus.OK);
	}
	
	@DeleteMapping("/commands/{id}")
	public ResponseEntity<?> deleteCommands(@PathVariable("id") Long id){
		commandsService.deleteCommands(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
