package com.gcs.firewall.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.gcs.firewall.model.Users;

public interface UsersServices {
	
	public Users addUsers(String users, MultipartFile file) throws IOException;
	public Users addUsers1(Users users);
	public List<Users> getAllUsers();
	public String getUserNumber();
	public List<Users> findUsersByStatus(Long account_id);
	public Users getUsersList(String username);
	public List<Users> findUsersByAccountId(Long account_id,Long user_id);
	public Users updateUsers(String users, MultipartFile file) throws Exception;
	public Users updateUsers1(Users users);
	public Users getOneUser(String username,String password);
	public Users getUserByUsername(String username);
	public Optional<Users> findUsersById(Long id) ;
	public void deleteUsers(Long id) ;

}
