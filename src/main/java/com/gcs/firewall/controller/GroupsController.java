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
import com.gcs.firewall.model.Groups;
import com.gcs.firewall.service.GroupsService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class GroupsController {
	
	@Autowired
	private GroupsService groupsService;
	
	
	@GetMapping("/groups")
	public List <Groups> getAllGroups(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return groupsService.findGroupsByAccountId(Long.parseLong(account));
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/groups/{id}")
	public ResponseEntity<Groups> getGroupsById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Groups groups = groupsService.findGroupsById(id).get();
				if(String.valueOf(groups.getAccount().getId()).equals(account) ) {
					return ResponseEntity.ok(groups);

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
	
	@GetMapping("/groups/account/{account_id}")
	public ResponseEntity<List<Groups>> findGroupsByAccountId(@PathVariable(name="account_id") Long account_id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account) ) {
					List<Groups> groups = groupsService.findGroupsByAccountId(account_id);
					return ResponseEntity.ok(groups);
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
	
	@GetMapping("/groups/rule/{r_id}/{acc_id}")
	public ResponseEntity<List<Groups>> getGroupsList(@PathVariable(name="r_id") Long r_id,@PathVariable(name="acc_id") Long acc_id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(acc_id).equals(account)) {
					List<Groups> groups = groupsService.findGroupsList(r_id,acc_id);
					return ResponseEntity.ok(groups);
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
	
	@PostMapping("/groups")
	public Groups addGroups(@Validated @RequestBody Groups groups){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				return groupsService.addGroups(groups);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@PutMapping("/groups/{id}")
	public ResponseEntity<Groups> updateGroups(@RequestBody Groups groups){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				Groups updateGroups = groupsService.updateGroups(groups);
				return new ResponseEntity<Groups>(updateGroups,HttpStatus.OK);				
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	
	}
	
	@DeleteMapping("/groups/{id}")
	public ResponseEntity<?> deleteGroups(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				Groups group = groupsService.findGroupsById(id).get();
				if(String.valueOf(group.getAccount().getId()).equals(account)) {
					groupsService.deleteGroups(id);
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
