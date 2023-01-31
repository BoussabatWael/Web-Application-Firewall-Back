package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.RulesInstances;

public interface RulesInstancesService {
	
	public RulesInstances addRulesInstances(RulesInstances rulesInstances) ;
	public List<RulesInstances> getAllRulesInstances();
	public List<RulesInstances> findRulesInstancesList(Long account_id);
	public RulesInstances updateRulesInstances(RulesInstances rulesInstances);
	public Optional<RulesInstances> findRulesInstancesById(Long id) ;
	public List<RulesInstances> findRulesInstancesByServerId(Long server_id);
	public List<RulesInstances> checkRule(Long server_id,String ip_address,String protocol,String port, String action);
	public List<RulesInstances> findRulesInstancesByRuleId(Long rule_id);
	public RulesInstances getRulesInstancesList(Long rule_id,Long server_id);
	public RulesInstances getRuleInstanceByRuleId(Long rule_id);
	public String getRuleInstanceNumberByServerId(Long server_id);
	public void deleteRulesInstances(Long id) ;
}
