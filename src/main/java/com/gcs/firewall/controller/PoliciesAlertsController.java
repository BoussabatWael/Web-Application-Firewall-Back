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
import com.gcs.firewall.model.PoliciesAlerts;
import com.gcs.firewall.service.PoliciesAlertsService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PoliciesAlertsController {
	
	@Autowired
	private PoliciesAlertsService policiesAlertsService;
	
	@GetMapping("/policiesalerts")
	public List <PoliciesAlerts> getAllPoliciesAlerts(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return policiesAlertsService.getAllPoliciesAlerts();
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/policiesalerts/{id}")
	public ResponseEntity<PoliciesAlerts> getPoliciesAlertsById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				PoliciesAlerts policieAlert = policiesAlertsService.findPoliciesAlertsById(id).get();
				if(String.valueOf(policieAlert.getPolicies().getAccount().getId()).equals(account)) {
					return ResponseEntity.ok(policieAlert);
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
	
	@PostMapping("/policiesalerts")
	public PoliciesAlerts addPoliciesAlerts(@Validated @RequestBody PoliciesAlerts policiesAlerts){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
					return policiesAlertsService.addPoliciesAlerts(policiesAlerts);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/policiesalerts/{id}")
	public ResponseEntity<PoliciesAlerts> updatePoliciesAlerts(@RequestBody PoliciesAlerts policiesAlerts){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
					PoliciesAlerts updatepoliciesAlerts = policiesAlertsService.updatePoliciesAlerts(policiesAlerts);
					return new ResponseEntity<PoliciesAlerts>(updatepoliciesAlerts,HttpStatus.OK);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@DeleteMapping("/policiesalerts/{id}")
	public ResponseEntity<?> deletePoliciesAlerts(@PathVariable("id") Long id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				PoliciesAlerts policieAlert = policiesAlertsService.findPoliciesAlertsById(id).get();
				if(String.valueOf(policieAlert.getPolicies().getAccount().getId()).equals(account)) {
					policiesAlertsService.deletePoliciesAlerts(id);
					return new ResponseEntity<Object>(HttpStatus.OK);
				}else {
					throw new CustomException("NOT FOUND !");
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
}
