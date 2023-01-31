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
@Table(name="firewall_rules")
@Data @AllArgsConstructor @NoArgsConstructor
public class Rules implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;	
	private String action;	
	private String protocol;	
	private String port;	
	private String ip_address;
	private int status;	
	private Date start_date;
	private Date end_date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_id",nullable = false )
	private Account account;
	
	@OneToMany(mappedBy = "rules")
	@JsonIgnore
	private List<GroupsRules> groupsRules=new ArrayList<>();
	
	@OneToMany(mappedBy = "rules")
	@JsonIgnore
	private List<RulesInstances> rulesInstances=new ArrayList<>();
	
	@OneToMany(mappedBy = "rules")
	@JsonIgnore
	private List<UsersServersRules> usersServersRules=new ArrayList<>();
	
	@PreRemove
	public void check() {
		if(!this.rulesInstances.isEmpty()) {
			throw new RuntimeException("Can't remove a Rule that has a Rule Instances");
		}
		if(!this.groupsRules.isEmpty()) {
			throw new RuntimeException("Can't remove a Rule that has a Group Rules");
		}
	}

}
