package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcs.firewall.model.UsersServers;


public interface UsersServersRepo extends JpaRepository<UsersServers, Long>{
	
	@Query(value="SELECT * FROM firewall_users_servers WHERE firewall_users_servers.status IN (1,2,3)",nativeQuery=true)
	public List<UsersServers> findUsersServersByStatus();
	
	@Query(value="SELECT * FROM firewall_users_servers WHERE firewall_users_servers.user_id =?1 AND firewall_users_servers.status IN (1,2,3)",nativeQuery=true)
	public List<UsersServers> findUsersServersByUserID(Long user_id);
	
	@Query(value="SELECT * FROM firewall_users_servers WHERE firewall_users_servers.user_id=?1 AND firewall_users_servers.server_id=?2 AND firewall_users_servers.status IN (1,2,3)",nativeQuery=true)
	public UsersServers findUsersServersByUserServerID(Long user_id, Long server_id);
	
	@Query(value="SELECT firewall_users_servers.* FROM `firewall_users_servers` inner join firewall_users on firewall_users_servers.user_id = firewall_users.id WHERE firewall_users.account_id =?1 AND firewall_users_servers.status IN (1,2,3)",nativeQuery=true)
	public List<UsersServers> findUsersServersList(Long account_id);
}
