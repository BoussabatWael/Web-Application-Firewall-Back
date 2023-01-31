package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.Providers;

public interface ProvidersService {
	
	public Providers addProviders(Providers providers) ;
	public List<Providers> getAllProviders();
	public List<Providers> getProvidersList(Long account_id);
	public Providers updateProviders(Providers providers);
	public Optional<Providers> findProvidersById(Long id) ;
	public void deleteProviders(Long id) ;
}
