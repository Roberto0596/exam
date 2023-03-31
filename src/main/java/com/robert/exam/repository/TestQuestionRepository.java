package com.robert.exam.repository;

import com.robert.exam.entity.TestQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends CrudRepository<TestQuestion, Long> {
}
