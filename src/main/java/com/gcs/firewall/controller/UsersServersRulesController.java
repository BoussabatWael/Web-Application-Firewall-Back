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
import com.gcs.firewall.model.Rules;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.model.UsersServers;
import com.gcs.firewall.model.UsersServersRules;
import com.gcs.firewall.repository.RulesRepo;
import com.gcs.firewall.repository.UsersServersRepo;
import com.gcs.firewall.service.RulesService;
import com.gcs.firewall.service.ServersService;
import com.gcs.firewall.service.UsersServersRulesService;
import com.gcs.firewall.service.UsersServersService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UsersServersRulesController {
	
	@Autowired
	private UsersServersRulesService usersServersRulesService;
	
	@Autowired
	private ServersService serversService;
	
	@Autowired
	private UsersServersService usersServersService;
	
	@Autowired
	private RulesService rulesService;
	
	@Autowired
	private UsersServersRepo usersServersRepo;
	
	@Autowired
	private RulesRepo rulesRepo;
	
	@GetMapping("/usersserversrules")
	public List <UsersServersRules> getAllUsersServersByStatus(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return usersServersRulesService.findAllUsersServersRules(Long.parseLong(account_id));
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/usersserversrules/{server_id}")
	public List <UsersServersRules> getUsersServersRulesList(@PathVariable(name="server_id") Long server_id){
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
					return usersServersRulesService.getUsersServersRulesList(server_id);
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
	
	@GetMapping("/usersserversrules/usr_srv/{user_server_id}")
	public List <UsersServersRules> getUserSeverRuleByUserServerID(@PathVariable(name="user_server_id") Long user_server_id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				UsersServers user_server = usersServersService.findUsersServersById(user_server_id).get();
				if(user_server.getServers().getAccount().getId() == Long.parseLong(account_id) && user_server.getUsers().getAccount().getId() == Long.parseLong(account_id)) {
					return usersServersRulesService.getUserSeverRuleByUserServerID(user_server_id);
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
	
	@GetMapping("/usersserversrules/{server_id}/{rule_id}")
	public UsersServersRules getUserServerRule(@PathVariable(name="server_id") Long server_id, @PathVariable(name="rule_id") Long rule_id){	
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
				Rules rule = rulesService.findRulesById(rule_id).get();
				if(server.getAccount().getId() == Long.parseLong(account_id) && rule.getAccount().getId() == Long.parseLong(account_id)) {
					return usersServersRulesService.getUserServerRule(server_id, rule_id);
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
	
	@PostMapping("/usersserversrules")
	public UsersServersRules addUsersServersRules(@RequestBody UsersServersRules usersServersRules){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				UsersServers user_server = usersServersRepo.findById(usersServersRules.getUsersServers().getId()).get();
				Rules rule = rulesRepo.findById(usersServersRules.getRules().getId()).get();
				if(rule.getAccount().getId() == Long.parseLong(account_id)  && user_server.getUsers().getAccount().getId() == Long.parseLong(account_id)) {
					UsersServersRules usr = usersServersRulesService.addUsersServersRules(usersServersRules);
					return usr;
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
	
	@PutMapping("/usersserversrules/{id}")
	public ResponseEntity<UsersServersRules> updateUsersServersRules(@RequestBody UsersServersRules usersServersRules, @PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				UsersServersRules user_server_rule = usersServersRulesService.findUsersServersRulesById(id).get();
				if(user_server_rule.getUsersServers().getUsers().getAccount().getId() == Long.parseLong(account_id) && user_server_rule.getUsersServers().getServers().getAccount().getId() == Long.parseLong(account_id) && usersServersRules.getRules().getAccount().getId() == Long.parseLong(account_id)) {
					UsersServersRules updateUsersServersRules = usersServersRulesService.updateUsersServersRules(usersServersRules);
					return new ResponseEntity<UsersServersRules>(updateUsersServersRules,HttpStatus.OK);
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
	
	@DeleteMapping("/usersserversrules/{id}")
	public ResponseEntity<?> deleteUsersServersRules(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				UsersServersRules user_server_rule = usersServersRulesService.findUsersServersRulesById(id).get();
				if(String.valueOf(user_server_rule.getRules().getAccount().getId()).equals(account_id)) {
					user_server_rule.setStatus(4);
					user_server_rule.setEnd_date(new Date());
					usersServersRulesService.updateUsersServersRules(user_server_rule);
					//usersServersRulesService.deleteUsersServersRules(id);
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
