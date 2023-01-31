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
@Table(name="firewall_policies")
@Data @AllArgsConstructor @NoArgsConstructor
public class Policies implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//private int account_id;
	private String name;	
	private int metric;
	private String action;
	private String threshold;
	private int duration;
	private int status;
	private Date start_date;
	private Date end_date;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_id",nullable = false )
	private Account account;
	
	@OneToMany(mappedBy = "policies")
	@JsonIgnore
	private List<PoliciesAlerts> policiesAlerts=new ArrayList<>();
	
	@OneToMany(mappedBy = "policies")
	@JsonIgnore
	private List<PoliciesInstances> policiesInstances=new ArrayList<>();
	
	@PreRemove
	public void check() {
		if(!this.policiesAlerts.isEmpty()) {
			throw new RuntimeException("Can't remove a Policy that has a Policy Alert");
		}
		if(!this.policiesInstances.isEmpty()) {
			throw new RuntimeException("Can't remove a Policy that has a Policy Instance");
		}
	}
}
