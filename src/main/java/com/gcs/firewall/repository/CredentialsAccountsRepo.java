package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.CredentialsAccounts;


public interface CredentialsAccountsRepo extends JpaRepository<CredentialsAccounts, Long> {
	
	@Query(value="SELECT * FROM firewall_credentials_accounts WHERE firewall_credentials_accounts.source_id = ?1 AND firewall_credentials_accounts.status IN (1,2,3)",
			nativeQuery=true)
	public List<CredentialsAccounts> getCredentialsBySourceId(Long source_id);
}
