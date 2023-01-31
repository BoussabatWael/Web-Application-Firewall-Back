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
import javax.persistence.PreRemove;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="firewall_users")
@Data @AllArgsConstructor @NoArgsConstructor
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private int role;
	private String language;
	private String timezone;
	private String browser;
	private String ip_address;	
	private Date last_auth;
	private String photo;
	private int status;
	private Date start_date;
	private Date end_date;
	private String auto_sync;
	private String key_value;	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_id",nullable = false )
	private Account account;
	
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<UsersLogs> usersLogs=new ArrayList<>();
	
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<UsersPermissions> usersPermissions=new ArrayList<>();
	
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<UsersServers> usersServers=new ArrayList<>();
	
	@PreRemove
	public void check() {
		if(!this.usersLogs.isEmpty()) {
			throw new RuntimeException("Can't remove a User that has a User Logs");
		}
		if(!this.usersPermissions.isEmpty()) {
			throw new RuntimeException("Can't remove a User that has a User Permissions");
		}
	}
}
