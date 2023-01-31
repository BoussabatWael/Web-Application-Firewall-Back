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
import com.gcs.firewall.model.CommandsTasks;
import com.gcs.firewall.service.CommandsTasksService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CommandsTasksController {
	
	@Autowired
	private CommandsTasksService commandsTasksService;
	
	@GetMapping("/commandstasks")
	public List <CommandsTasks> getAllCommandsTasks(){
		return commandsTasksService.getAllCommandsTasks();
	}
	
	@GetMapping("/commandstasks/{id}")
	public ResponseEntity<CommandsTasks> getCommandsTasksById(@PathVariable(name="id") Long id){
		CommandsTasks commandsTasks = commandsTasksService.findCommandsTasksById(id).get();
		return ResponseEntity.ok(commandsTasks);
	}
	
	@PostMapping("/commandstasks")
	public CommandsTasks addCommandsTasks(@Validated @RequestBody CommandsTasks commandsTasks){
		return commandsTasksService.addCommandsTasks(commandsTasks);
	}
	@PutMapping("/commandstasks/{id}")
	public ResponseEntity<CommandsTasks> updateCommandsTasks(@RequestBody CommandsTasks commandsTasks){
		CommandsTasks updateCommandsTasks = commandsTasksService.updateCommandsTasks(commandsTasks);
		return new ResponseEntity<CommandsTasks>(updateCommandsTasks,HttpStatus.OK);
	}
	
	@DeleteMapping("/commandstasks/{id}")
	public ResponseEntity<?> deleteCommandsTasks(@PathVariable("id") Long id){
		commandsTasksService.deleteCommandsTasks(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
