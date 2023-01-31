package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.UsersServers;

public interface UsersServersService {
	
	public UsersServers addUsersServers(UsersServers usersServers);
	public List<UsersServers> getAllUsersServers();
	public List<UsersServers> findUsersServersList(Long account_id);
	public List<UsersServers> findUsersServersByStatus();
	public List<UsersServers> findUsersServersByUserID(Long user_id);
	public UsersServers findUsersServersByUserServerID(Long user_id, Long server_id);
	public UsersServers updateUsersServers(UsersServers usersServers);
	public Optional<UsersServers> findUsersServersById(Long id) ;
	public void deleteUsersServers(Long id);
}
