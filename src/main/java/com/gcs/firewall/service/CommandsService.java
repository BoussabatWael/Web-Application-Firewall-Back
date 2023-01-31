package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.Commands;

public interface CommandsService {
	
	public Commands addCommands(Commands commands) ;
	public List<Commands> getAllCommands();
	public Commands updateCommands(Commands commands);
	public Optional<Commands> findCommandsById(Long id) ;
	public void deleteCommands(Long id) ;
}
