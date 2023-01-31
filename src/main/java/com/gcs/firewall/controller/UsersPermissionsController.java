package com.gcs.firewall.controller;

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
import com.gcs.firewall.model.UsersPermissions;
import com.gcs.firewall.service.UsersPermissionsService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UsersPermissionsController {
	
	@Autowired
	private UsersPermissionsService usersPermissionsService;
	
	/*
	@GetMapping("/userspermissions")
	public List <UsersPermissions> getAllUsersPermissions(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return usersPermissionsService.getAllUsersPermissions();
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	*/
	@GetMapping("/userspermissions/{id}")
	public ResponseEntity<UsersPermissions> getUsersPermissionsById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				UsersPermissions usersPermissions = usersPermissionsService.findUsersPermissionsById(id).get();
				if(usersPermissions.getUsers().getAccount().getId() == Long.parseLong(account_id)) {
					return ResponseEntity.ok(usersPermissions);
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
	
	@GetMapping("/userspermissions/user/{user_id}")
	public ResponseEntity<UsersPermissions> getUsersPermissionsByUserId(@PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				UsersPermissions usersPermissions = usersPermissionsService.findUsersPermissionsByUserId(user_id);
				if(usersPermissions.getUsers().getAccount().getId() == Long.parseLong(account_id)) {
					return ResponseEntity.ok(usersPermissions);			
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
	
	@PostMapping("/userspermissions")
	public UsersPermissions addUsersPermissions(@Validated @RequestBody UsersPermissions usersPermissions){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				if(usersPermissions.getUsers().getAccount().getId() == Long.parseLong(account_id)) {
					return usersPermissionsService.addUsersPermissions(usersPermissions);	
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
	
	@PutMapping("/userspermissions/{id}")
	public ResponseEntity<UsersPermissions> updateUsersPermissions(@RequestBody UsersPermissions usersPermissions){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				if(usersPermissions.getUsers().getAccount().getId() == Long.parseLong(account_id)) {
					UsersPermissions updateUsersPermissions = usersPermissionsService.updateUsersPermissions(usersPermissions);
					return new ResponseEntity<UsersPermissions>(updateUsersPermissions,HttpStatus.OK);
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
	
	@DeleteMapping("/userspermissions/{id}")
	public ResponseEntity<?> deleteUsersPermissions(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				UsersPermissions usersPermission = usersPermissionsService.findUsersPermissionsById(id).get();
				if(String.valueOf(usersPermission.getUsers().getAccount().getId()).equals(account_id)) {
					usersPermissionsService.deleteUsersPermissions(id);
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
