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
@Table(name="firewall_commands_tasks")
@Data @AllArgsConstructor @NoArgsConstructor
public class CommandsTasks implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int status;
	private Date start_date;
	private Date end_date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="command_id",nullable = false )
	private Commands commands;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="server_id",nullable = false )
	private Servers servers;
}
