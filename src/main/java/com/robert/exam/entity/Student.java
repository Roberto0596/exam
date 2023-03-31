package com.robert.exam.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column(nullable = false)
	private String age;
	
	@Column(nullable = false)
	private String email;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "timezone_id")
	private ZoneTime timeZone;
	
	@Column
	private Date created_at;
}
