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
import com.gcs.firewall.model.Notes;
import com.gcs.firewall.service.NotesService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class NotesController {
	
	@Autowired
	private NotesService notesService;
	
	@GetMapping("/notes")
	public List <Notes> getAllNotes(){
		return notesService.getAllNotes();
	}
	
	@PostMapping("/notes")
	public Notes addNotes(@Validated @RequestBody Notes notes){
		return notesService.addNotes(notes);
	}
	
	@GetMapping("/notes/{id}")
	public ResponseEntity<Notes> getNotesById(@PathVariable(name="id") Long id){
		Notes notes = notesService.findNotesById(id).get();
		return ResponseEntity.ok(notes);
	}
	
	@PutMapping("/notes/{id}")
	public ResponseEntity<Notes> updateNotes(@RequestBody Notes notes){
		Notes updateNotes = notesService.updateNotes(notes);
		return new ResponseEntity<Notes>(updateNotes,HttpStatus.OK);
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNotes(@PathVariable("id") Long id){
		notesService.deleteNotes(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
