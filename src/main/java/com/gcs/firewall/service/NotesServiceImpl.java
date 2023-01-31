package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.model.Notes;
import com.gcs.firewall.repository.NotesRepo;

@Service
public class NotesServiceImpl implements NotesService{
	
	@Autowired
	private NotesRepo notesRepo;
	
	@Override
	public Notes addNotes(Notes notes) {
		// TODO Auto-generated method stub
		return notesRepo.save(notes);
	}

	@Override
	public List<Notes> getAllNotes() {
		// TODO Auto-generated method stub
		return notesRepo.findAll();
	}

	@Override
	public Notes updateNotes(Notes notes) {
		// TODO Auto-generated method stub
		return notesRepo.save(notes);
	}

	@Override
	public Optional<Notes> findNotesById(Long id) {
		// TODO Auto-generated method stub
		return notesRepo.findById(id);
	}

	@Override
	public void deleteNotes(Long id) {
		// TODO Auto-generated method stub
		notesRepo.deleteById(id);
	}

}
