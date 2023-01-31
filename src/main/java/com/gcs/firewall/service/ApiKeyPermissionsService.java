package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.Api_key_permissions;

public interface ApiKeyPermissionsService {
	
	public List<Api_key_permissions> findAllApi_key_permissions();
	public Api_key_permissions findApiKeysPermissionsList(Long apikey);
	public Api_key_permissions addApi_key_permissions(Api_key_permissions api_key_permissions) ;
	public Api_key_permissions updateApi_key_permissions(Api_key_permissions api_key_permissions);
	public Optional<Api_key_permissions> findApi_key_permissionsById(Long id) ;
	public void deleteApi_key_permissions(Long id) ;
}
