package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.Commands;
import com.gcs.firewall.model.CommandsTasks;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.repository.CommandsRepo;
import com.gcs.firewall.repository.CommandsTasksRepo;
import com.gcs.firewall.repository.ServersRepo;

@Service
public class CommandsTasksServiceImpl implements CommandsTasksService{
	
	@Autowired
	private CommandsTasksRepo commandsTasksRepo;
	
	@Autowired
	private CommandsRepo commandsRepo;
	
	@Autowired
	private ServersRepo serversRepo;
	
	@Override
	public CommandsTasks addCommandsTasks(CommandsTasks commandsTasks) {
		Commands commands=commandsRepo.findById(commandsTasks.getCommands().getId()).get();
		Servers servers= serversRepo.findById(commandsTasks.getServers().getId()).get();
		if(commands==null) {
			return null;
		}
		if(servers==null) {
			return null;
		}
		else {
			commandsTasks.setCommands(commands);
			commandsTasks.setServers(servers);
			return commandsTasksRepo.save(commandsTasks);
		}
	}

	@Override
	public List<CommandsTasks> getAllCommandsTasks() {
		// TODO Auto-generated method stub
		return commandsTasksRepo.findAll();
	}

	@Override
	public CommandsTasks updateCommandsTasks(CommandsTasks commandsTasks) {
		// TODO Auto-generated method stub
		return commandsTasksRepo.save(commandsTasks);
	}

	@Override
	public Optional<CommandsTasks> findCommandsTasksById(Long id) {
		// TODO Auto-generated method stub
		return commandsTasksRepo.findById(id);
	}

	@Override
	public void deleteCommandsTasks(Long id) {
		// TODO Auto-generated method stub
		commandsTasksRepo.deleteById(id);
	}

}
