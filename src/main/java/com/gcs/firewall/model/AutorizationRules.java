package com.gcs.firewall.model;

import java.io.Serializable;
import java.sql.Time;
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
@Table(name="firewall_autorization_rules")
@Data @AllArgsConstructor @NoArgsConstructor
public class AutorizationRules implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//private Long account_id;
	private String name;
	private String protocol;
	private String port;
	private String ip_address;
	private Time time_start;
	private Time time_end;
	private String machine;	
	private String zones;
	private int factor;
	private String factor_type;
	private String code;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="server_id",nullable = false )
	private Servers server;
}
