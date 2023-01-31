package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.PoliciesInstances;


public interface PoliciesInstancesRepo extends JpaRepository<PoliciesInstances, Long>{
	
	@Query(value= "select * from firewall_policies_instances inner join firewall_servers on firewall_policies_instances.server_id = firewall_servers.id where firewall_servers.status IN (1,2,3) AND firewall_policies_instances.status IN (1,2,3) AND firewall_policies_instances.policy_id =?1 order by firewall_servers.id",
            nativeQuery = true)
	public List<PoliciesInstances> findPoliciesInstancesList(Long policy_id);
	
	@Query(value= "select * from firewall_policies_instances where firewall_policies_instances.policy_id =?1 and firewall_policies_instances.server_id=?2 AND firewall_policies_instances.status IN (1,2,3)",
            nativeQuery = true)
	public PoliciesInstances getOnePolicyInstance(Long policy_id,Long server_id);
}
