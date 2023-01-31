package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Rules;
import com.gcs.firewall.model.RulesInstances;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.RulesInstancesRepo;
import com.gcs.firewall.repository.RulesRepo;
import com.gcs.firewall.repository.ServersRepo;

@Service
public class RulesInstancesServiceImpl implements RulesInstancesService{
	
	@Autowired
	private ServersRepo serversRepo;
	
	@Autowired
	private RulesRepo rulesRepo;
	
	@Autowired
	private RulesInstancesRepo rulesInstancesRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public RulesInstances addRulesInstances(RulesInstances rulesInstances) {
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		Rules rules=rulesRepo.findById(rulesInstances.getRules().getId()).get();
		Servers servers= serversRepo.findById(rulesInstances.getServers().getId()).get();
		if(rules == null || rules.getAccount() != account) {
			return null;
		}
		if(servers == null || servers.getAccount() != account) {
			return null;
		}
		else {
			rulesInstances.setRules(rules);
			rulesInstances.setServers(servers);
			if(rulesInstances.getStatus() == 0) {
				rulesInstances.setStatus(1);
			}
			if(rulesInstances.getStart_date() == null) {
				rulesInstances.setStart_date(new Date());
			}
			return rulesInstancesRepo.save(rulesInstances);
		}
		
	}

	@Override
	public List<RulesInstances> getAllRulesInstances() {
		return rulesInstancesRepo.findAll();	
	}

	@Override
	public RulesInstances updateRulesInstances(RulesInstances rulesInstances) {
		// TODO Auto-generated method stub
		if(rulesInstances.getStatus() == 0) {
			rulesInstances.setStatus(1);
		}
		if(rulesInstances.getStart_date() == null) {
			rulesInstances.setStart_date(new Date());
		}
		return rulesInstancesRepo.save(rulesInstances);
	}

	@Override
	public Optional<RulesInstances> findRulesInstancesById(Long id) {
		// TODO Auto-generated method stub
		return rulesInstancesRepo.findById(id);
	}

	@Override
	public void deleteRulesInstances(Long id) {
		// TODO Auto-generated method stub
		rulesInstancesRepo.deleteById(id);
	}

	@Override
	public List<RulesInstances> findRulesInstancesByServerId(Long server_id) {
		return rulesInstancesRepo.findRulesInstancesByServerId(server_id);
	}

	@Override
	public List<RulesInstances> findRulesInstancesByRuleId(Long rule_id) {
		// TODO Auto-generated method stub
		return rulesInstancesRepo.findRulesInstancesByRuleId(rule_id);
	}

	@Override
	public RulesInstances getRulesInstancesList(Long rule_id, Long server_id) {
		// TODO Auto-generated method stub
		return rulesInstancesRepo.getRulesInstancesList(rule_id, server_id);
	}

	@Override
	public RulesInstances getRuleInstanceByRuleId(Long rule_id) {
		// TODO Auto-generated method stub
		return rulesInstancesRepo.getRuleInstanceByRuleId(rule_id);
	}

	@Override
	public String getRuleInstanceNumberByServerId(Long server_id) {
		// TODO Auto-generated method stub
		return rulesInstancesRepo.getRulesInstancesNumberByServerId(server_id);
	}

	@Override
	public List<RulesInstances> checkRule(Long server_id, String ip_address, String protocol, String port,
			String action) {
		// TODO Auto-generated method stub
		if(ip_address == null) {
			ip_address ="";
		}
		return rulesInstancesRepo.checkRule(server_id, ip_address, protocol, port, action);
	}

	@Override
	public List<RulesInstances> findRulesInstancesList(Long account_id) {
		// TODO Auto-generated method stub
		return rulesInstancesRepo.findRulesInstancesList(account_id);
	}

	

}
