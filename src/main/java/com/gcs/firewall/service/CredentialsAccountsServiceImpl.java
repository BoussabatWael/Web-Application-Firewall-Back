package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.firewall.model.CredentialsAccounts;
import com.gcs.firewall.repository.CredentialsAccountsRepo;

@Service
public class CredentialsAccountsServiceImpl implements CredentialsAccountsService{
	
	@Autowired
	private CredentialsAccountsRepo credentialsAccountsRepo;
	
	@Override
	public CredentialsAccounts addCredentialsAccounts(CredentialsAccounts credentialsAccounts) {
		// TODO Auto-generated method stub
		if(credentialsAccounts.getStatus() == 0) {
			credentialsAccounts.setStatus(1);
		}
		if(credentialsAccounts.getSource() == 0) {
			credentialsAccounts.setSource(1);
		}
		if(credentialsAccounts.getName() == null) {
			credentialsAccounts.setName("");
		}
		return credentialsAccountsRepo.save(credentialsAccounts);
	}

	@Override
	public List<CredentialsAccounts> getAllCredentialsAccounts() {
		// TODO Auto-generated method stub
		return credentialsAccountsRepo.findAll();
	}

	@Override
	public CredentialsAccounts updateCredentialsAccounts(CredentialsAccounts credentialsAccounts) {
		// TODO Auto-generated method stub
		if(credentialsAccounts.getStatus() == 0) {
			credentialsAccounts.setStatus(1);
		}
		if(credentialsAccounts.getSource() == 0) {
			credentialsAccounts.setSource(1);
		}
		return credentialsAccountsRepo.save(credentialsAccounts);
	}

	@Override
	public Optional<CredentialsAccounts> findCredentialsAccountsById(Long id) {
		// TODO Auto-generated method stub
		return credentialsAccountsRepo.findById(id);
	}

	@Override
	public void deleteCredentialsAccounts(Long id) {
		// TODO Auto-generated method stub
		credentialsAccountsRepo.deleteById(id);
	}

	@Override
	public List<CredentialsAccounts> getCredentialsBySourceId(Long source_id) {
		// TODO Auto-generated method stub
		return credentialsAccountsRepo.getCredentialsBySourceId(source_id);
	}

}
