package com.gcs.firewall.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gcs.firewall.exceptions.CustomException;
import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Groups;
import com.gcs.firewall.model.Policies;
import com.gcs.firewall.model.Rules;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.model.Users;
import com.gcs.firewall.model.UsersLogs;
import com.gcs.firewall.service.GroupsService;
import com.gcs.firewall.service.PoliciesService;
import com.gcs.firewall.service.RulesService;
import com.gcs.firewall.service.ServersService;
import com.gcs.firewall.service.UsersLogsService;
import com.gcs.firewall.service.UsersServices;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UsersLogsController {
	
	@Autowired
	private UsersLogsService usersLogsService;
	
	@Autowired
	private UsersServices usersService;
	
	@Autowired
	private ServersService serversService;
	
	@Autowired
	private RulesService rulesService;
	
	@Autowired
	private PoliciesService policiesService;
	
	@Autowired
	private GroupsService groupsService;
	/*
	@GetMapping("/userslogs")
	public List <UsersLogs> getAllUsersLogs(){
		return usersLogsService.getAllUsersLogs();
	}
	*/
	@GetMapping("/userslogs/{id}")
	public ResponseEntity<UsersLogs> getUsersLogsById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				UsersLogs usersLogs = usersLogsService.findUsersLogsById(id).get();
				if(String.valueOf(usersLogs.getUsers().getAccount().getId()).equals(account_id)) {
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/{source}/{user_id}")
	public ResponseEntity<List<UsersLogs>> getUsersLogs(@PathVariable(name="source") Long source,@PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Users user = usersService.findUsersById(user_id).get();
				if(user.getAccount().getId() == Long.parseLong(account_id)) {
					List<UsersLogs> usersLogs=usersLogsService.findUsersLogs(source,user_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/1/{source}/{user_id}")
	public ResponseEntity<List<UsersLogs>> getUsersLogs1(@PathVariable(name="source") Long source,@PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Users user = usersService.findUsersById(user_id).get();
				if(user.getAccount().getId() == Long.parseLong(account_id)) {
					List<UsersLogs> usersLogs=usersLogsService.findUsersLogs1(source,user_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/server_id/{server_id}")
	public ResponseEntity<List<UsersLogs>> getServersUsersLogs(@PathVariable(name="server_id") Long server_id){	
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
					List<UsersLogs> usersLogs=usersLogsService.findServersUsersLogs(server_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/rule_id/{rule_id}")
	public ResponseEntity<List<UsersLogs>> getRulesUsersLogs(@PathVariable(name="rule_id") Long rule_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Rules rule = rulesService.findRulesById(rule_id).get();
				if(rule.getAccount().getId() == Long.parseLong(account_id)) {
					List<UsersLogs> usersLogs=usersLogsService.findRulesUsersLogs(rule_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/group_id/{group_id}")
	public ResponseEntity<List<UsersLogs>> getGroupsUsersLogs(@PathVariable(name="group_id") Long group_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Groups group = groupsService.findGroupsById(group_id).get();
				if(group.getAccount().getId() == Long.parseLong(account_id)) {
					List<UsersLogs> usersLogs=usersLogsService.findGroupsUsersLogs(group_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/policy_id/{policy_id}")
	public ResponseEntity<List<UsersLogs>> getPoliciesUsersLogs(@PathVariable(name="policy_id") Long policy_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Policies policy = policiesService.findPoliciesById(policy_id).get();
				if(policy.getAccount().getId() == Long.parseLong(account_id)) {
					List<UsersLogs> usersLogs=usersLogsService.findPoliciesUsersLogs(policy_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/usr/{user_id}")
	public ResponseEntity<List<UsersLogs>> getUserBy(@PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Users user = usersService.findUsersById(user_id).get();
				if(user.getAccount().getId() == Long.parseLong(account_id)) {
					List<UsersLogs> usersLogs=usersLogsService.findUserLogs(user_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/user/{user_id}")
	public ResponseEntity<List<UsersLogs>> getUsersLogsByUserId(@PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Users user = usersService.findUsersById(user_id).get();
				if(user.getAccount().getId() == Long.parseLong(account_id)) {
					List<UsersLogs> usersLogs=usersLogsService.findUsersLogsByUserId(user_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@GetMapping("/userslogs/last/user/{user_id}")
	public ResponseEntity<List<UsersLogs>> getLastUsersLogsByUserId(@PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(Arrays.toString(per.split(":")));
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Users user = usersService.findUsersById(user_id).get();
				if(user.getAccount().getId() == Long.parseLong(account_id)) {
					List<UsersLogs> usersLogs=usersLogsService.findlastUsersLogsByUserId(user_id);
					return ResponseEntity.ok(usersLogs);
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
	
	@PostMapping("/userslogs")
	public UsersLogs addUsersLogs(@Validated @RequestBody UsersLogs usersLogs){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				if(usersLogs.getUsers().getAccount().getId() == Long.parseLong(account_id)) {
					return usersLogsService.addUsersLogs(usersLogs);
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
	
	@DeleteMapping("/userslogs/{id}")
	public ResponseEntity<?> deleteUsersLogs(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				UsersLogs userlog = usersLogsService.findUsersLogsById(id).get();
				if(String.valueOf(userlog.getUsers().getAccount().getId()).equals(account_id)) {
					usersLogsService.deleteUsersLogs(id);
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
