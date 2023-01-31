package com.gcs.firewall.controller;


import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.gcs.firewall.model.Users;
import com.gcs.firewall.model.UsersServers;
import com.gcs.firewall.service.UsersServersService;
import com.gcs.firewall.service.UsersServices;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UsersServersController {
	
	@Autowired
	private UsersServersService usersServersService;
	
	@Autowired
	private UsersServices usersService;
	
	@GetMapping("/usersservers")
	public List <UsersServers> getAllUsersServersByStatus(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return usersServersService.findUsersServersList(Long.parseLong(account_id));
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/usersservers/id/{id}")
	public ResponseEntity<UsersServers> getUsersServersByID(@PathVariable(name="id") Long id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				UsersServers uservers = usersServersService.findUsersServersById(id).get();
				if(uservers.getUsers().getAccount().getId() == Long.parseLong(account_id) && uservers.getServers().getAccount().getId() == Long.parseLong(account_id)) {
					return ResponseEntity.ok(uservers);
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
	
	@GetMapping("/usersservers/user/{user_id}/server/{server_id}")
	public UsersServers getUsersServersByUserServerID(@PathVariable(name="user_id") Long user_id,@PathVariable(name="server_id") Long server_id){		
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				UsersServers uservers = usersServersService.findUsersServersByUserServerID(user_id, server_id);
				if(uservers.getUsers().getAccount().getId() == Long.parseLong(account_id) && uservers.getServers().getAccount().getId() == Long.parseLong(account_id)) {
					return uservers;

				}else {
					throw new CustomException("Error !");
				}
				
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@GetMapping("/usersservers/{user_id}")
	public List <UsersServers> getAllUsersServersByUserID(@PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Users user = usersService.findUsersById(user_id).get();
				if(user.getAccount().getId() == Long.parseLong(account_id)) {
					return usersServersService.findUsersServersByUserID(user_id);
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
	
	@PostMapping("/usersservers")
	public UsersServers addUsersServers(@RequestBody UsersServers usersServers){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				UsersServers us = usersServersService.addUsersServers(usersServers);
				return us;
				
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/usersservers/{id}")
	public ResponseEntity<UsersServers> updateUsersServers(@RequestBody UsersServers usersServers, @PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				UsersServers updateUsersServers = usersServersService.updateUsersServers(usersServers);
				return new ResponseEntity<UsersServers>(updateUsersServers,HttpStatus.OK);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@DeleteMapping("/usersservers/{id}")
	public ResponseEntity<?> deleteUsersServers(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				UsersServers uservers = usersServersService.findUsersServersById(id).get();
				if(String.valueOf(uservers.getUsers().getAccount().getId()).equals(account_id)) {
					uservers.setStatus(4);
					uservers.setEnd_date(new Date());
					usersServersService.updateUsersServers(uservers);
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
