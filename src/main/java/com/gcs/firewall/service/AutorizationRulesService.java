package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.AutorizationRules;

public interface AutorizationRulesService {
	
	public AutorizationRules addAutorizationRules(AutorizationRules autorizationRules) ;
	public List<AutorizationRules> getAllAutorizationRules();
	public AutorizationRules updateAutorizationRules(AutorizationRules autorizationRules);
	public Optional<AutorizationRules> findAutorizationRulesById(Long id) ;
	public void deleteAutorizationRules(Long id) ;
}
