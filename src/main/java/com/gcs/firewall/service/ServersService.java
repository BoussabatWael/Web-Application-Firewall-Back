package com.gcs.firewall.service;

import java.util.List;
import java.util.Optional;

import com.gcs.firewall.model.Servers;


public interface ServersService {
	
	public Servers addServers(Servers servers) ;
	public List<Servers> getAllServers();
	public String getServersNumber(Long account_id);
	public List<Object[]> getRulesByServer(Long account_id);
	public List<Servers> findServersByStatus();
	public List<Servers> findServersList(Long r_id,Long acc_id);
	public List<Servers> findServersByAccountId(Long account_id);
	public List<Servers> findServerList(Long policy_id,Long acc_id);
	public List<Servers> getUsersServersList(Long user_id,Long account_id);
	public Servers updateServers(Servers servers);
	public Optional<Servers> findServersById(Long id) ;
	public void deleteServers(Long id) ;
}
