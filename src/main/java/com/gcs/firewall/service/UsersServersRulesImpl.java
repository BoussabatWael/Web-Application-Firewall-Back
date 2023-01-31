package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.UsersServersRules;
import com.gcs.firewall.repository.UsersServersRulesRepo;

@Service
public class UsersServersRulesImpl implements UsersServersRulesService{
	
	@Autowired
	private UsersServersRulesRepo usersServersRulesRepo;
	
	@Override
	public UsersServersRules addUsersServersRules(UsersServersRules usersServersRules) {
		// TODO Auto-generated method stub
		if(usersServersRules.getStatus() == 0) {
			usersServersRules.setStatus(2);
		}
		if(usersServersRules.getStart_date() ==null) {
			usersServersRules.setStart_date(new Date());
		}
		return usersServersRulesRepo.save(usersServersRules);
	}

	@Override
	public List<UsersServersRules> getAllUsersServersRules() {
		// TODO Auto-generated method stub
		return usersServersRulesRepo.findAll();
	}

	@Override
	public UsersServersRules updateUsersServersRules(UsersServersRules usersServersRules) {
		// TODO Auto-generated method stub
		if(usersServersRules.getStatus() == 0) {
			usersServersRules.setStatus(2);
		}
		if(usersServersRules.getStart_date() ==null) {
			usersServersRules.setStart_date(new Date());
		}
		return usersServersRulesRepo.save(usersServersRules);
	}

	@Override
	public Optional<UsersServersRules> findUsersServersRulesById(Long id) {
		// TODO Auto-generated method stub
		return usersServersRulesRepo.findById(id);
	}

	@Override
	public void deleteUsersServersRules(Long id) {
		// TODO Auto-generated method stub
		usersServersRulesRepo.deleteById(id);
	}

	@Override
	public List<UsersServersRules> getUsersServersRulesList(Long server_id) {
		// TODO Auto-generated method stub
		return usersServersRulesRepo.getUsersServersRulesList(server_id);
	}

	@Override
	public UsersServersRules getUserServerRule(Long server_id, Long rule_id) {
		// TODO Auto-generated method stub
		return usersServersRulesRepo.getUserServerRule(server_id, rule_id);
	}

	@Override
	public List<UsersServersRules> getUserSeverRuleByUserServerID(Long user_server_id) {
		// TODO Auto-generated method stub
		return usersServersRulesRepo.getUserSeverRuleByUserServerID(user_server_id);
	}

	@Override
	public List<UsersServersRules> findAllUsersServersRules(Long account_id) {
		// TODO Auto-generated method stub
		return usersServersRulesRepo.findAllUsersServersRules(account_id);
	}

}
