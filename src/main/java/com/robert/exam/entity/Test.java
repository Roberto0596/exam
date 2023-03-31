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
public class Test implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;

	@OneToMany(mappedBy = "test")
	private List<TestQuestion> testQuestions;
	
	@Column
	private Date created_at;
	
}
