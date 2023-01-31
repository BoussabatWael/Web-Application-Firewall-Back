package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.Rules;

public interface RulesService {
	
	public Rules addRules(Rules rules) ;
	public Rules addRules1(Rules rules, String server_id) ;
	public List<Rules> getAllRules();
	public String getRulesNumber(Long account_id);
	public List<Object[]> getRulesByMonth(Long account_id);
	public String getRulesNumberByStatus(Long status, Long account_id);
	public Rules updateRules(Rules rules);
	public Rules updateRules1(Rules rules, String server_id);
	public Optional<Rules> findRulesById(Long id) ;
	public List<Rules> findRulesByStatus();
	public List<Rules> findRulesByAccountId(Long account_id);
	public List<Rules> getRulesUsersServers(Long server_id, Long account_id, Long user_id);
	public List<Rules> getUsersServersRulesList(Long server_id, Long account_id,Long user_id);
	public void deleteRules(Long id) ;
	public void deleteRules1(Long id,String server_id) ;
}
