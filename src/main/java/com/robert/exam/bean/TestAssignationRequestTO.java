package com.robert.exam.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class TestAssignationRequestTO implements Serializable {

    private String id;
    private Long student_id;
    private Long test_id;

    private Long zone_id;
    private Double general_qualification;

    private String applicationDate;
}
