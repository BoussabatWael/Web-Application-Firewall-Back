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
import com.gcs.firewall.model.Account;
import com.gcs.firewall.service.AccountService;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
		
	@GetMapping("/accounts")
	public List <Account> getAllAccounts(){
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String accountt = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(per);
			if(valeur.contains("view")) {
				
				return accountService.findAllAccounts();
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	@PostMapping("/accounts")
	public Account addAccount(@Validated @RequestBody Account account){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String accountt = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("create")) {
					return accountService.addAccount(account);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}else {
			throw new CustomException("Key type error !");
		}
		
	}
	@GetMapping("/accounts/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable(name="id") Long id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")){
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String accountt = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("list")) {
				if(String.valueOf(id).equals(accountt)) {
					Account account = accountService.findAccountById(id).get();
					return ResponseEntity.ok(account);
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
	
	@PutMapping("/accounts/{id}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			//String accountt = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("edit")) {
					Account updateAccount = accountService.updateAccount(account);
					return new ResponseEntity<Account>(updateAccount,HttpStatus.OK);
				}
			else {
				throw new CustomException("Permission Denied !");
			}
		}
		else {
			throw new CustomException("Key type error !");
		}
	}
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id){	
		if(!BasicAuthInterceptor.TARGET.equals("front")) {
			String per = BasicAuthInterceptor.GLOBAL_PERMISSIONS;
			String accountt = BasicAuthInterceptor.GLOBAL_ACCOUNT;
			System.out.println(per);
			String cle = per.split(":")[0];
			String valeur = per.split(":")[1];
			System.out.println(cle);
			System.out.println(valeur);
			if(valeur.contains("delete")) {
				if(String.valueOf(id).equals(accountt)) {
					accountService.deleteAccount(id);
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
