package com.gcs.firewall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcs.firewall.model.EmailsSenders;

public interface EmailsSendersRepo extends JpaRepository<EmailsSenders, Long>{

}
