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
@Table(name="firewall_users_logs")
@Data @AllArgsConstructor @NoArgsConstructor
public class UsersLogs implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date action_date;
	private String action;
	private int element;
	private int element_id;
	private int source;
	private String code;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="user_id",nullable = false )
	private Users users;
	
}
