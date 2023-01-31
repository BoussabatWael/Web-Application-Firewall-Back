package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.Api_key_permissions;
import com.gcs.firewall.repository.ApiKeysPermissionsRepo;

@Service
public class ApiKeyPermissionsServiceImpl implements  ApiKeyPermissionsService {

	@Autowired
	private ApiKeysPermissionsRepo apiKeysPermissionsRepo;
	
	@Override
	public List<Api_key_permissions> findAllApi_key_permissions() {
		// TODO Auto-generated method stub
		return apiKeysPermissionsRepo.findAll();
	}

	@Override
	public Api_key_permissions addApi_key_permissions(Api_key_permissions api_key_permissions) {
		// TODO Auto-generated method stub
		return apiKeysPermissionsRepo.saveAndFlush(api_key_permissions);
	}

	@Override
	public Api_key_permissions updateApi_key_permissions(Api_key_permissions api_key_permissions) {
		// TODO Auto-generated method stub
		return apiKeysPermissionsRepo.saveAndFlush(api_key_permissions);
	}

	@Override
	public Optional<Api_key_permissions> findApi_key_permissionsById(Long id) {
		// TODO Auto-generated method stub
		return apiKeysPermissionsRepo.findById(id);
	}

	@Override
	public void deleteApi_key_permissions(Long id) {
		// TODO Auto-generated method stub
		apiKeysPermissionsRepo.deleteById(id);
	}

	@Override
	public Api_key_permissions findApiKeysPermissionsList(Long apikey) {
		// TODO Auto-generated method stub
		return apiKeysPermissionsRepo.findApiKeysPermissionsList(apikey);
	}

}
