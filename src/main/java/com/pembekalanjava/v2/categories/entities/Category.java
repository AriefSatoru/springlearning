package com.pembekalanjava.v2.categories.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "initial", length = 5)
	private String initial;
	
	@Column(name = "name", length = 30)
	private String name;
	
	@Column(name = "description", length = 200)
	private String description;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_on", updatable = false)
	private Date create_on;
	
	@Column(name = "created_by")
	private Long create_by;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_on")
	private Date update_on;
	
	@Column(name = "updated_by")
	private Long updated_by;
	
	@Column(name = "is_delete")
	private Boolean is_delete;
}
