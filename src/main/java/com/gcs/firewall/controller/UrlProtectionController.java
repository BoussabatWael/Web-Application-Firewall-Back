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
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.model.UrlProtection;
import com.gcs.firewall.service.ServersService;
import com.gcs.firewall.service.UrlProtectionService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UrlProtectionController {
	
	@Autowired
	private UrlProtectionService urlProtectionService;
	
	@Autowired
	private ServersService serversService;
	
	@GetMapping("/urlprotection/server/{server_id}")
	public List <UrlProtection> getAllUsers(@PathVariable(name="server_id") Long server_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Servers server = serversService.findServersById(server_id).get();
				if(server.getAccount().getId() == Long.parseLong(account_id)) {
					return urlProtectionService.getURLByServer(server_id);
				}else {
					throw new CustomException("Something went wrong !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@GetMapping("/urlprotection/{id}")
	public ResponseEntity<UrlProtection> getUrlProtectionById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				UrlProtection urlProtection = urlProtectionService.findUrlProtectionById(id).get();
				if(urlProtection.getServer().getAccount().getId() == Long.parseLong(account_id)) {
					return ResponseEntity.ok(urlProtection);
				}else {
					throw new CustomException("Something went wrong !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PostMapping("/urlprotection")
	public UrlProtection addUrlProtection(@Validated @RequestBody UrlProtection urlProtection){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				if(urlProtection.getServer().getAccount().getId() == Long.parseLong(account_id)) {
					return urlProtectionService.addUrlProtections(urlProtection);
				}else {
					throw new CustomException("Something went wrong !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@PutMapping("/urlprotection/{id}")
	public ResponseEntity<UrlProtection> updateUrlProtection(@RequestBody UrlProtection urlProtection){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				if(urlProtection.getServer().getAccount().getId() == Long.parseLong(account_id)) {
					UrlProtection updateUrlProtection = urlProtectionService.updateUrlProtection(urlProtection);
					return new ResponseEntity<UrlProtection>(updateUrlProtection,HttpStatus.OK);
				}else {
					throw new CustomException("Something went wrong !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@DeleteMapping("/urlprotection/{id}")
	public ResponseEntity<?> deleteUrlProtection(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				UrlProtection urlProtection = urlProtectionService.findUrlProtectionById(id).get();
				Servers server = serversService.findServersById(urlProtection.getServer().getAccount().getId()).get();
				if(String.valueOf(server.getAccount().getId()).equals(account_id)) {
					urlProtectionService.deleteUrlProtection(id);
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
