package com.gcs.firewall.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.gcs.firewall.model.Users;

public interface UsersRepo extends JpaRepository<Users, Long>{
	
	@Query(value="select * from firewall_users where firewall_users.account_id =?1 AND firewall_users.status IN (1,2,3)",
			nativeQuery=true)
	public List<Users> findUsersByStatus(Long account_id);
	
	@Query(value="select * FROM firewall_users WHERE firewall_users.username=?1 AND firewall_users.password=?2 AND firewall_users.status IN (1,2,3)",
			nativeQuery=true)
	public Users getOneUser(String username,String password);
	
	public Optional<Users>  findByUsername(String username);
	
	@Query(value="SELECT * FROM firewall_users WHERE firewall_users.account_id =?1 AND firewall_users.id !=?2 AND firewall_users.status IN (1,2,3) AND firewall_users.role !=1",nativeQuery=true)
	public List<Users> findUsersByAccountId(Long account_id,Long user_id);
	
	@Query(value="SELECT count(*) FROM firewall_users WHERE firewall_users.status IN (1,2,3)",nativeQuery=true)
	public String getUsersNumber();
	
	@Query(value="SELECT * FROM firewall_users WHERE firewall_users.username = ?1 AND firewall_users.status IN (1,2,3)",nativeQuery=true)
	public Users getUsersList(String username);
	
	@Query(value="SELECT * FROM firewall_users WHERE firewall_users.username = ?1 AND firewall_users.status IN (1,2,3)",nativeQuery=true)
	public Users getUsersByUsername(String username);
}
