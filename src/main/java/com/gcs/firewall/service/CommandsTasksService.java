package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.CommandsTasks;

public interface CommandsTasksService {
	
	public CommandsTasks addCommandsTasks(CommandsTasks commandsTasks) ;
	public List<CommandsTasks> getAllCommandsTasks();
	public CommandsTasks updateCommandsTasks(CommandsTasks commandsTasks);
	public Optional<CommandsTasks> findCommandsTasksById(Long id) ;
	public void deleteCommandsTasks(Long id) ;
}
