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
import com.gcs.firewall.model.Policies;
import com.gcs.firewall.service.PoliciesService;


@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PoliciesController {
	
	@Autowired
	private PoliciesService policiesService;
	
	@GetMapping("/policies")
	public List <Policies> getAllPolicies(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return policiesService.findPoliciesByAccountId(Long.parseLong(account));
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/policies/{id}")
	public ResponseEntity<Policies> getPoliciesById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {	
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Policies policie = policiesService.findPoliciesById(id).get();
				if(String.valueOf(policie.getAccount().getId()).equals(account)) {
					return ResponseEntity.ok(policie);
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
	
	@GetMapping("/policies/{account_id}/all")
	public String getRulesNumber(@PathVariable(name="account_id") Long account_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					return policiesService.getPoliciesNumber(account_id);
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
	
	@GetMapping("/policies/account/{account_id}")
	public ResponseEntity<List<Policies>> findPoliciesByAccountId(@PathVariable(name="account_id") Long account_id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					List<Policies> policies = policiesService.findPoliciesByAccountId(account_id);
					return ResponseEntity.ok(policies);
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
	
	@PostMapping("/policies")
	public Policies addPolicies(@Validated @RequestBody Policies policies){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				return policiesService.addPolicies(policies);
				
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}	
	}
	
	@PutMapping("/policies/{id}")
	public ResponseEntity<Policies> updatePolicies(@RequestBody Policies policies){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				Policies updatepolicies = policiesService.updatePolicies(policies);
				return new ResponseEntity<Policies>(updatepolicies,HttpStatus.OK);	
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@DeleteMapping("/policies/{id}")
	public ResponseEntity<?> deletePolicies(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				Policies policie = policiesService.findPoliciesById(id).get();
				if(String.valueOf(policie.getAccount().getId()).equals(account)) {
					policiesService.deletePolicies(id);
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
