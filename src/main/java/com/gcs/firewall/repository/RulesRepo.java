package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.Rules;

public interface RulesRepo extends JpaRepository<Rules, Long>{
	
	@Query(value="select * from firewall_rules where firewall_rules.status IN (1,2,3)",
			nativeQuery=true)
	public List<Rules> findRulesByStatus();
	
	@Query(value="SELECT * FROM firewall_rules WHERE firewall_rules.account_id = ?1 AND firewall_rules.status IN (1,2,3)",nativeQuery=true)
	public List<Rules> findRulesByAccountId(Long account_id);
	
	@Query(value="SELECT count(*) FROM firewall_rules WHERE firewall_rules.account_id =?1",nativeQuery=true)
	public String getRulesNumber(Long account_id);
	
	@Query(value="SELECT COUNT(*) FROM firewall_rules WHERE firewall_rules.status =?1 AND firewall_rules.account_id =?2",nativeQuery=true)
	public String getRulesNumberByStatus(Long status, Long account_id);
	
	@Query(value="SELECT MONTH(start_date),count(*),status FROM firewall_rules WHERE account_id = ?1 GROUP BY MONTH(start_date),status",nativeQuery=true)
	public List<Object[]> getRulesByMonth(Long account_id);
	
	@Query(value="SELECT DISTINCT firewall_rules.* FROM firewall_rules LEFT JOIN firewall_users_servers_rules ON firewall_rules.id = firewall_users_servers_rules.rule_id AND firewall_users_servers_rules.status !=4 WHERE firewall_rules.id NOT IN (SELECT firewall_rules.id FROM firewall_rules LEFT JOIN firewall_users_servers_rules ON firewall_rules.id = firewall_users_servers_rules.rule_id AND firewall_users_servers_rules.status !=4 JOIN firewall_users_servers ON firewall_users_servers.id = firewall_users_servers_rules.user_server_id AND firewall_users_servers.server_id=?1 WHERE firewall_rules.account_id =?2 AND firewall_rules.status IN (1,2,3) AND firewall_users_servers.user_id=?3) AND firewall_rules.account_id =?2 AND firewall_rules.status IN (1,2,3) AND firewall_rules.name != 'RULE_DENY'",nativeQuery=true)
	public List<Rules> getRulesUsersServers(Long server_id, Long account_id, Long user_id);
	
	@Query(value="SELECT DISTINCT firewall_rules.* FROM firewall_rules LEFT JOIN firewall_users_servers_rules ON firewall_rules.id = firewall_users_servers_rules.rule_id AND firewall_users_servers_rules.status !=4 JOIN firewall_users_servers ON  firewall_users_servers.id = firewall_users_servers_rules.user_server_id AND firewall_users_servers.server_id=?1 WHERE firewall_rules.account_id =?2 AND firewall_rules.status IN (1,2,3) AND firewall_users_servers.user_id =?3",nativeQuery=true)
	public List<Rules> getUsersServersRulesList(Long server_id,Long account_id,Long user_id);
}
