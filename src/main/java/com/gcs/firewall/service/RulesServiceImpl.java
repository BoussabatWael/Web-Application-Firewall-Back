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
import com.gcs.firewall.script.FirewallScript;

@Service
public class RulesServiceImpl implements RulesService {
	
	@Autowired
	private ServersRepo serversRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private RulesRepo rulesRepo;
	
	@Autowired
	private RulesInstancesRepo rulesInstancesRepo;
	
	@Override
	public Rules addRules(Rules rules) {
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		if(rules.getAccount() == null) {
			rules.setAccount(account);
		}
		if(rules.getStatus() == 0) {
			rules.setStatus(1);
		}
		if(rules.getStart_date() == null) {
			rules.setStart_date(new Date());
		}
		if(rules.getIp_address() == null) {
			rules.setIp_address("");
		}
		return rulesRepo.saveAndFlush(rules);
	}

	@Override
	public List<Rules> getAllRules() {
		return rulesRepo.findAll();
	}

	@Override
	public Rules updateRules(Rules rules) {
		// TODO Auto-generated method stub
		if(rules.getStatus() == 0) {
			rules.setStatus(1);
		}
		if(rules.getStart_date() == null) {
			rules.setStart_date(new Date());
		}
		if(rules.getIp_address() == null) {
			rules.setIp_address("");
		}
		return rulesRepo.saveAndFlush(rules);
	}

	@Override
	public Optional<Rules> findRulesById(Long id) {
		// TODO Auto-generated method stub
		return rulesRepo.findById(id);
	}

	@Override
	public void deleteRules(Long id) {
		rulesRepo.deleteById(id);
	}

	@Override
	public List<Rules> findRulesByStatus() {
		// TODO Auto-generated method stub
		return rulesRepo.findRulesByStatus();
	}

	@Override
	public List<Rules> findRulesByAccountId(Long account_id) {
		// TODO Auto-generated method stub
		return rulesRepo.findRulesByAccountId(account_id);
	}

	@Override
	public String getRulesNumber(Long account_id) {
		// TODO Auto-generated method stub
		return rulesRepo.getRulesNumber(account_id);
	}

	@Override
	public String getRulesNumberByStatus(Long status, Long account_id) {
		// TODO Auto-generated method stub
		return rulesRepo.getRulesNumberByStatus(status, account_id);
	}

	@Override
	public List<Object[]> getRulesByMonth(Long account_id) {
		// TODO Auto-generated method stub
		return rulesRepo.getRulesByMonth(account_id);
	}

	@Override
	public List<Rules> getRulesUsersServers(Long server_id, Long account_id, Long user_id) {
		// TODO Auto-generated method stub
		return rulesRepo.getRulesUsersServers(server_id, account_id, user_id);
	}

	@Override
	public List<Rules> getUsersServersRulesList(Long server_id, Long account_id,Long user_id) {
		// TODO Auto-generated method stub
		return rulesRepo.getUsersServersRulesList(server_id, account_id, user_id);
	}

	@Override
	public Rules addRules1(Rules rules, String server_id) {
		// TODO Auto-generated method stub
		if(rules.getIp_address() == null) {
			rules.setIp_address("");
		}
		if(rules.getAccount() == null) {
			Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
			rules.setAccount(account);
		}
		if(rules.getStatus() ==0) {
			rules.setStatus(1);
		}
		if(rules.getStart_date() == null) {
			rules.setStart_date(new Date());
		}
		FirewallScript.add(Long.parseLong(server_id), "add", rules.getIp_address(), rules.getProtocol(), rules.getPort(), rules.getAction());
		Rules rule = rulesRepo.saveAndFlush(rules);
		Servers server = serversRepo.getById(Long.parseLong(server_id));
		RulesInstances RI = new RulesInstances();
		RI.setRules(rule);
		RI.setServers(server);
		RI.setStatus(1);
		RI.setStart_date(new Date());
		rulesInstancesRepo.saveAndFlush(RI);
		return rule;
	}

	@Override
	public Rules updateRules1(Rules rules, String server_id) {
		// TODO Auto-generated method stub
		if(rules.getIp_address() == null) {
			rules.setIp_address("");
		}
		if(rules.getAccount() == null) {
			Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
			rules.setAccount(account);
		}

		FirewallScript.edit(Long.parseLong(server_id), "edit", rules.getId(), rules.getProtocol(), rules.getIp_address(), rules.getPort(), rules.getAction());
		return rulesRepo.saveAndFlush(rules);
	}

	@Override
	public void deleteRules1(Long id, String server_id) {
		// TODO Auto-generated method stub
		FirewallScript.delete(Long.parseLong(server_id), "delete", id);
		rulesRepo.deleteById(id);
	}


}
