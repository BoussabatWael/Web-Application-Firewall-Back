package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.GroupsRules;

public interface GroupsRulesService {
	
	public GroupsRules addGroupsRules(GroupsRules groupsRules) ;
	public List<GroupsRules> getAllGroupsRules();
	public GroupsRules updateGroupsRules(GroupsRules groupsRules);
	public Optional<GroupsRules> findGroupsRulesById(Long id) ;
	public List<GroupsRules> findGroupsRulesByRuleId(Long rule_id);
	public List<GroupsRules> findGroupsRulesByGroupId(Long group_id);
	public GroupsRules getOneGroupRule(Long rule_id,Long group_id);
	public List<GroupsRules> getGroupRuleByGroupId(Long group_id);
	public void deleteGroupsRules(Long id) ;
}
