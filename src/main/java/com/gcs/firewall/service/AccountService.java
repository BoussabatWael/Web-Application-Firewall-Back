package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.Account;

public interface AccountService {
	
	public List<Account> findAllAccounts();
	public Account addAccount(Account account) ;
	public Account updateAccount(Account account);
	public Optional<Account> findAccountById(Long id) ;
	public void deleteAccount(Long id) ;
}
