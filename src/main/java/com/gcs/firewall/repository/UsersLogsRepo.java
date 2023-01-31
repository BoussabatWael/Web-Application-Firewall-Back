package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gcs.firewall.model.UsersLogs;

public interface UsersLogsRepo extends JpaRepository<UsersLogs, Long>{
	
	@Query(value= "select * from firewall_users_logs where firewall_users_logs.element=?1 AND firewall_users_logs.element_id=?2",
            nativeQuery = true)
	public List<UsersLogs> findUsersLogsByElement(@Param("element") long element,@Param("element_id") long element_id);
	
	@Query(value= "SELECT * FROM firewall_users_logs WHERE firewall_users_logs.source =?1 OR firewall_users_logs.element IN (3,6) AND firewall_users_logs.user_id =?2",
            nativeQuery = true)
	public List<UsersLogs> findUsersLogs1(@Param("source") long source,@Param("user_id") long user_id);
	
	@Query(value= "SELECT * FROM firewall_users_logs WHERE firewall_users_logs.source =?1 AND firewall_users_logs.user_id =?2",
            nativeQuery = true)
	public List<UsersLogs> findUsersLogs(@Param("source") long source,@Param("user_id") long user_id);
	
	@Query(value= "SELECT * FROM firewall_users_logs WHERE user_id =?1",
            nativeQuery = true)
	public List<UsersLogs> findUsersLogsByUserId(@Param("user_id") long user_id);
	
	@Query(value= "SELECT * FROM firewall_users_logs WHERE firewall_users_logs.user_id =?1 ORDER BY id DESC LIMIT 5",
            nativeQuery = true)
	public List<UsersLogs> findLastUsersLogsByUserId(@Param("user_id") long user_id);
	
	@Query(value= "SELECT a.* FROM firewall_users_logs a LEFT JOIN `firewall_credentials_accounts` b ON b.id = a.element_id AND a.element = 9 LEFT JOIN firewall_rules_instances c ON c.id = a.element_id AND a.element= 6 WHERE (a.element = 2 AND a.element_id = ?1) OR (b.source = 1 AND b.source_id = ?1) OR c.server_id = ?1",nativeQuery = true)
	public List<UsersLogs> findServersLogs(@Param("server_id") long server_id);
	
	@Query(value= "SELECT a.* FROM firewall_users_logs a LEFT JOIN firewall_groups_rules b ON b.id = a.element_id AND a.element = 7 LEFT JOIN firewall_rules_instances c ON c.id = a.element_id AND a.element= 6 WHERE (a.element = 3 AND a.element_id = ?1) OR b.rule_id =?1 OR c.rule_id = ?1",nativeQuery = true)
	public List<UsersLogs> findRulesLogs(@Param("rule_id") long rule_id);
	
	@Query(value= "SELECT a.* FROM firewall_users_logs a LEFT JOIN firewall_groups_rules b ON b.id = a.element_id AND a.element = 7 WHERE (a.element = 4 AND a.element_id = ?1) OR b.group_id =?1",nativeQuery = true)
	public List<UsersLogs> findGroupsLogs(@Param("group_id") long group_id);
	
	@Query(value= "SELECT a.* FROM firewall_users_logs a LEFT JOIN firewall_policies_instances b ON b.id = a.element_id AND a.element = 8 WHERE (a.element = 5 AND a.element_id = ?1) OR b.policy_id =?1",nativeQuery = true)
	public List<UsersLogs> findPoliciesLogs(@Param("policy_id") long policy_id);
	
	@Query(value= "SELECT a.* FROM firewall_users_logs a LEFT JOIN firewall_users_permissions b ON b.id = a.element_id AND a.element = 10 LEFT JOIN firewall_users_servers c ON c.id = a.element_id AND a.element = 16 LEFT JOIN firewall_users_servers_rules d ON d.id = a.element_id AND a.element = 15 WHERE (a.element =1 AND a.element_id =?1) OR b.user_id =?1 OR c.user_id =?1 OR d.user_server_id IN (SELECT firewall_users_servers_rules.user_server_id FROM firewall_users_servers_rules JOIN firewall_users_servers on firewall_users_servers_rules.user_server_id = firewall_users_servers.id WHERE firewall_users_servers.user_id=?1)",nativeQuery = true)
	//SELECT a.* FROM firewall_users_logs a LEFT JOIN firewall_users_permissions b ON b.id = a.element_id AND a.element = 10 LEFT JOIN firewall_users_servers c ON c.id = a.element_id AND a.element = 16 LEFT JOIN firewall_users_servers_rules d ON d.id = a.element_id AND a.element = 15 WHERE (a.element =1 AND a.element_id =?1) OR b.user_id =?1 OR c.user_id =?1 OR d.user_server_id IN (SELECT firewall_users_servers_rules.user_server_id FROM firewall_users_servers_rules JOIN firewall_users_servers on firewall_users_servers_rules.user_server_id = firewall_users_servers.id WHERE firewall_users_servers.user_id=?1)
	public List<UsersLogs> findUserLogs(@Param("user_id") long user_id);
}
