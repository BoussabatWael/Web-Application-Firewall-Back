package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.UrlProtection;


public interface UrlProtectionService {
	
	public UrlProtection addUrlProtections(UrlProtection urlprotection) ;
	public List<UrlProtection> getAllUrlProtection();
	public List<UrlProtection> getURLByServer(Long server_id);
	public UrlProtection updateUrlProtection(UrlProtection urlprotection);
	public Optional<UrlProtection> findUrlProtectionById(Long id) ;
	public void deleteUrlProtection(Long id) ;
}
