package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.UsersServersRules;

public interface UsersServersRulesService {
	
	public UsersServersRules addUsersServersRules(UsersServersRules usersServersRules);
	public List<UsersServersRules> getAllUsersServersRules();
	public List<UsersServersRules> findAllUsersServersRules(Long account_id);
	public UsersServersRules updateUsersServersRules(UsersServersRules usersServersRules);
	public Optional<UsersServersRules> findUsersServersRulesById(Long id) ;
	public List<UsersServersRules> getUsersServersRulesList(Long server_id) ;
	public UsersServersRules getUserServerRule(Long server_id, Long rule_id) ;
	public List<UsersServersRules> getUserSeverRuleByUserServerID(Long user_server_id) ;
	public void deleteUsersServersRules(Long id);
}
