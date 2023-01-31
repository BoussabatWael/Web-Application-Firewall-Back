package com.gcs.firewall.model;

import java.io.Serializable;
import java.sql.Time;
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
@Table(name="firewall_commands")
@Data @AllArgsConstructor @NoArgsConstructor
public class Commands implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//private Long account_id;
	private String name;
	private String command;
	private int classe;
	private String file_command;
	private int system;
	private String user;
	private int event;	
	private Date event_datetime;
	private int event_unit;
	private String event_scale;
	private Time event_time;
	private int status;
	private Date start_date;
	private Date end_date;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_id",nullable = false )
	private Account account;
	
	@OneToMany(mappedBy = "commands")
	@JsonIgnore
	private List<CommandsTasks> commandsTasks=new ArrayList<>();
	
	@PreRemove
	public void check() {
		if(!this.commandsTasks.isEmpty()) {
			throw new RuntimeException("Can't remove a Command that has a Command Tasks");
		}
	}
}
