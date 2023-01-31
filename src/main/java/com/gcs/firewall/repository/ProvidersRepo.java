package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcs.firewall.model.Providers;

public interface ProvidersRepo extends JpaRepository<Providers, Long>{
	
	@Query(value="SELECT * FROM firewall_providers WHERE firewall_providers.account_id = ?1",nativeQuery=true)
	public List<Providers> getProvidersList(Long account_id);
}
