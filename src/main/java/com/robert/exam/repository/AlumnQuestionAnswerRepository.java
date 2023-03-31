package com.robert.exam.repository;

import com.robert.exam.entity.AlumnQuestionAnswer;
import com.robert.exam.entity.TestAssignation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnQuestionAnswerRepository extends CrudRepository<AlumnQuestionAnswer, Long> {
    @Query(value = "select * from alumnquestionanswer where student = ? and test = ?", nativeQuery = true)
    public List<AlumnQuestionAnswer> getByAlumnAndTest(Long alumnId, Long testId);
}
