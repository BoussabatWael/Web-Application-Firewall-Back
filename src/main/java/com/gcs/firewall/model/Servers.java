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
import javax.validation.constraints.Null;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="firewall_servers")
@Data @AllArgsConstructor @NoArgsConstructor
public class Servers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;	
	private String ip_address;	
	private int authorization;
	private String operating_system;
	private String system_version;	
	private int status;
	private Date start_date;
	private Date end_date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_id",nullable = false )
	private Account account;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Nullable
	@Null
	@JoinColumn(name ="category_id",nullable = true )
	private Categories categories;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="provider_id",nullable = true )
	@Nullable
	@Null
	private Providers providers;
	
	@OneToMany(mappedBy = "server")
	@JsonIgnore
	private List<UrlProtection> url_protection=new ArrayList<>();
	
	@OneToMany(mappedBy = "server")
	@JsonIgnore
	private List<AutorizationRules> autorizationRules=new ArrayList<>();
	
	@OneToMany(mappedBy = "servers")
	@JsonIgnore
	private List<CommandsTasks> commandsTasks=new ArrayList<>();
	
	@OneToMany(mappedBy = "servers")
	@JsonIgnore
	private List<PoliciesInstances> policiesInstances=new ArrayList<>();
	
	@OneToMany(mappedBy = "servers")
	@JsonIgnore
	private List<RulesInstances> rulesInstances=new ArrayList<>();
	
	@OneToMany(mappedBy = "servers")
	@JsonIgnore
	private List<UsersServers> usersServers=new ArrayList<>();
	
	@PreRemove
	public void check() {

		if(!this.autorizationRules.isEmpty()) {
			throw new RuntimeException("Can't remove a Server that has an Autorization Rule");
		}
		if(!this.commandsTasks.isEmpty()) {
			throw new RuntimeException("Can't remove a Server that has a Command Tasks");
		}
		if(!this.policiesInstances.isEmpty()) {
			throw new RuntimeException("Can't remove a Server that has a Policy Instance");
		}
		
	}
}
