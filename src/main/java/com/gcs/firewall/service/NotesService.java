package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.Notes;

public interface NotesService {
	
	public Notes addNotes(Notes notes) ;
	public List<Notes> getAllNotes();
	public Notes updateNotes(Notes notes);
	public Optional<Notes> findNotesById(Long id) ;
	public void deleteNotes(Long id) ;
}
