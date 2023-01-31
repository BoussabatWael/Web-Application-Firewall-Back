package com.gcs.firewall.model;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name="firewall_categories")
@Data @AllArgsConstructor @NoArgsConstructor
public class Categories implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private int classe;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_id",nullable = false )
	private Account account;
	
	@OneToMany(mappedBy = "categories")
	@JsonIgnore
	private List<Groups> groups=new ArrayList<>();
	
	@OneToMany(mappedBy = "categories")
	@JsonIgnore
	private List<Providers> providers=new ArrayList<>();
	
	@OneToMany(mappedBy = "categories")
	@JsonIgnore
	private List<Servers> servers=new ArrayList<>();
	
	@PreRemove
	public void check() {
		if(!this.groups.isEmpty()) {
			throw new RuntimeException("Can't remove a Category that has a Group");
		}
		if(!this.providers.isEmpty()) {
			throw new RuntimeException("Can't remove a Category that has a Provider");
		}
		if(!this.servers.isEmpty()) {
			throw new RuntimeException("Can't remove a Category that has a Server");
		}
	}
}
