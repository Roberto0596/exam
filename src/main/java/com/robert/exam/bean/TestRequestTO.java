package com.robert.exam.bean;

import com.robert.exam.entity.Test;
import com.robert.exam.entity.TestQuestion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestRequestTO {
    private Test test;
    private List<TestQuestion> testQuestions;
}
