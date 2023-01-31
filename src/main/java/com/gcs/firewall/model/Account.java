package com.gcs.firewall.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="firewall_accounts")
@Data @AllArgsConstructor @NoArgsConstructor
public class Account implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private int status;
	private Date start_date;
	private Date end_date;
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Users> users=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Api_keys> apikeys=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Groups> groups=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Policies> policies=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Categories> categories=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Providers> providers=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Servers> servers=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Commands> commands=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<EmailsSenders> emailsSenders=new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private List<Templates> templates=new ArrayList<>();
	
	@PreRemove
	public void check() {
		if(!this.users.isEmpty()) {
			throw new RuntimeException("Can't remove an Account that has a User");
		}
		if(!this.groups.isEmpty()) {
			throw new RuntimeException("Can't remove an Account that has a Group");
		}
		if(!this.policies.isEmpty()) {
			throw new RuntimeException("Can't remove an Account that has a Policy");
		}
		if(!this.commands.isEmpty()) {
			throw new RuntimeException("Can't remove an Account that has a Command");
		}
		if(!this.emailsSenders.isEmpty()) {
			throw new RuntimeException("Can't remove an Account that has an Email Sender");
		}
		if(!this.templates.isEmpty()) {
			throw new RuntimeException("Can't remove an Account that has a Template");
		}
	}
}
