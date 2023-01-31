package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcs.firewall.model.Groups;


public interface GroupsRepo extends JpaRepository<Groups, Long> {
	
	@Query(value="select * from firewall_groups where firewall_groups.status IN (1,2,3)",
			nativeQuery=true)
	public List<Groups> findGroupsByStatus();
	
	@Query(value="SELECT * FROM firewall_groups WHERE firewall_groups.id NOT IN (select firewall_groups_rules.group_id from firewall_groups_rules inner join firewall_groups on firewall_groups_rules.group_id = firewall_groups.id where firewall_groups.status IN (1,2,3) AND firewall_groups_rules.status IN (1,2,3) AND firewall_groups_rules.rule_id =?1 ORDER BY firewall_groups_rules.group_id) AND firewall_groups.status IN (1,2,3) AND firewall_groups.account_id=?2",
			nativeQuery=true)
	public List<Groups> findGroupsList(Long r_id,Long acc_id);
	
	@Query(value="SELECT * FROM firewall_groups WHERE firewall_groups.account_id = ?1 AND firewall_groups.status IN (1,2,3)",nativeQuery=true)
	public List<Groups> findGroupsByAccountId(Long account_id);
}
