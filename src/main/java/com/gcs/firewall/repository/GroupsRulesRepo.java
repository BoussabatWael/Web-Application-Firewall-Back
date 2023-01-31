package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.GroupsRules;

public interface GroupsRulesRepo extends JpaRepository<GroupsRules, Long>{
	
	@Query(value= "select * from firewall_groups_rules inner join firewall_groups on firewall_groups_rules.group_id = firewall_groups.id where firewall_groups_rules.status IN (1,2,3) AND firewall_groups.status IN (1,2,3) AND firewall_groups_rules.rule_id =?1 order by firewall_groups.id",
            nativeQuery = true)
	public List<GroupsRules> findGroupsRulesByRuleId(Long rule_id);
	
	@Query(value= "select * from firewall_groups_rules inner join firewall_rules on firewall_groups_rules.rule_id = firewall_rules.id where firewall_rules.status IN (1,2,3) AND firewall_groups_rules.group_id =?1 AND firewall_groups_rules.status IN (1,2,3) order by firewall_rules.id",
            nativeQuery = true)
	public List<GroupsRules> findGroupsRulesByGroupId(Long group_id);
	
	@Query(value= "select * from firewall_groups_rules where firewall_groups_rules.rule_id =?1 and firewall_groups_rules.group_id=?2 AND firewall_groups_rules.status IN (1,2,3)",
            nativeQuery = true)
	public GroupsRules getOneGroupRule(Long rule_id,Long group_id);
	
	@Query(value= "select * from firewall_groups_rules where firewall_groups_rules.group_id =?1",
            nativeQuery = true)
	public List <GroupsRules> getGroupRuleByGroupId(Long group_id);
}
