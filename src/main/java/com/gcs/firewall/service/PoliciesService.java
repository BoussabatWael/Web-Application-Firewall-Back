package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.Policies;

public interface PoliciesService {
	
	public Policies addPolicies(Policies policies) ;
	public List<Policies> getAllPolicies();
	public String getPoliciesNumber(Long account_id);
	public List<Policies> findPoliciesByStatus();
	public List<Policies> findPoliciesByAccountId(Long account_id);
	public Policies updatePolicies(Policies policies);
	public Optional<Policies> findPoliciesById(Long id) ;
	public void deletePolicies(Long id) ;
}
