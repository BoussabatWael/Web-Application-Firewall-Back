package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.PoliciesAlerts;

public interface PoliciesAlertsService {
	
	public PoliciesAlerts addPoliciesAlerts(PoliciesAlerts policiesAlerts) ;
	public List<PoliciesAlerts> getAllPoliciesAlerts();
	public PoliciesAlerts updatePoliciesAlerts(PoliciesAlerts policiesAlerts);
	public Optional<PoliciesAlerts> findPoliciesAlertsById(Long id) ;
	public void deletePoliciesAlerts(Long id) ;
}
