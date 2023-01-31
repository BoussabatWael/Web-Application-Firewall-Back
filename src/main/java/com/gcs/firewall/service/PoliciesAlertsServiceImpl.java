package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.model.Policies;
import com.gcs.firewall.model.PoliciesAlerts;
import com.gcs.firewall.repository.PoliciesAlertsRepo;
import com.gcs.firewall.repository.PoliciesRepo;

@Service
public class PoliciesAlertsServiceImpl implements PoliciesAlertsService{
	
	@Autowired
	private PoliciesRepo policiesRepo;
	
	@Autowired
	private PoliciesAlertsRepo policiesAlertsRepo;
	
	@Override
	public PoliciesAlerts addPoliciesAlerts(PoliciesAlerts policiesAlerts) {
		Policies policies=policiesRepo.findById(policiesAlerts.getPolicies().getId()).get();
		if(policies==null) {
			return null;
		}
		
		else {
			policiesAlerts.setPolicies(policies);
			return policiesAlertsRepo.save(policiesAlerts);
		}
	}

	@Override
	public List<PoliciesAlerts> getAllPoliciesAlerts() {
		// TODO Auto-generated method stub
		return policiesAlertsRepo.findAll();
	}

	@Override
	public PoliciesAlerts updatePoliciesAlerts(PoliciesAlerts policiesAlerts) {
		// TODO Auto-generated method stub
		return policiesAlertsRepo.save(policiesAlerts);
	}

	@Override
	public Optional<PoliciesAlerts> findPoliciesAlertsById(Long id) {
		// TODO Auto-generated method stub
		return policiesAlertsRepo.findById(id);
	}

	@Override
	public void deletePoliciesAlerts(Long id) {
		// TODO Auto-generated method stub
		policiesAlertsRepo.deleteById(id);
	}

}
