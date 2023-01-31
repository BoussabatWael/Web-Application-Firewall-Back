package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.Servers;
import com.gcs.firewall.model.UrlProtection;
import com.gcs.firewall.repository.ServersRepo;
import com.gcs.firewall.repository.UrlProtectionRepo;

@Service
public class UrlProtectionServiceImpl implements UrlProtectionService{
	
	@Autowired
	private UrlProtectionRepo urlProtectionRepo;
	
	@Autowired
	private ServersRepo serversRepo;
	
	@Override
	public UrlProtection addUrlProtections(UrlProtection urlprotection) {
		Servers server=serversRepo.findById(urlprotection.getServer().getId()).get();
		if(server==null) {
			return null;
		}
		else {
			urlprotection.setServer(server);
			return urlProtectionRepo.save(urlprotection);
		}
	}

	@Override
	public List<UrlProtection> getAllUrlProtection() {
		// TODO Auto-generated method stub
		return urlProtectionRepo.findAll();
	}

	@Override
	public UrlProtection updateUrlProtection(UrlProtection urlprotection) {
		// TODO Auto-generated method stub
		return urlProtectionRepo.saveAndFlush(urlprotection);
	}

	@Override
	public Optional<UrlProtection> findUrlProtectionById(Long id) {
		// TODO Auto-generated method stub
		return urlProtectionRepo.findById(id);
	}

	@Override
	public void deleteUrlProtection(Long id) {
		// TODO Auto-generated method stub
		urlProtectionRepo.deleteById(id);
	}

	@Override
	public List<UrlProtection> getURLByServer(Long server_id) {
		// TODO Auto-generated method stub
		return urlProtectionRepo.findUrlProtectionByServer(server_id);
	}

	
}
