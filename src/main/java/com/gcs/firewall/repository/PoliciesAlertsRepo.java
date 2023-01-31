package com.gcs.firewall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gcs.firewall.model.PoliciesAlerts;

public interface PoliciesAlertsRepo extends JpaRepository<PoliciesAlerts, Long>{

}
