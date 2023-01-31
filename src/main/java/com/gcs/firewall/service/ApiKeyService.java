package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.Api_keys;

public interface ApiKeyService {
	
	public List<Api_keys> findAllApiKeys();
	public Api_keys addApiKey(Api_keys apiKey) ;
	public Api_keys updateApiKey(Api_keys apiKey);
	public List<Api_keys> findActiveApiKeys(Long account_id);
	public Api_keys findOneApiKey(String apikey);
	public Optional<Api_keys> findApiKeyById(Long id) ;
	public void deleteApiKey(Long id) ;
}
