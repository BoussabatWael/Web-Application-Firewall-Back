package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;
import com.gcs.firewall.model.Groups;

public interface GroupsService {
	
	public Groups addGroups(Groups groups) ;
	public List<Groups> getAllGroups();
	public List<Groups> findGroupsByStatus();
	public List<Groups> findGroupsList(Long r_id,Long acc_id);
	public List<Groups> findGroupsByAccountId(Long account_id);
	public Groups updateGroups(Groups groups);
	public Optional<Groups> findGroupsById(Long id) ;
	public void deleteGroups(Long id) ;
}
