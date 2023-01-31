package com.gcs.firewall.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Policies;
import com.gcs.firewall.model.PoliciesInstances;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.PoliciesInstancesRepo;
import com.gcs.firewall.repository.PoliciesRepo;
import com.gcs.firewall.repository.ServersRepo;

@Service
public class PoliciesInstancesServiceImpl implements PoliciesInstancesService{
	
	@Autowired
	private ServersRepo serversRepo;
	
	@Autowired
	private PoliciesRepo policiesRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private PoliciesInstancesRepo policiesInstancesRepo;
	
	@Override
	public PoliciesInstances addPoliciesInstances(PoliciesInstances policiesInstances) {
		Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
		Servers servers = serversRepo.findById(policiesInstances.getServers().getId()).get();
		Policies policies = policiesRepo.findById(policiesInstances.getPolicies().getId()).get();
		
		if(servers==null || servers.getAccount() != account) {
			return null;
		}
		if(policies==null|| servers.getAccount() != account) {
			return null;
		}
		else {
			policiesInstances.setPolicies(policies);
			policiesInstances.setServers(servers);
			if(policiesInstances.getStatus() == 0) {
				policiesInstances.setStatus(1);
			}
			if(policiesInstances.getStart_date() == null) {
				policiesInstances.setStart_date(new Date());
			}
			return policiesInstancesRepo.save(policiesInstances);
		}
	}

	@Override
	public List<PoliciesInstances> getAllPoliciesInstances() {
		// TODO Auto-generated method stub
		return policiesInstancesRepo.findAll();
	}

	@Override
	public PoliciesInstances updatePoliciesInstances(PoliciesInstances policiesInstances) {
		// TODO Auto-generated method stub
		if(policiesInstances.getStatus() == 0) {
			policiesInstances.setStatus(1);
		}
		if(policiesInstances.getStart_date() == null) {
			policiesInstances.setStart_date(new Date());
		}
		return policiesInstancesRepo.saveAndFlush(policiesInstances);
	}

	@Override
	public Optional<PoliciesInstances> findPoliciesInstancesById(Long id) {
		// TODO Auto-generated method stub
		return policiesInstancesRepo.findById(id);
	}

	@Override
	public void deletePoliciesInstances(Long id) {
		// TODO Auto-generated method stub
		policiesInstancesRepo.deleteById(id);
	}

	@Override
	public List<PoliciesInstances> findPoliciesInstancesList(Long policy_id) {
		// TODO Auto-generated method stub
		return policiesInstancesRepo.findPoliciesInstancesList(policy_id);
	}

	@Override
	public PoliciesInstances getOnePolicyInstance(Long policy_id, Long server_id) {
		// TODO Auto-generated method stub
		return policiesInstancesRepo.getOnePolicyInstance(policy_id, server_id);
	}

}
