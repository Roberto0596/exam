package com.robert.exam.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlumnQuestionAsnwerRequestTO {
    private String id;

    private Long testAssignationId;

    private Long testQuestionId;

    private int answer;
}

