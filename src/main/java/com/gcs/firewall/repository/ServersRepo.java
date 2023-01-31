package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.Servers;


public interface ServersRepo extends JpaRepository<Servers, Long>{
	
	@Query(value="select * from firewall_servers where firewall_servers.status IN (1,2,3)",nativeQuery=true)
	public List<Servers> findServersByStatus();
	
	@Query(value="SELECT * FROM firewall_servers WHERE account_id = ?1 AND status IN (1,2,3)",nativeQuery=true)
	public List<Servers> findServersByAccountId(Long account_id);
	
	@Query(value="SELECT * FROM firewall_servers WHERE firewall_servers.id NOT IN ( select firewall_rules_instances.server_id from firewall_rules_instances inner join firewall_servers on firewall_rules_instances.server_id = firewall_servers.id where firewall_servers.status IN (1,2,3) AND firewall_rules_instances.status IN (1,2,3) AND firewall_rules_instances.rule_id =?1 order by firewall_servers.id) AND firewall_servers.status IN (1,2,3) AND firewall_servers.account_id =?2",
			nativeQuery=true)
	public List<Servers> findServersList(Long r_id,Long acc_id);
	
	@Query(value="SELECT * FROM firewall_servers WHERE firewall_servers.id NOT IN ( select firewall_policies_instances.server_id from firewall_policies_instances inner join firewall_servers on firewall_policies_instances.server_id = firewall_servers.id where firewall_servers.status IN (1,2,3) AND firewall_policies_instances.status IN (1,2,3) AND firewall_policies_instances.policy_id =?1 order by firewall_servers.id) AND firewall_servers.status IN (1,2,3) AND firewall_servers.account_id =?2",
			nativeQuery=true)
	public List<Servers> findServerList(Long policy_id,Long acc_id);
	
	@Query(value="SELECT count(*) FROM firewall_servers WHERE firewall_servers.status IN (1,2,3) AND firewall_servers.account_id =?1",nativeQuery=true)
	public String getServersNumber(Long account_id);
	
	@Query(value="SELECT fs.name,COUNT(fri.id) FROM firewall_servers fs JOIN firewall_rules_instances fri on fs.id=fri.server_id WHERE fs.account_id =?1 GROUP BY fs.id",nativeQuery=true)
	public List<Object[]> getRulesByServer(Long account_id);
	
	@Query(value="SELECT DISTINCT firewall_servers.* FROM firewall_servers LEFT JOIN firewall_users_servers ON firewall_servers.id = firewall_users_servers.server_id AND firewall_users_servers.user_id=?1 AND firewall_users_servers.status !=4 WHERE firewall_servers.account_id =?2 AND firewall_users_servers.server_id IS NULL AND firewall_servers.status IN (1,2,3)",nativeQuery=true)
	public List<Servers> getUsersServersList(Long user_id, Long account_id);
	
}
