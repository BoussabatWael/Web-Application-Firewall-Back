package com.gcs.firewall.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.Api_key_permissions;

public interface ApiKeysPermissionsRepo extends JpaRepository<Api_key_permissions, Long>{

	@Query(value="SELECT * FROM firewall_api_keys_permissions WHERE firewall_api_keys_permissions.api_key_id =?1 AND firewall_api_keys_permissions.status IN (1,2,3)",
			nativeQuery=true)
	public Api_key_permissions findApiKeysPermissionsList(Long account_id);
}
