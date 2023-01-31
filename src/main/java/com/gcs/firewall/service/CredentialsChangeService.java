package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.CredentialsChange;


public interface CredentialsChangeService {
	
	public CredentialsChange addCredentialsChange(CredentialsChange credentialsChange) ;
	public List<CredentialsChange> getAllCredentialsChange();
	public CredentialsChange updateCredentialsChange(CredentialsChange credentialsChange);
	public Optional<CredentialsChange> findCredentialsChangeById(Long id) ;
	public void deleteCredentialsChange(Long id) ;
}
