package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.UsersServersRules;

public interface UsersServersRulesRepo extends JpaRepository<UsersServersRules, Long>{
	
	@Query(value="SELECT firewall_users_servers_rules.* FROM firewall_users_servers_rules LEFT JOIN firewall_users_servers ON firewall_users_servers_rules.user_server_id = firewall_users_servers.id WHERE firewall_users_servers.server_id =?1 AND firewall_users_servers.status IN (1,2,3) AND firewall_users_servers_rules.status IN (1,2,3)",nativeQuery=true)
	public List<UsersServersRules> getUsersServersRulesList(Long server_id);
	
	@Query(value="SELECT firewall_users_servers_rules.* FROM firewall_users_servers_rules LEFT JOIN firewall_users_servers ON firewall_users_servers.id = firewall_users_servers_rules.user_server_id WHERE firewall_users_servers.server_id=?1 AND firewall_users_servers_rules.rule_id =?2 AND firewall_users_servers.status IN (1,2,3) AND firewall_users_servers_rules.status IN (1,2,3)",nativeQuery=true)
	public UsersServersRules getUserServerRule(Long server_id, Long rule_id);
	
	@Query(value="SELECT * FROM firewall_users_servers_rules WHERE firewall_users_servers_rules.user_server_id=?1",nativeQuery=true)
	public List<UsersServersRules> getUserSeverRuleByUserServerID(Long user_server_id);
	
	@Query(value="SELECT firewall_users_servers_rules.* FROM `firewall_users_servers_rules` inner join firewall_rules on  firewall_users_servers_rules.rule_id = firewall_rules.id WHERE  firewall_rules.account_id =?1 AND firewall_users_servers_rules.status IN (1,2,3)",nativeQuery=true)
	public List<UsersServersRules> findAllUsersServersRules(Long account_id);
}
