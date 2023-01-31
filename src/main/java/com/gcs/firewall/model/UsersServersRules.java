package com.gcs.firewall.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="firewall_users_servers_rules")
@Data @AllArgsConstructor @NoArgsConstructor
public class UsersServersRules  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="user_server_id",nullable = false )
	private UsersServers usersServers;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="rule_id",nullable = false )
	private Rules rules;
	
	private Date start_date;
	private Date end_date;
}
