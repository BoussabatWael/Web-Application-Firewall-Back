package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gcs.firewall.model.UrlProtection;


public interface UrlProtectionRepo extends JpaRepository<UrlProtection, Long>{
	
	@Query(value="select * from firewall_url_protection where firewall_url_protection.status IN (1,2,3) and firewall_url_protection.server_id = ?1",
			nativeQuery=true)
	public List<UrlProtection> findUrlProtectionByServer(Long server_id);
}
