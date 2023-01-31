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
import com.gcs.firewall.model.PoliciesInstances;
import com.gcs.firewall.service.PoliciesInstancesService;
import com.gcs.firewall.service.PoliciesService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PoliciesInstancesController {
	
	@Autowired
	private PoliciesInstancesService policiesInstancesService;
	
	@Autowired
	private PoliciesService policiesService;

	@GetMapping("/policiesinstances")
	public List <PoliciesInstances> getAllPoliciesInstances(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return policiesInstancesService.getAllPoliciesInstances();
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/policiesinstances/policy/{policy_id}")
	public ResponseEntity<List<PoliciesInstances>> findPoliciesInstancesList(@PathVariable(name="policy_id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Policies pol = policiesService.findPoliciesById(id).get();
				if(pol.getAccount().getId() == Long.parseLong(account)) {
					List<PoliciesInstances> policiesInstances=policiesInstancesService.findPoliciesInstancesList(id);
					return ResponseEntity.ok(policiesInstances);
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
	
	@GetMapping("/policiesinstances/{policy_id}/{server_id}")
	public ResponseEntity<PoliciesInstances> getOnePolicyInstance(@PathVariable(name="policy_id") Long policy_id ,@PathVariable(name="server_id") Long server_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {		
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				PoliciesInstances policyInstances=policiesInstancesService.getOnePolicyInstance(policy_id, server_id);
				if(String.valueOf(policyInstances.getPolicies().getAccount().getId()).equals(account)) {
					return ResponseEntity.ok(policyInstances);
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
	
	@GetMapping("/policiesinstances/{id}")
	public ResponseEntity<PoliciesInstances> getPoliciesInstancesById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				PoliciesInstances policiesInstances = policiesInstancesService.findPoliciesInstancesById(id).get();
				if(String.valueOf(policiesInstances.getPolicies().getAccount().getId()).equals(account)) {
					return ResponseEntity.ok(policiesInstances);
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
	
	@PostMapping("/policiesinstances")
	public PoliciesInstances addPoliciesInstances(@Validated @RequestBody PoliciesInstances policiesInstances){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				return policiesInstancesService.addPoliciesInstances(policiesInstances);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/policiesinstances/{id}")
	public ResponseEntity<PoliciesInstances> updatePoliciesInstances(@RequestBody PoliciesInstances policiesInstances){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				PoliciesInstances updatepoliciesInstances = policiesInstancesService.updatePoliciesInstances(policiesInstances);
				return new ResponseEntity<PoliciesInstances>(updatepoliciesInstances,HttpStatus.OK);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@DeleteMapping("/policiesinstances/{id}")
	public ResponseEntity<?> deletePoliciesInstances(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				PoliciesInstances policiesInstance = policiesInstancesService.findPoliciesInstancesById(id).get();
				if(String.valueOf(policiesInstance.getPolicies().getAccount().getId()).equals(account)) {
					policiesInstancesService.deletePoliciesInstances(id);
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
