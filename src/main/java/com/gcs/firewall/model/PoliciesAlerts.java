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
@Table(name="firewall_policies_alerts")
@Data @AllArgsConstructor @NoArgsConstructor
public class PoliciesAlerts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int classe;
	private String alert_value;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="policy_id",nullable = false )
	private Policies policies;
}
