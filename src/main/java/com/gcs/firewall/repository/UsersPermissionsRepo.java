package com.gcs.firewall.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.UsersPermissions;

public interface UsersPermissionsRepo extends JpaRepository<UsersPermissions, Long>{
	
	@Query(value= "select * from firewall_users_permissions where firewall_users_permissions.user_id =?1 AND firewall_users_permissions.status IN (1,2,3)",
            nativeQuery = true)
	public UsersPermissions findUsersPermissionsByUserId(Long user_id);
}
