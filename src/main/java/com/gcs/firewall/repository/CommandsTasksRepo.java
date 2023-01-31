package com.gcs.firewall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gcs.firewall.model.CommandsTasks;

public interface CommandsTasksRepo extends JpaRepository<CommandsTasks, Long>{

}
