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
import com.gcs.firewall.model.GroupsRules;
import com.gcs.firewall.model.Rules;
import com.gcs.firewall.service.GroupsRulesService;
import com.gcs.firewall.service.GroupsService;
import com.gcs.firewall.service.RulesService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class GroupsRulesController {
	
	@Autowired
	private GroupsRulesService groupsRulesService;
	
	@Autowired
	private GroupsService groupsService;
	
	@Autowired
	private RulesService rulesService;
	
	@GetMapping("/groupsrules")
	public List <GroupsRules> getAllGroupsRules(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				List <Groups> group = groupsService.findGroupsByAccountId(Long.parseLong(account));
				List <Rules> rule = rulesService.findRulesByAccountId(Long.parseLong(account));
				if(!group.isEmpty() && !rule.isEmpty()) {
					return groupsRulesService.getAllGroupsRules();
				}else {
					throw new CustomException("Something went wrong !");
				}
			}else {
				throw new CustomException("Permission Denied !");
			}

		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/groupsrules/rule/{rule_id}")
	public ResponseEntity<List<GroupsRules>> getGroupsRulesByRuleId(@PathVariable(name="rule_id") Long rule_id){
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
				if(String.valueOf(rule.getAccount().getId()).equals(account)) {
					List<GroupsRules> groupsRules = groupsRulesService.findGroupsRulesByRuleId(rule_id);
					return ResponseEntity.ok(groupsRules);
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
	
	@GetMapping("/groupsrules/group/{group_id}")
	public ResponseEntity<List<GroupsRules>> getGroupsRulesByGroupId(@PathVariable(name="group_id") Long group_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Groups group = groupsService.findGroupsById(group_id).get();
				if(String.valueOf(group.getAccount().getId()).equals(account)) {
					List<GroupsRules> groupsRules = groupsRulesService.findGroupsRulesByGroupId(group_id);
					return ResponseEntity.ok(groupsRules);
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
	
	@GetMapping("/groupsrules/group_id/{group_id}")
	public ResponseEntity<List<GroupsRules>> getGroupRuleByGroupId(@PathVariable(name="group_id") Long group_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Groups group = groupsService.findGroupsById(group_id).get();
				if(String.valueOf(group.getAccount().getId()).equals(account)) {
					List<GroupsRules> groupRule=groupsRulesService.getGroupRuleByGroupId(group_id);
					return ResponseEntity.ok(groupRule);
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
	
	@GetMapping("/groupsrules/{rule_id}/{group_id}")
	public ResponseEntity<GroupsRules> getOneGroupRule(@PathVariable(name="rule_id") Long rule_id ,@PathVariable(name="group_id") Long group_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Groups group = groupsService.findGroupsById(group_id).get();
				Rules rule = rulesService.findRulesById(rule_id).get();
				if(String.valueOf(group.getAccount().getId()).equals(account) && String.valueOf(rule.getAccount().getId()).equals(account)) {
					GroupsRules groupRule=groupsRulesService.getOneGroupRule(rule_id, group_id);
					return ResponseEntity.ok(groupRule);
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
	
	@PostMapping("/groupsrules")
	public GroupsRules addGroupsRules(@Validated @RequestBody GroupsRules groupsRules){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				return groupsRulesService.addGroupsRules(groupsRules);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/groupsrules/{id}")
	public ResponseEntity<GroupsRules> updateGroupsRules(@RequestBody GroupsRules groupsRules){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				GroupsRules updateGroupsRules = groupsRulesService.updateGroupsRules(groupsRules);
				return new ResponseEntity<GroupsRules>(updateGroupsRules,HttpStatus.OK);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@DeleteMapping("/groupsrules/{id}")
	public ResponseEntity<?> deleteGroupsRules(@PathVariable("id") Long id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[4];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				GroupsRules GR = groupsRulesService.findGroupsRulesById(id).get();
				if(GR.getGroups().getAccount().getId() == Long.parseLong(account) && GR.getRules().getAccount().getId() == Long.parseLong(account)) {
					groupsRulesService.deleteGroupsRules(id);
					return new ResponseEntity<Object>(HttpStatus.OK);
				}else {
					throw new CustomException("Something went wrong !");
				}
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
}
