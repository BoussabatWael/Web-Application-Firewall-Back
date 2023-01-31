package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Providers;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.ProvidersRepo;

@Service
public class ProvidersServiceImpl implements ProvidersService{
	
	@Autowired
	private ProvidersRepo providersRepo;
	
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public Providers addProviders(Providers providers) {
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		//Categories categories= categoriesRepo.findById(providers.getCategories().getId()).get();
		if(providers.getAccount()==null) {
			providers.setAccount(account);;
		}
		if(providers.getStatus() == 0) {
			providers.setStatus(1);
		}
		if(providers.getStart_date() == null) {
			providers.setStart_date(new Date());
		}	
		return providersRepo.saveAndFlush(providers);

	}

	@Override
	public List<Providers> getAllProviders() {
		// TODO Auto-generated method stub
		return providersRepo.findAll();
	}

	@Override
	public Providers updateProviders(Providers providers) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		//Categories categories= categoriesRepo.findById(providers.getCategories().getId()).get();
		if(providers.getAccount()==null) {
			providers.setAccount(account);;
		}
		if(providers.getStatus() == 0) {
			providers.setStatus(1);
		}
		if(providers.getStart_date() == null) {
			providers.setStart_date(new Date());
		}
		return providersRepo.save(providers);
	}

	@Override
	public Optional<Providers> findProvidersById(Long id) {
		// TODO Auto-generated method stub
		return providersRepo.findById(id);
	}

	@Override
	public void deleteProviders(Long id) {
		// TODO Auto-generated method stub
		providersRepo.deleteById(id);
	}

	@Override
	public List<Providers> getProvidersList(Long account_id) {
		// TODO Auto-generated method stub
		return providersRepo.getProvidersList(account_id);
	}

}
