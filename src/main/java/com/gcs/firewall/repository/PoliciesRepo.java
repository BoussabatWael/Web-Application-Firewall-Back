package com.gcs.firewall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.Policies;

public interface PoliciesRepo extends JpaRepository<Policies, Long>{
	
	@Query(value="select * from firewall_policies where firewall_policies.status IN (1,2,3)",
			nativeQuery=true)
	public List<Policies> findPoliciesByStatus();
	
	@Query(value="SELECT * FROM firewall_policies WHERE firewall_policies.account_id = ?1 AND firewall_policies.status IN (1,2,3)",nativeQuery=true)
	public List<Policies> findPoliciesByAccountId(Long account_id);
	
	@Query(value="SELECT count(*) FROM firewall_policies WHERE firewall_policies.status IN (1,2,3) AND firewall_policies.account_id =?1",nativeQuery=true)
	public String getPoliciesNumber(Long account_id);
}
