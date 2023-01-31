package com.gcs.firewall.model;

import java.io.Serializable;
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
@Table(name="firewall_emails_senders")
@Data @AllArgsConstructor @NoArgsConstructor
public class EmailsSenders implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;	
	private String email_address;	
	private String password;
	private String host;	
	private String port;
	private String ssl_encrypt;
	private int status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_id",nullable = false )
	private Account account;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="provider_id",nullable = false )
	private Providers providers;
}
