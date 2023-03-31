package com.robert.exam.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
public class TestQuestion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String question;
	
	@Column
	private String answer1;
	
	@Column
	private String answer2;
	
	@Column
	private String answer3;
	
	@Column
	private String answer4;

	@Column
	private Double value;
	
	@Column
	private int correctOption;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "test_id")
	@JsonIgnore
	private Test test;
	
	@Column
	private Date created_at;	
}
