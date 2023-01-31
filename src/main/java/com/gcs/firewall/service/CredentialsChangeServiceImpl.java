package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.CredentialsAccounts;
import com.gcs.firewall.model.CredentialsChange;
import com.gcs.firewall.repository.CredentialsAccountsRepo;
import com.gcs.firewall.repository.CredentialsChangeRepo;

@Service
public class CredentialsChangeServiceImpl implements CredentialsChangeService{
	
	@Autowired
	private CredentialsChangeRepo credentialsChangeRepo;
	
	@Autowired
	private CredentialsAccountsRepo credentialsAccountsRepo;
	
	@Override
	public List<CredentialsChange> getAllCredentialsChange() {
		// TODO Auto-generated method stub
		return credentialsChangeRepo.findAll();
	}

	@Override
	public CredentialsChange updateCredentialsChange(CredentialsChange credentialsChange) {
		// TODO Auto-generated method stub
		return credentialsChangeRepo.save(credentialsChange);
	}

	@Override
	public Optional<CredentialsChange> findCredentialsChangeById(Long id) {
		// TODO Auto-generated method stub
		return credentialsChangeRepo.findById(id);
	}

	@Override
	public void deleteCredentialsChange(Long id) {
		// TODO Auto-generated method stub
		credentialsChangeRepo.deleteById(id);
	}

	@Override
	public CredentialsChange addCredentialsChange(CredentialsChange credentialsChange) {
		CredentialsAccounts credentialsAccounts=credentialsAccountsRepo.findById(credentialsChange.getCredentialsAccounts().getId()).get();
		if(credentialsAccounts==null) {
			return null;
		}
		else {
			credentialsChange.setCredentialsAccounts(credentialsAccounts);
			return credentialsChangeRepo.save(credentialsChange);
		}
	}

}
