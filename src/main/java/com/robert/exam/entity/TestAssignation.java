package com.robert.exam.entity;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
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
@ToString
public class TestAssignation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "test_id")
	private Test test;
	
	@Column(columnDefinition = "integer default 0.0")
	private Double general_qualification;

	@Column(columnDefinition = "integer default 0")
	private Integer sended;

	@Column(name = "application_date")
	private Timestamp applicationDate;
	@Column(name = "alumn_aplication_date")
	private Timestamp alumnAplicationDate;

	@ManyToOne(optional = true)
	@JoinColumn(name = "timezone_id")
	private ZoneTime timeZone;

	@OneToMany(mappedBy = "testAssignation")
	private List<AlumnQuestionAnswer> alumnQuestionAnswers;
	
	@Column
	private Date created_at;
}
