package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.ServersRepo;

@Service
public class ServersServiceImpl implements ServersService{
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private ServersRepo serversRepo;
	
	@Override
	public Servers addServers(Servers servers) {
		//Account account=accountRepo.findById(servers.getAccount().getId()).get();
		//Categories categories= categoriesRepo.findById(servers.getCategories().getId()).get();
		//Providers providers=providersRepo.findById(servers.getProviders().getId()).get();
		//RulesInstances rulesInstances = rulesInstancesRepo.findById(servers.getRulesInstances().g)
		/*if(account==null) {
			return null;
		}/*
		if(categories==null) {
			return null;
		}
		if(providers==null) {
			return null;
		}*/
		//else {
			//servers.setAccount(account);
			//servers.setCategories(categories);
			//servers.setProviders(providers);
			Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
			if(servers.getAccount() == null) {
				servers.setAccount(account);
			}
			if(servers.getStatus() == 0) {
				servers.setStatus(1);
			}
			if(servers.getStart_date() == null) {
				servers.setStart_date(new Date());
			}
			if(servers.getIp_address() == null) {
				servers.setIp_address("");
			}
			return serversRepo.saveAndFlush(servers);
		//}
	}

	@Override
	public List<Servers> getAllServers() {
		// TODO Auto-generated method stub
		return serversRepo.findAll();
	}

	@Override
	public Servers updateServers(Servers servers) {
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(servers.getAccount() == null) {
			servers.setAccount(account);
		}
		if(servers.getStatus() == 0) {
			servers.setStatus(1);
		}
		if(servers.getIp_address() == null) {
			servers.setIp_address("");
		}
		return serversRepo.saveAndFlush(servers);
	}

	@Override
	public Optional<Servers> findServersById(Long id) {
		// TODO Auto-generated method stub
		return serversRepo.findById(id);
	}

	@Override
	public void deleteServers(Long id) {
		serversRepo.deleteById(id);
		
	}

	@Override
	public List<Servers> findServersByStatus() {
		// TODO Auto-generated method stub
		return serversRepo.findServersByStatus();
	}

	@Override
	public List<Servers> findServersList(Long r_id,Long acc_id) {
		// TODO Auto-generated method stub
		return serversRepo.findServersList(r_id,acc_id);
	}

	@Override
	public List<Servers> findServerList(Long policy_id,Long acc_id) {
		// TODO Auto-generated method stub
		return serversRepo.findServerList(policy_id,acc_id);
	}

	@Override
	public List<Servers> findServersByAccountId(Long account_id) {
		// TODO Auto-generated method stub
		return serversRepo.findServersByAccountId(account_id);
	}

	@Override
	public String getServersNumber(Long account_id) {
		// TODO Auto-generated method stub
		return serversRepo.getServersNumber(account_id);
	}

	@Override
	public List<Object[]> getRulesByServer(Long account_id) {
		// TODO Auto-generated method stub
		return serversRepo.getRulesByServer(account_id);
	}

	@Override
	public List<Servers> getUsersServersList(Long user_id, Long account_id) {
		// TODO Auto-generated method stub
		return serversRepo.getUsersServersList(user_id, account_id);
	}

}
