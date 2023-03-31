package com.robert.exam.repository;

import com.robert.exam.entity.AlumnQuestionAnswer;
import com.robert.exam.entity.TestAssignation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestAssignationRepository extends CrudRepository<TestAssignation, Long> {
    @Query(value = "select * from testassignation where student_id = ? and test_id = ?", nativeQuery = true)
    public List<TestAssignation> getByAlumnAndTest(Long alumnId, Long testId);

    @Query(value = "select * from testassignation where student_id = ?", nativeQuery = true)
    public List<TestAssignation> getByAlumn(Long alumnId);
}
