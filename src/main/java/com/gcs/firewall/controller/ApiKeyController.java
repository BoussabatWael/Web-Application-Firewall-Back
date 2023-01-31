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
import com.gcs.firewall.model.Api_keys;
import com.gcs.firewall.service.ApiKeyService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ApiKeyController {

	@Autowired
	private ApiKeyService apiKeyService;
	
	@GetMapping("/apikeys")
	public List <Api_keys> getAllApiKeys(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return apiKeyService.findActiveApiKeys(Long.parseLong(account));
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/apikeys/account/{account_id}")
	public List <Api_keys> getActiveApiKeys(@PathVariable(name="account_id") Long account_id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					return apiKeyService.findActiveApiKeys(account_id);
				}else {
					throw new CustomException("NOT FOUND !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}

	}
	
	@PostMapping("/apikeys")
	public Api_keys addApiKey(@Validated @RequestBody Api_keys api_keys){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				if(api_keys.getAccount().getId() == Long.parseLong(account)) {
					return apiKeyService.addApiKey(api_keys);
				}else {
					throw new CustomException("Something went wrong !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}
		else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/apikeys/{id}")
	public ResponseEntity<Api_keys> getApi_keyById(@PathVariable(name="id") Long id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
		
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Api_keys apikey = apiKeyService.findApiKeyById(id).get();
				if(String.valueOf(apikey.getAccount().getId()).equals(account)) {
					Api_keys api_key = apiKeyService.findApiKeyById(id).get();
					return ResponseEntity.ok(api_key);
				}else {
					throw new CustomException("NOT FOUND !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	
	}
	@GetMapping("/apikeys/key/{apikey}")
	public ResponseEntity<Api_keys> getOneApikey(@PathVariable(name="apikey") String apikey){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Api_keys api_key = apiKeyService.findOneApiKey(apikey);
				if(String.valueOf(api_key.getAccount().getId()).equals(account)) {
					return ResponseEntity.ok(api_key);
				}else {
					throw new CustomException("NOT FOUND !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}

	}
	
	@PutMapping("/apikeys/{id}")
	public ResponseEntity<Api_keys> updateApi_key(@RequestBody Api_keys api_key){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				if(api_key.getAccount().getId() == Long.parseLong(account)) {
				Api_keys updateApi_key = apiKeyService.updateApiKey(api_key);
				return new ResponseEntity<Api_keys>(updateApi_key,HttpStatus.OK);
				}else {
					throw new CustomException("Something went wrong !");
				}
			}else {
				throw new CustomException("Permission Denied !");
			}	
		}else {
			throw new CustomException("Key type error !");
		}

	}
	
	@DeleteMapping("/apikeys/{id}")
	public ResponseEntity<?> deleteApiKey(@PathVariable("id") Long id){		
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				Api_keys apikey = apiKeyService.findApiKeyById(id).get();
				if(String.valueOf(apikey.getAccount().getId()).equals(account)) {
					apiKeyService.deleteApiKey(id);
					return new ResponseEntity<Object>(HttpStatus.OK);
				}else {
					throw new CustomException("NOT FOUND !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	
	}
}
