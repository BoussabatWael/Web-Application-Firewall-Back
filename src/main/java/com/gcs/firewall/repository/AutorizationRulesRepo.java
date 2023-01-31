package com.gcs.firewall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gcs.firewall.model.AutorizationRules;

public interface AutorizationRulesRepo extends JpaRepository<AutorizationRules, Long>{

}
