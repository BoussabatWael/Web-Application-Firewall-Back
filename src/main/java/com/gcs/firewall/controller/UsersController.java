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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.gcs.firewall.exceptions.CustomException;
import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Users;
import com.gcs.firewall.service.UsersServices;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UsersController {
	
	@Autowired
	private UsersServices usersService;
	
	@GetMapping("/users")
	public List <Users> getAllUsers(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return usersService.findUsersByStatus(Long.parseLong(account_id));
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/users/all")
	public String getUsersNumber(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return usersService.getUserNumber();
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Users> getUsersById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Users users = usersService.findUsersById(id).get();
				if(String.valueOf(users.getAccount().getId()).equals(account_id)) {
					return ResponseEntity.ok(users);
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
	
	@GetMapping("/users/account/{account_id}/user/{user_id}")
	public ResponseEntity<List<Users>> findUsersByAccountId(@PathVariable(name="account_id") Long account_id,@PathVariable(name="user_id") Long user_id){
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
					List<Users> usersList = usersService.findUsersByAccountId(account_id,user_id);
					return ResponseEntity.ok(usersList);
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
	
	@GetMapping("/users/{username}/{password}")
	public ResponseEntity<Users> getOneUser(@PathVariable(name="username") String username,@PathVariable(name="password") String password){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Users user =  usersService.getOneUser(username, password);
				if(user != null && user.getAccount().getId() == Long.parseLong(account_id)){
					return ResponseEntity.ok(user);
				}
				throw new CustomException("user not found !");
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}

	//@CrossOrigin(allowedHeaders = {"Authorization","Origin"},methods = RequestMethod.GET)
	@GetMapping("/users/username/{username}")
	public Users getUser(@PathVariable(name="username") String username){
		if(BasicAuthInterceptor.TARGET.equals("front")) {
			Users user =  usersService.getUsersList(username);
			return user;
/*
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view" )) {
				Users user =  usersService.getUsersList(username);
				if(String.valueOf(user.getAccount().getId()).equals(account_id)) {
					Users user =  usersService.getUsersList(username);
				}else {
					throw new CustomException("NOT FOUND !");
				}
				}
			else {
				throw new CustomException("Permission Denied !");
			}*/
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@GetMapping("/users/name/{username}")
	public Users getUserByUsername(@PathVariable(name="username") String username){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view" )) {
				Users user =  usersService.getUserByUsername(username);
				return user;

				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	@PostMapping("/users")
	public Users addUsers(@RequestBody Users users){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				Users u = usersService.addUsers1(users);
				return u;
							
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PostMapping("/users/add")
	public Users addUsers(@Validated @RequestParam String users, @RequestParam("file") MultipartFile
			file)throws Exception{		
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				Users u = usersService.addUsers(users,file);
				return u;
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUsers(@RequestParam String users, @PathVariable(name="id") Long id, @RequestParam("file") MultipartFile
			file)throws Exception{
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			
				String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
				//String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
				System.out.println(per);
				String cle = per.split(":")[0];
				String valeur = per.split(":")[1];
				System.out.println(cle);
				System.out.println(valeur);
				if(valeur.contains("edit")) {
					Users u = usersService.updateUsers(users,file);
					return new ResponseEntity<Users>(u,HttpStatus.OK);
				}else {
					throw new CustomException("Permission Denied !");
				}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/users/update/{id}")
	public ResponseEntity<Users> updateUsers(@RequestBody Users users, @PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
				String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
				String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
				System.out.println(per);
				String cle = per.split(":")[0];
				String valeur = per.split(":")[1];
				System.out.println(cle);
				System.out.println(valeur);
				if(valeur.contains("edit")) {
					if(users.getAccount().getId() == Long.parseLong(account_id)) {
						Users updateUsers = usersService.updateUsers1(users);
						return new ResponseEntity<Users>(updateUsers,HttpStatus.OK);	
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
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUsers(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				Users user = usersService.findUsersById(id).get();
				if(String.valueOf(user.getAccount().getId()).equals(account_id)) {
					usersService.deleteUsers(id);
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

