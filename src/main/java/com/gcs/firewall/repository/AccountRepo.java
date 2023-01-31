package com.gcs.firewall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcs.firewall.model.Account;


public interface AccountRepo extends JpaRepository<Account, Long>{

}
