package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Groups;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.GroupsRepo;

@Service
public class GroupsServiceImpl implements GroupsService{
	
	@Autowired
	private GroupsRepo groupsRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	
	@Override
	public Groups addGroups(Groups groups) {
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(groups.getAccount()==null) {
			groups.setAccount(account);
		}
		if(groups.getStatus() == 0) {
			groups.setStatus(1);
		}
		if(groups.getStart_date() == null) {
			groups.setStart_date(new Date());
		}
		return groupsRepo.saveAndFlush(groups);

	}

	@Override
	public List<Groups> getAllGroups() {
		return groupsRepo.findAll();
	}

	@Override
	public Groups updateGroups(Groups groups) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(groups.getAccount()==null) {
			groups.setAccount(account);
		}
		if(groups.getStatus() == 0) {
			groups.setStatus(1);
		}
		if(groups.getStart_date() == null) {
			groups.setStart_date(new Date());
		}
		return groupsRepo.save(groups);
	}

	@Override
	public Optional<Groups> findGroupsById(Long id) {
		// TODO Auto-generated method stub
		return groupsRepo.findById(id);
	}

	@Override
	public void deleteGroups(Long id) {
		// TODO Auto-generated method stub
		groupsRepo.deleteById(id);
	}

	@Override
	public List<Groups> findGroupsByStatus() {
		// TODO Auto-generated method stub
		return groupsRepo.findGroupsByStatus();
	}

	@Override
	public List<Groups> findGroupsList(Long r_id,Long acc_id) {
		// TODO Auto-generated method stub
		return groupsRepo.findGroupsList(r_id,acc_id);
	}

	@Override
	public List<Groups> findGroupsByAccountId(Long account_id) {
		// TODO Auto-generated method stub
		return groupsRepo.findGroupsByAccountId(account_id);
	}

}
