package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Policies;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.PoliciesRepo;

@Service
public class PoliciesServiceImpl implements PoliciesService{
	
	@Autowired
	private PoliciesRepo policiesRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public Policies addPolicies(Policies policies) {
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(policies.getAccount()==null) {
			policies.setAccount(account);
		}
		if(policies.getStatus() == 0) {
			policies.setStatus(1);
		}
		if(policies.getStart_date() == null) {
			policies.setStart_date(new Date());
		}
		return policiesRepo.save(policies);

	}

	@Override
	public List<Policies> getAllPolicies() {
		return policiesRepo.findAll();
	}

	@Override
	public Policies updatePolicies(Policies policies) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(policies.getAccount()==null) {
			policies.setAccount(account);
		}
		if(policies.getStatus() == 0) {
			policies.setStatus(1);
		}
		if(policies.getStart_date() == null) {
			policies.setStart_date(new Date());
		}
		return policiesRepo.save(policies);
	}

	@Override
	public Optional<Policies> findPoliciesById(Long id) {
		// TODO Auto-generated method stub
		return policiesRepo.findById(id);
	}

	@Override
	public void deletePolicies(Long id) {
		// TODO Auto-generated method stub
		policiesRepo.deleteById(id);
	}

	@Override
	public List<Policies> findPoliciesByStatus() {
		// TODO Auto-generated method stub
		return policiesRepo.findPoliciesByStatus();
	}

	@Override
	public List<Policies> findPoliciesByAccountId(Long account_id) {
		// TODO Auto-generated method stub
		return policiesRepo.findPoliciesByAccountId(account_id);
	}

	@Override
	public String getPoliciesNumber(Long account_id) {
		// TODO Auto-generated method stub
		return policiesRepo.getPoliciesNumber(account_id);
	}

}
