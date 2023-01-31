package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.model.AutorizationRules;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.repository.AutorizationRulesRepo;
import com.gcs.firewall.repository.ServersRepo;

@Service
public class AutorizationRulesServiceImpl implements AutorizationRulesService{
	
	@Autowired
	private AutorizationRulesRepo autorizationRulesRepo;
	
	@Autowired
	private ServersRepo serversRepo;
	
	@Override
	public AutorizationRules addAutorizationRules(AutorizationRules autorizationRules) {
		Servers servers=serversRepo.findById(autorizationRules.getServer().getId()).get();
		if(servers==null) {
			return null;
		}
		else {
			autorizationRules.setServer(servers);
			return autorizationRulesRepo.saveAndFlush(autorizationRules);
		}
	}

	@Override
	public List<AutorizationRules> getAllAutorizationRules() {
		// TODO Auto-generated method stub
		return autorizationRulesRepo.findAll();
	}

	@Override
	public AutorizationRules updateAutorizationRules(AutorizationRules autorizationRules) {
		// TODO Auto-generated method stub
		return autorizationRulesRepo.save(autorizationRules);
	}

	@Override
	public Optional<AutorizationRules> findAutorizationRulesById(Long id) {
		// TODO Auto-generated method stub
		return autorizationRulesRepo.findById(id);
	}

	@Override
	public void deleteAutorizationRules(Long id) {
		// TODO Auto-generated method stub
		autorizationRulesRepo.deleteById(id);
	}

}
