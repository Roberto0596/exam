package com.robert.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;

@Entity
@Table
@Getter
@Setter
@ToString
public class AlumnQuestionAnswer {

    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_assignation_id")
    @JsonIgnore
    private TestAssignation testAssignation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_question_id")
    private TestQuestion testQuestion;

    @Column
    private int answer;

    @Column
    private Date created_at;
}
