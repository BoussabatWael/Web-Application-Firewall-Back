package com.gcs.firewall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcs.firewall.model.CredentialsChange;

public interface CredentialsChangeRepo extends JpaRepository<CredentialsChange, Long>{

}
