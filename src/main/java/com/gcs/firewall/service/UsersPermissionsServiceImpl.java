package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.firewall.interceptors.BasicAuthInterceptor;
import com.gcs.firewall.model.Account;
import com.gcs.firewall.model.Users;
import com.gcs.firewall.model.UsersPermissions;
import com.gcs.firewall.repository.AccountRepo;
import com.gcs.firewall.repository.UsersPermissionsRepo;
import com.gcs.firewall.repository.UsersRepo;

@Service
public class UsersPermissionsServiceImpl implements UsersPermissionsService{
	
	@Autowired
	private UsersPermissionsRepo usersPermissionsRepo;
	
	@Autowired
	private UsersRepo usersRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public UsersPermissions addUsersPermissions(UsersPermissions usersPermissions) {
		Users users=usersRepo.findById(usersPermissions.getUsers().getId()).get();
		if(users==null) {
			return null;
		}
		if(usersPermissions.getStatus() == 0) {
			usersPermissions.setStatus(1);
		}
		if(users.getAccount() == null) {
			Account account=accountRepo.findById(Long.parseLong(BasicAuthInterceptor.GLOBAL_ACCOUNT)).get();
			users.setAccount(account);
		}
		usersPermissions.setUsers(users);
		return usersPermissionsRepo.save(usersPermissions);
		
	}

	@Override
	public List<UsersPermissions> getAllUsersPermissions() {
		// TODO Auto-generated method stub
		return usersPermissionsRepo.findAll();
	}

	@Override
	public UsersPermissions updateUsersPermissions(UsersPermissions usersPermissions) {
		// TODO Auto-generated method stub
		if(usersPermissions.getStatus() == 0) {
			usersPermissions.setStatus(1);
		}
		return usersPermissionsRepo.save(usersPermissions);
	}

	@Override
	public Optional<UsersPermissions> findUsersPermissionsById(Long id) {
		// TODO Auto-generated method stub
		return usersPermissionsRepo.findById(id);
	}

	@Override
	public void deleteUsersPermissions(Long id) {
		// TODO Auto-generated method stub
		usersPermissionsRepo.deleteById(id);
	}

	@Override
	public UsersPermissions findUsersPermissionsByUserId(Long user_id) {
		// TODO Auto-generated method stub
		return usersPermissionsRepo.findUsersPermissionsByUserId(user_id);
	}

}
