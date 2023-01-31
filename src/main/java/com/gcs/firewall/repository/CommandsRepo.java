package com.gcs.firewall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gcs.firewall.model.Commands;

public interface CommandsRepo extends JpaRepository<Commands, Long>{

}
