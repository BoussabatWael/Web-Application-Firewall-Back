package com.gcs.firewall.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="firewall_users_servers")
@Data @AllArgsConstructor @NoArgsConstructor
public class UsersServers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int status;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="user_id",nullable = false )
	private Users users;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="server_id",nullable = false )
	private Servers servers;
	
	private Date start_date;
	private Date end_date;
	
	@OneToMany(mappedBy = "usersServers")
	@JsonIgnore
	private List<UsersServersRules> usersServersRules=new ArrayList<>();
}
