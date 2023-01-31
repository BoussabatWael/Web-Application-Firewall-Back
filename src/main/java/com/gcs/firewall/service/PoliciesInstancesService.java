package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.PoliciesInstances;

public interface PoliciesInstancesService {
	
	public PoliciesInstances addPoliciesInstances(PoliciesInstances policiesInstances) ;
	public List<PoliciesInstances> getAllPoliciesInstances();
	public List<PoliciesInstances> findPoliciesInstancesList(Long policy_id);
	public PoliciesInstances updatePoliciesInstances(PoliciesInstances policiesInstances);
	public PoliciesInstances getOnePolicyInstance(Long policy_id,Long server_id);
	public Optional<PoliciesInstances> findPoliciesInstancesById(Long id) ;
	public void deletePoliciesInstances(Long id) ;
}
