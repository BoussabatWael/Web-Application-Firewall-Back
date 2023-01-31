package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Groups;
import com.gcs.firewall.model.GroupsRules;
import com.gcs.firewall.model.Rules;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.GroupsRepo;
import com.gcs.firewall.repository.GroupsRulesRepo;
import com.gcs.firewall.repository.RulesRepo;


@Service
public class GroupsRulesServiceImpl implements GroupsRulesService{
	
	@Autowired
	private RulesRepo rulesRepo;
	
	@Autowired
	private GroupsRepo groupsRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private GroupsRulesRepo groupsRulesRepo;
	
	@Override
	public GroupsRules addGroupsRules(GroupsRules groupsRules) {
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		Rules rules = rulesRepo.findById(groupsRules.getRules().getId()).get();
		Groups groups = groupsRepo.findById(groupsRules.getGroups().getId()).get();
		
		if(rules==null || rules.getAccount() != account) {
			return null;
		}
		if(groups==null || rules.getAccount() != account) {
			return null;
		}
		else {
			groupsRules.setGroups(groups);
			groupsRules.setRules(rules);
			if(groupsRules.getStatus() == 0) {
				groupsRules.setStatus(1);
			}
			if(groupsRules.getStart_date() == null) {
				groupsRules.setStart_date(new Date());
			}
			return groupsRulesRepo.saveAndFlush(groupsRules);
		}
	}

	@Override
	public List<GroupsRules> getAllGroupsRules() {
		// TODO Auto-generated method stub
		return groupsRulesRepo.findAll();
	}

	@Override
	public GroupsRules updateGroupsRules(GroupsRules groupsRules) {
		// TODO Auto-generated method stub
		if(groupsRules.getStatus() == 0) {
			groupsRules.setStatus(1);
		}
		if(groupsRules.getStart_date() == null) {
			groupsRules.setStart_date(new Date());
		}
		return groupsRulesRepo.save(groupsRules);
	}

	@Override
	public Optional<GroupsRules> findGroupsRulesById(Long id) {
		// TODO Auto-generated method stub
		return groupsRulesRepo.findById(id);
	}

	@Override
	public void deleteGroupsRules(Long id) {
		// TODO Auto-generated method stub
		groupsRulesRepo.deleteById(id);
	}

	@Override
	public List<GroupsRules> findGroupsRulesByRuleId(Long rule_id) {
		// TODO Auto-generated method stub
		return groupsRulesRepo.findGroupsRulesByRuleId(rule_id);
	}

	@Override
	public List<GroupsRules> findGroupsRulesByGroupId(Long group_id) {
		// TODO Auto-generated method stub
		return groupsRulesRepo.findGroupsRulesByGroupId(group_id);
	}

	@Override
	public GroupsRules getOneGroupRule(Long rule_id, Long group_id) {
		// TODO Auto-generated method stub
		return groupsRulesRepo.getOneGroupRule(rule_id, group_id);
	}

	@Override
	public List<GroupsRules> getGroupRuleByGroupId(Long group_id) {
		// TODO Auto-generated method stub
		return groupsRulesRepo.getGroupRuleByGroupId(group_id);
	}

}
