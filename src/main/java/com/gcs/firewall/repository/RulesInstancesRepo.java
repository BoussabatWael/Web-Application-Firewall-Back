package com.gcs.firewall.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.RulesInstances;

public interface RulesInstancesRepo extends JpaRepository<RulesInstances, Long>{
	
	 @Query(value= "select * from firewall_rules_instances inner join firewall_rules on firewall_rules_instances.rule_id = firewall_rules.id where firewall_rules_instances.status IN (1,2,3) AND firewall_rules.status IN (1,2,3) AND firewall_rules_instances.server_id =?1 order by firewall_rules.id",
	            nativeQuery = true)
	 public List<RulesInstances> findRulesInstancesByServerId(Long server_id);
	 
	 @Query(value= "select * from firewall_rules_instances inner join firewall_servers on firewall_rules_instances.server_id = firewall_servers.id where firewall_servers.status IN (1,2,3) AND firewall_rules_instances.status IN (1,2,3) AND firewall_rules_instances.rule_id =?1 order by firewall_servers.id",
	            nativeQuery = true)
	 public List<RulesInstances> findRulesInstancesByRuleId(Long rule_id);
	 
	 @Query(value= "SELECT * FROM firewall_rules_instances WHERE firewall_rules_instances.rule_id =?1 AND firewall_rules_instances.server_id=?2 AND firewall_rules_instances.status IN (1,2,3)",
	            nativeQuery = true)
	 public RulesInstances getRulesInstancesList(Long rule_id,Long server_id);
	 
	 @Query(value= "select * from firewall_rules_instances where firewall_rules_instances.rule_id=?1",
	            nativeQuery = true)
	 public RulesInstances getRuleInstanceByRuleId(Long rule_id);
	 
	 @Query(value="SELECT COUNT(*) FROM firewall_rules_instances WHERE firewall_rules_instances.server_id = ?1 AND firewall_rules_instances.status IN (1,2,3)",nativeQuery=true)
	 public String getRulesInstancesNumberByServerId(Long server_id);
	 
	 @Query(value="select * from firewall_rules_instances inner join firewall_rules on firewall_rules_instances.rule_id = firewall_rules.id where firewall_rules_instances.status IN (1,2,3) AND firewall_rules.status IN (1,2,3) AND firewall_rules_instances.server_id =?1 AND (firewall_rules.ip_address=?2 AND firewall_rules.action=?5 AND firewall_rules.protocol=?3 AND firewall_rules.port=?4) order by firewall_rules.id",nativeQuery=true)
	 public List<RulesInstances> checkRule(Long server_id,String ip_address,String protocol,String port, String action);
	 
	 @Query(value="SELECT firewall_rules_instances.* FROM `firewall_rules_instances` inner join firewall_rules on firewall_rules_instances.rule_id = firewall_rules.id WHERE firewall_rules.account_id=?1 AND firewall_rules_instances.status IN (1,2,3)",nativeQuery=true)
	 public List<RulesInstances> findRulesInstancesList(Long account_id);
	 
}
