package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.UsersLogs;
import com.gcs.firewall.repository.UsersLogsRepo;

@Service
public class UsersLogsServiceImpl implements UsersLogsService{
	
	@Autowired
	private UsersLogsRepo usersLogsRepo;
	
	@Override
	public List<UsersLogs> getAllUsersLogs() {
		// TODO Auto-generated method stub
		return usersLogsRepo.findAll();
	}

	@Override
	public Optional<UsersLogs> findUsersLogsById(Long id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findById(id);
	}

	@Override
	public void deleteUsersLogs(Long id) {
		// TODO Auto-generated method stub
		usersLogsRepo.deleteById(id);
	}

	@Override
	public UsersLogs addUsersLogs(UsersLogs usersLogs) {
		// TODO Auto-generated method stub
		return usersLogsRepo.saveAndFlush(usersLogs);
	}

	@Override
	public List<UsersLogs> findUsersLogsByElement(Long element, Long element_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findUsersLogsByElement(element, element_id);
	}

	@Override
	public List<UsersLogs> findUsersLogs(Long source,Long user_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findUsersLogs(source,user_id);
	}

	@Override
	public List<UsersLogs> findUsersLogsByUserId(Long user_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findUsersLogsByUserId(user_id);
	}

	@Override
	public List<UsersLogs> findlastUsersLogsByUserId(Long user_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findLastUsersLogsByUserId(user_id);
	}

	@Override
	public List<UsersLogs> findUsersLogs1(Long source, Long user_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findUsersLogs1(source,user_id);
	}

	@Override
	public List<UsersLogs> findServersUsersLogs(Long server_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findServersLogs(server_id);
	}

	@Override
	public List<UsersLogs> findRulesUsersLogs(Long rule_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findRulesLogs(rule_id);
	}

	@Override
	public List<UsersLogs> findGroupsUsersLogs(Long group_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findGroupsLogs(group_id);
	}

	@Override
	public List<UsersLogs> findPoliciesUsersLogs(Long policy_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findPoliciesLogs(policy_id);
	}

	@Override
	public List<UsersLogs> findUserLogs(Long user_id) {
		// TODO Auto-generated method stub
		return usersLogsRepo.findUserLogs(user_id);
	}

}
