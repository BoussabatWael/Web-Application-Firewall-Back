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
@Table(name="firewall_groups")
@Data @AllArgsConstructor @NoArgsConstructor
public class Groups implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;	
	private int status;
	private Date start_date;
	private Date end_date;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_id",nullable = false )
	private Account account;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="category_id",nullable = true )
	@Nullable
	@Null
	private Categories categories;
	
	@OneToMany(mappedBy = "groups")
	@JsonIgnore
	private List<GroupsRules> groupsRules=new ArrayList<>();
	
	@PreRemove
	public void check() {
		if(!this.groupsRules.isEmpty()) {
			throw new RuntimeException("Can't remove a Group that has a Group Rule");
		}
	}
}
