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
import com.gcs.firewall.model.CredentialsAccounts;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.service.CredentialsAccountsService;
import com.gcs.firewall.service.ServersService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CredentialsAccountsController {
	
	@Autowired
	private CredentialsAccountsService credentialsAccountsService;
	
	@Autowired
	private ServersService serversService;
	
	@GetMapping("/credentialsaccounts")
	public ResponseEntity <CredentialsAccounts> getAllCredentialsAccounts(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				CredentialsAccounts credentialsAccounts = credentialsAccountsService.findCredentialsAccountsById(Long.parseLong(account)).get();
				return ResponseEntity.ok(credentialsAccounts);
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@PostMapping("/credentialsaccounts")
	public CredentialsAccounts addCredentialsAccounts(@Validated @RequestBody CredentialsAccounts credentialsAccounts){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				Servers server = serversService.findServersById((long) credentialsAccounts.getSource_id()).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					return credentialsAccountsService.addCredentialsAccounts(credentialsAccounts);
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
	
	@GetMapping("/credentialsaccounts/{id}")
	public ResponseEntity<CredentialsAccounts> getCredentialsAccountsById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				CredentialsAccounts credentialsAccounts = credentialsAccountsService.findCredentialsAccountsById(id).get();
				Servers server = serversService.findServersById((long) credentialsAccounts.getSource_id()).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					return ResponseEntity.ok(credentialsAccounts);
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
	
	@GetMapping("/credentialsaccounts/source/{source_id}")
	public ResponseEntity<List<CredentialsAccounts>> getCredentialsBySourceId(@PathVariable(name="source_id") Long source_id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Servers server = serversService.findServersById(source_id).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					List <CredentialsAccounts> credentialsAccounts = credentialsAccountsService.getCredentialsBySourceId(source_id);
					return ResponseEntity.ok(credentialsAccounts);
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
	
	@PutMapping("/credentialsaccounts/{id}")
	public ResponseEntity<CredentialsAccounts> updateCredentialsAccounts(@RequestBody CredentialsAccounts credentialsAccounts){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				Servers server = serversService.findServersById((long) credentialsAccounts.getSource_id()).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					CredentialsAccounts updateCredentialsAccounts = credentialsAccountsService.updateCredentialsAccounts(credentialsAccounts);
					return new ResponseEntity<CredentialsAccounts>(updateCredentialsAccounts,HttpStatus.OK);
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
	
	@DeleteMapping("/credentialsaccounts/{id}")
	public ResponseEntity<?> deleteCredentialsAccounts(@PathVariable("id") Long id){

		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				CredentialsAccounts CA = credentialsAccountsService.findCredentialsAccountsById(id).get();
				Servers server = serversService.findServersById((long)CA.getSource()).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					CA.setStatus(4);
					credentialsAccountsService.updateCredentialsAccounts(CA);
					//credentialsAccountsService.deleteCredentialsAccounts(id);
					return new ResponseEntity<Object>(HttpStatus.OK);
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
}
