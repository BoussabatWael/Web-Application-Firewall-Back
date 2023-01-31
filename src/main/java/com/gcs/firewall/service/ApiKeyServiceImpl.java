package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.model.Api_keys;
import com.gcs.firewall.repository.Api_keysRepo;

@Service
public class ApiKeyServiceImpl  implements ApiKeyService{

	@Autowired
	private Api_keysRepo apiKeyRepo;
	
	@Override
	public List<Api_keys> findAllApiKeys() {
		// TODO Auto-generated method stub
		return apiKeyRepo.findAll();
	}

	@Override
	public Api_keys addApiKey(Api_keys apiKey) {
		// TODO Auto-generated method stub
		return apiKeyRepo.saveAndFlush(apiKey);
	}

	@Override
	public Api_keys updateApiKey(Api_keys apiKey) {
		// TODO Auto-generated method stub
		return apiKeyRepo.saveAndFlush(apiKey);
	}

	@Override
	public Optional<Api_keys> findApiKeyById(Long id) {
		// TODO Auto-generated method stub
		return apiKeyRepo.findById(id);
	}

	@Override
	public void deleteApiKey(Long id) {
		// TODO Auto-generated method stub
		apiKeyRepo.deleteById(id);
	}

	@Override
	public List<Api_keys> findActiveApiKeys(Long account_id) {
		// TODO Auto-generated method stub
		return apiKeyRepo.findActiveApiKeys(account_id);
	}

	@Override
	public Api_keys findOneApiKey(String apikey) {
		// TODO Auto-generated method stub
		return apiKeyRepo.findOneApiKey(apikey);
	}

}
