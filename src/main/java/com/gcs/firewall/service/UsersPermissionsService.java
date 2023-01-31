package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.UsersPermissions;

public interface UsersPermissionsService {
	
	public UsersPermissions addUsersPermissions(UsersPermissions usersPermissions) ;
	public List<UsersPermissions> getAllUsersPermissions();
	public UsersPermissions updateUsersPermissions(UsersPermissions usersPermissions);
	public UsersPermissions findUsersPermissionsByUserId(Long user_id);
	public Optional<UsersPermissions> findUsersPermissionsById(Long id) ;
	public void deleteUsersPermissions(Long id) ;
}
