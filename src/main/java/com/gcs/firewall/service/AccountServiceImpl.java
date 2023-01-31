package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}
	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepo.save(account);
	}
	@Override
	public Optional<Account> findAccountById(Long id) {
		// TODO Auto-generated method stub
		return accountRepo.findById(id);
	}
	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		accountRepo.deleteById(id);
	}
	@Override
	public Account addAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepo.saveAndFlush(account);
	}
	
}
