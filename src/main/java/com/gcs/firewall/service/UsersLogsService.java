package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.UsersLogs;

public interface UsersLogsService {
	
	public UsersLogs addUsersLogs(UsersLogs usersLogs) ;
	public List<UsersLogs> getAllUsersLogs();
	public Optional<UsersLogs> findUsersLogsById(Long id) ;
	public List<UsersLogs> findUsersLogsByElement(Long element,Long element_id);
	public List<UsersLogs> findUsersLogs(Long source,Long user_id);
	public List<UsersLogs> findUsersLogs1(Long source,Long user_id);
	public List<UsersLogs> findUsersLogsByUserId(Long user_id);
	public List<UsersLogs> findlastUsersLogsByUserId(Long user_id);
	public List<UsersLogs> findServersUsersLogs(Long server_id);
	public List<UsersLogs> findRulesUsersLogs(Long rule_id);
	public List<UsersLogs> findGroupsUsersLogs(Long group_id);
	public List<UsersLogs> findPoliciesUsersLogs(Long policy_id);
	public List<UsersLogs> findUserLogs(Long user_id);
	public void deleteUsersLogs(Long id) ;
}
