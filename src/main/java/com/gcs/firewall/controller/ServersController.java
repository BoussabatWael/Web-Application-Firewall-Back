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
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.service.ServersService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ServersController {
	
	@Autowired
	private ServersService serversService;
	
	@GetMapping("/servers")
	public List <Servers> getAllServers(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				return serversService.findServersByAccountId(Long.parseLong(account));
			}else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
	}
	
	@GetMapping("/servers/{account_id}/all")
	public String getServersNumber(@PathVariable(name="account_id") Long account_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					return serversService.getServersNumber(account_id);
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
	
	@GetMapping("/servers/rules/{account_id}")
	public List<Object[]> getRulesByServer(@PathVariable(name="account_id") Long account_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					return serversService.getRulesByServer(account_id);
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
	
	@GetMapping("/servers/{id}")
	public ResponseEntity<Servers> getServersById(@PathVariable(name="id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account_id = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				Servers servers = serversService.findServersById(id).get();
				if(String.valueOf(servers.getAccount().getId()).equals(account_id)) {
					return ResponseEntity.ok(servers);
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
	
	@GetMapping("/servers/account/{account_id}")
	public ResponseEntity<List<Servers>> findServersByAccountId(@PathVariable(name="account_id") Long account_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(account_id).equals(account)) {
					List<Servers> servers = serversService.findServersByAccountId(account_id);
					return ResponseEntity.ok(servers);
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
	
	@GetMapping("/servers/rule/{r_id}/{acc_id}")
	public ResponseEntity<List<Servers>> getServersList(@PathVariable(name="r_id") Long r_id,@PathVariable(name="acc_id") Long acc_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[3];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(acc_id).equals(account)) {
					List<Servers> servers = serversService.findServersList(r_id,acc_id);
					return ResponseEntity.ok(servers);
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
	
	@GetMapping("/servers/policy/{policy_id}/{acc_id}")
	public ResponseEntity<List<Servers>> getServerList(@PathVariable(name="policy_id") Long policy_id,@PathVariable(name="acc_id") Long acc_id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[5];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("view")) {
				if(String.valueOf(acc_id).equals(account)) {
					List<Servers> servers = serversService.findServerList(policy_id,acc_id);
					return ResponseEntity.ok(servers);
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
	
	@GetMapping("/servers/user/{user_id}/{account_id}")
	public ResponseEntity<List<Servers>> getUsersServerList(@PathVariable(name="user_id") Long user_id,@PathVariable(name="account_id") Long account_id){
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
					List<Servers> servers = serversService.getUsersServersList(user_id,account_id);
					return ResponseEntity.ok(servers);
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
	
	@PostMapping("/servers")
	public Servers addUsers(@Validated @RequestBody Servers servers){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
		
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
				return serversService.addServers(servers);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@PutMapping("/servers/{id}")
	public ResponseEntity<Servers> updateServer(@RequestBody Servers servers){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				Servers updateServers = serversService.updateServers(servers);
				return new ResponseEntity<Servers>(updateServers,HttpStatus.OK);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	
	@DeleteMapping("/servers/{id}")
	public ResponseEntity<?> deleteServer(@PathVariable("id") Long id){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String account = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[2];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
				Servers server = serversService.findServersById(id).get();
				if(server.getAccount().getId() == Long.parseLong(account)) {
					serversService.deleteServers(id);
					return new ResponseEntity<Object>(HttpStatus.OK);
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
}
