package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.UsersServers;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.UsersServersRepo;

@Service
public class UsersServersImpl implements UsersServersService{
	
	@Autowired
	private UsersServersRepo usersServersRepo;
	
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public UsersServers addUsersServers(UsersServers usersServers) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(usersServers.getServers().getAccount() == null && usersServers.getUsers().getAccount() == null) {
			usersServers.getServers().setAccount(account);
			usersServers.getUsers().setAccount(account);	
		}
		if(usersServers.getStatus() == 0) {
			usersServers.setStatus(1);
		}
		if(usersServers.getStart_date() ==null) {
			usersServers.setStart_date(new Date());
		}
		return usersServersRepo.save(usersServers);
		
	}

	@Override
	public List<UsersServers> getAllUsersServers() {
		// TODO Auto-generated method stub
		return usersServersRepo.findAll();
	}

	@Override
	public List<UsersServers> findUsersServersByStatus() {
		return usersServersRepo.findUsersServersByStatus();
		
	}

	@Override
	public UsersServers updateUsersServers(UsersServers usersServers) {
		// TODO Auto-generated method stub
		if(usersServers.getStatus() == 0) {
			usersServers.setStatus(1);
		}
		if(usersServers.getStart_date() ==null) {
			usersServers.setStart_date(new Date());
		}
		return usersServersRepo.save(usersServers);
	}

	@Override
	public Optional<UsersServers> findUsersServersById(Long id) {
		// TODO Auto-generated method stub
		return usersServersRepo.findById(id);
	}

	@Override
	public void deleteUsersServers(Long id) {
		// TODO Auto-generated method stub
		usersServersRepo.deleteById(id);
	}

	@Override
	public List<UsersServers> findUsersServersByUserID(Long user_id) {
		// TODO Auto-generated method stub
		return usersServersRepo.findUsersServersByUserID(user_id);
	}

	@Override
	public UsersServers findUsersServersByUserServerID(Long user_id, Long server_id) {
		// TODO Auto-generated method stub
		return usersServersRepo.findUsersServersByUserServerID(user_id, server_id);
	}

	@Override
	public List<UsersServers> findUsersServersList(Long account_id) {
		// TODO Auto-generated method stub
		return usersServersRepo.findUsersServersList(account_id);
	}
	


}
