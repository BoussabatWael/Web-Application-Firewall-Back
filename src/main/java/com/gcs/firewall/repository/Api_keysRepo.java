package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcs.firewall.model.Api_keys;

public interface Api_keysRepo extends JpaRepository<Api_keys, Long>{

	@Query(value="SELECT * FROM firewall_api_keys WHERE firewall_api_keys.account_id =?1 AND firewall_api_keys.status IN (1,2,3)",
			nativeQuery=true)
	public List<Api_keys> findActiveApiKeys(Long apikey);
	
	@Query(value="SELECT * FROM firewall_api_keys WHERE firewall_api_keys.key_value =?1 AND firewall_api_keys.status IN (1,2,3)",
			nativeQuery=true)
	public Api_keys findOneApiKey(String apikey);
}
