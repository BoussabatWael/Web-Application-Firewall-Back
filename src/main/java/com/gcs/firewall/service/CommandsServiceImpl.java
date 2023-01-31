package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Commands;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.CommandsRepo;

@Service
public class CommandsServiceImpl implements CommandsService{
	
	@Autowired
	private CommandsRepo commandsRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public Commands addCommands(Commands commands) {
		Account account=accountRepo.findById(commands.getAccount().getId()).get();
		if(account==null) {
			return null;
		}
		else {
			commands.setAccount(account);
			return commandsRepo.save(commands);
		}
	}

	@Override
	public List<Commands> getAllCommands() {
		// TODO Auto-generated method stub
		return commandsRepo.findAll();
	}

	@Override
	public Commands updateCommands(Commands commands) {
		// TODO Auto-generated method stub
		return commandsRepo.save(commands);
	}

	@Override
	public Optional<Commands> findCommandsById(Long id) {
		// TODO Auto-generated method stub
		return commandsRepo.findById(id);
	}

	@Override
	public void deleteCommands(Long id) {
		// TODO Auto-generated method stub
		commandsRepo.deleteById(id);
	}

}
