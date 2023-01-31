package com.gcs.firewall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gcs.firewall.exceptions.CustomException;
import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Api_key_permissions;
import com.gcs.firewall.model.Api_keys;
import com.gcs.firewall.service.ApiKeyPermissionsService;
import com.gcs.firewall.service.ApiKeyService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ApiKeyPermissionsController {
	
	@Autowired
	private ApiKeyPermissionsService apiKeyPermissionsService;
	
	@Autowired
	private ApiKeyService apiKeyService;
	
	@GetMapping("/apikeypermissions")
	public List <Api_key_permissions> getAllApikeyPermissions(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return apiKeyPermissionsService.findAllApi_key_permissions();
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/apikeypermissions/apikey/{apikey}")
	public Api_key_permissions findApiKeysPermissionsList(@PathVariable(name="apikey") Long apikey){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
					Api_keys api_key = apiKeyService.findApiKeyById(apikey).get();
					if(api_key.getAccount().getId() == Long.parseLong(account)) {
						return apiKeyPermissionsService.findApiKeysPermissionsList(apikey);
					}else {
						throw new CustomException("NOT FOUND !");
					}
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	
	}
	
	@PostMapping("/apikeypermissions")
	public Api_key_permissions addApikeyPermissions(@Validated @RequestBody Api_key_permissions api_key_permissions){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				return apiKeyPermissionsService.addApi_key_permissions(api_key_permissions);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	
	}
	
	@GetMapping("/apikeypermissions/{id}")
	public ResponseEntity<Api_key_permissions> getApikeyPermissionsById(@PathVariable(name="id") Long id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Api_key_permissions api_key_permissions = apiKeyPermissionsService.findApi_key_permissionsById(id).get();
				return ResponseEntity.ok(api_key_permissions);
				
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	
	}
	
	@PutMapping("/apikeypermissions/{id}")
	public ResponseEntity<Api_key_permissions> updateApikeyPermissions(@RequestBody Api_key_permissions api_key_permissions){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				Api_key_permissions updateapi_key_permissions = apiKeyPermissionsService.updateApi_key_permissions(api_key_permissions);
				return new ResponseEntity<Api_key_permissions>(updateapi_key_permissions,HttpStatus.OK);					
				}
			else {
				throw new CustomException("Permission Denied !");
			}

		}else {
			throw new CustomException("Key type error !");
		}
			}
	
	@DeleteMapping("/apikeypermissions/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				//Api_key_permissions api_key_permission = apiKeyPermissionsService.findApi_key_permissionsById(id).get();
				apiKeyPermissionsService.deleteApi_key_permissions(id);
				return new ResponseEntity<Object>(HttpStatus.OK);
				
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	
	}
}
