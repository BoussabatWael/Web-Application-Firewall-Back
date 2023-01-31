package com.gcs.firewall.model;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name="firewall_credentials_accounts")
@Data @AllArgsConstructor @NoArgsConstructor
public class CredentialsAccounts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int source;
	private int source_id;
	private int classe;
	private String name;
	private String url;
	private String port;
	private String login;	
	private String password;
	private int status;
	
	@OneToMany(mappedBy = "credentialsAccounts")
	@JsonIgnore
	private List<CredentialsChange> credentialsChange=new ArrayList<>();
	
	@PreRemove
	public void check() {
		if(!this.credentialsChange.isEmpty()) {
			throw new RuntimeException("Can't remove a Credential Account that has a Credential Changes");
		}
	}
}
