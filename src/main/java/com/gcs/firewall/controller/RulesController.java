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
import com.gcs.firewall.model.Rules;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.service.RulesService;
import com.gcs.firewall.service.ServersService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class RulesController {
	
	@Autowired
	private RulesService rulesService;
	
	@Autowired
	private ServersService serversService;
	

	@GetMapping("/rules")
	public List <Rules> getAllRules(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return rulesService.findRulesByAccountId(Long.parseLong(account));
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/rules/{account_id}/all")
	public String getRulesNumber(@PathVariable(name="account_id") Long account_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					return rulesService.getRulesNumber(account_id);
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
	
	@GetMapping("/rules/bymonth/{account_id}")
	public List<Object[]> getRulesByMonth(@PathVariable(name="account_id") Long account_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					return rulesService.getRulesByMonth(account_id);
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
	
	@GetMapping("/rules/status/{status}/account/{account_id}")
	public String getRulesNumberByStatus(@PathVariable(name="status") Long status, @PathVariable(name="account_id") Long account_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					return rulesService.getRulesNumberByStatus(status,account_id);
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
	/*@GetMapping("/list")
	public Page <Rules> showPage(@RequestParam(defaultValue="0") int page){
		return rulesRepo.findAll(new PageRequest(page,5));
	}*/
	@GetMapping("/rules/account/{account_id}")
	public ResponseEntity<List<Rules>> getRulesByAccountId(@PathVariable(name="account_id") Long account_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					List<Rules> rules = rulesService.findRulesByAccountId(account_id);
					return ResponseEntity.ok(rules);
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
	
	@GetMapping("/rules/sid/{server_id}/accid/{account_id}/usr/{user_id}")
	public ResponseEntity<List<Rules>> getRulesUsersServers(@PathVariable(name="server_id") Long server_id, @PathVariable(name="account_id") Long account_id, @PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					List<Rules> rules = rulesService.getRulesUsersServers(server_id, account_id, user_id);
					return ResponseEntity.ok(rules);
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
	
	@GetMapping("/rules/server/{server_id}/account/{account_id}/user/{user_id}")
	public ResponseEntity<List<Rules>> getUsersServersRulesList(@PathVariable(name="server_id") Long server_id, @PathVariable(name="account_id") Long account_id, @PathVariable(name="user_id") Long user_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					List<Rules> rules = rulesService.getUsersServersRulesList(server_id, account_id, user_id);
					return ResponseEntity.ok(rules);
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
	
	@GetMapping("/rules/{id}")
	public ResponseEntity<Rules> getRulesById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Rules rules = rulesService.findRulesById(id).get();
				if(String.valueOf(rules.getAccount().getId()).equals(account)) {
					return ResponseEntity.ok(rules);
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
	
	@PostMapping("/rules")
	public Rules addRules(@Validated @RequestBody Rules rules){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				return rulesService.addRules(rules);
				
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	@PostMapping("/rules/add/{server_id}")
	public Rules addRules1(@Validated @RequestBody Rules rules,@PathVariable(name="server_id") String server_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				Servers server = serversService.findServersById(Long.parseLong(server_id)).get();
				if(server.getAccount().getId() == Long.parseLong(account) || rules.getProtocol() != null || rules.getAction() != null) {
					return rulesService.addRules1(rules,server_id);
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
	
	@PutMapping("/rules/{id}")
	public ResponseEntity<Rules> updateRules(@RequestBody Rules rules){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				Rules updateRules = rulesService.updateRules(rules);
				return new ResponseEntity<Rules>(updateRules,HttpStatus.OK);					
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/rules/{id}/{server_id}")
	public ResponseEntity<Rules> updateRules(@RequestBody Rules rules,@PathVariable(name="server_id") String server_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				Servers server = serversService.findServersById(Long.parseLong(server_id)).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					Rules updateRules = rulesService.updateRules1(rules,server_id);
					return new ResponseEntity<Rules>(updateRules,HttpStatus.OK);
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
	
	@DeleteMapping("/rules/{id}")
	public ResponseEntity<?> deleteRules(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				Rules rule = rulesService.findRulesById(id).get();
				if(String.valueOf(rule.getAccount().getId()).equals(account)) {
					rulesService.deleteRules(id);
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
	
	@DeleteMapping("/rules/{id}/{server_id}")
	public ResponseEntity<?> deleteRules(@PathVariable("id") Long id,@PathVariable(name="server_id") String server_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				Rules rule = rulesService.findRulesById(id).get();
				Servers server = serversService.findServersById(Long.parseLong(server_id)).get();
				if(String.valueOf(rule.getAccount().getId()).equals(account) && String.valueOf(server.getAccount().getId()).equals(account)) {
					rulesService.deleteRules1(id,server_id);
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
