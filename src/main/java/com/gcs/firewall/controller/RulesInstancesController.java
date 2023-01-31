package com.gcs.firewall.controller;

import java.util.Date;
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
import com.gcs.firewall.model.Rules;
import com.gcs.firewall.model.RulesInstances;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.script.FirewallScript;
import com.gcs.firewall.service.RulesInstancesService;
import com.gcs.firewall.service.RulesService;
import com.gcs.firewall.service.ServersService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class RulesInstancesController {
	
	@Autowired
	private RulesInstancesService rulesInstancesService;
	
	@Autowired
	private ServersService serversService;
	
	@Autowired
	private RulesService rulesService;
	
	@GetMapping("/rulesinstances")
	public List <RulesInstances> getAllRulesInstances(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return rulesInstancesService.findRulesInstancesList(Long.parseLong(account));
			}else {
				throw new CustomException("Permission Denied !");
			}

		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/rulesinstances/server/{server_id}")
	public ResponseEntity<List<RulesInstances>> getRulesInstancesByServerId(@PathVariable(name="server_id") Long server_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Servers server = serversService.findServersById(server_id).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					List<RulesInstances> rulesInstances=rulesInstancesService.findRulesInstancesByServerId(server_id);
					return ResponseEntity.ok(rulesInstances);
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
	
	@PostMapping("/rulesinstances/server/{server_id}")
	public List<RulesInstances> checkRule(@RequestBody Rules rule,@PathVariable(name="server_id") Long server_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Servers server = serversService.findServersById(server_id).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					List<RulesInstances> rulesInstances=rulesInstancesService.checkRule(server_id, rule.getIp_address(), rule.getProtocol(), rule.getPort(), rule.getAction());
					return rulesInstances;	
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
	
	@GetMapping("/rulesinstances/rule_id/{rule_id}")
	public ResponseEntity<RulesInstances> getRuleInstanceByRuleId(@PathVariable(name="rule_id") Long rule_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Rules rule = rulesService.findRulesById(rule_id).get();
				if(rule.getAccount().getId() == Long.parseLong(account)) {
					RulesInstances rulesInstances=rulesInstancesService.getRuleInstanceByRuleId(rule_id);
					return ResponseEntity.ok(rulesInstances);
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
	
	@GetMapping("/rulesinstances/rule/{rule_id}")
	public ResponseEntity<List<RulesInstances>> getRulesInstancesByRuleId(@PathVariable(name="rule_id") Long rule_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Rules rule = rulesService.findRulesById(rule_id).get();
				if(rule.getAccount().getId() == Long.parseLong(account)) {
					List<RulesInstances> rulesInstances=rulesInstancesService.findRulesInstancesByRuleId(rule_id);
					return ResponseEntity.ok(rulesInstances);
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
	
	@GetMapping("/rulesinstances/{rule_id}/{server_id}")
	public ResponseEntity<RulesInstances> getRulesInstancesList(@PathVariable(name="rule_id") Long rule_id ,@PathVariable(name="server_id") Long server_id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Rules rule = rulesService.findRulesById(rule_id).get();
				Servers server = serversService.findServersById(server_id).get();
				if(rule.getAccount().getId() == Long.parseLong(account) && server.getAccount().getId() == Long.parseLong(account)) {
					RulesInstances ruleInstanceList=rulesInstancesService.getRulesInstancesList(rule_id, server_id);
					return ResponseEntity.ok(ruleInstanceList);
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
	
	@GetMapping("/rulesinstances/{server_id}")
	public String getRulesNumberByStatus(@PathVariable(name="server_id") Long server_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Servers server = serversService.findServersById(server_id).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					return rulesInstancesService.getRuleInstanceNumberByServerId(server_id);
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
	
	@PostMapping("/rulesinstances")
	public RulesInstances addRulesInstances(@Validated @RequestBody RulesInstances rulesInstances){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				return rulesInstancesService.addRulesInstances(rulesInstances);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/rulesinstances/{id}")
	public ResponseEntity<RulesInstances> updateRulesInstances(@RequestBody RulesInstances rulesInstances){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				RulesInstances updateRulesInstances = rulesInstancesService.updateRulesInstances(rulesInstances);
				return new ResponseEntity<RulesInstances>(updateRulesInstances,HttpStatus.OK);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@DeleteMapping("/rulesinstances/{id}")
	public ResponseEntity<?> deleteRulesInstances(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			String valeur1 = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete") && valeur1.contains("delete")) {
				RulesInstances rule_Instance = rulesInstancesService.findRulesInstancesById(id).get();
				if(String.valueOf(rule_Instance.getRules().getAccount().getId()).equals(account)) {
					
					FirewallScript.delete(rule_Instance.getServers().getId(), "delete", rule_Instance.getRules().getId());
					rule_Instance.setEnd_date(new Date());
					rule_Instance.setStatus(4);
					rulesInstancesService.updateRulesInstances(rule_Instance);
					//rulesInstancesService.deleteRulesInstances(id);
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
