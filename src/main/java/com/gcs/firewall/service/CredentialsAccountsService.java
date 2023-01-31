package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.CredentialsAccounts;

public interface CredentialsAccountsService{
	
	public CredentialsAccounts addCredentialsAccounts(CredentialsAccounts credentialsAccounts) ;
	public List<CredentialsAccounts> getAllCredentialsAccounts();
	public CredentialsAccounts updateCredentialsAccounts(CredentialsAccounts credentialsAccounts);
	public Optional<CredentialsAccounts> findCredentialsAccountsById(Long id) ;
	public void deleteCredentialsAccounts(Long id) ;
	public List<CredentialsAccounts> getCredentialsBySourceId(Long source_id);
}
