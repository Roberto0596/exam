package com.robert.exam.service;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.TestAssignationRequestTO;
import org.springframework.http.ResponseEntity;

public interface TestAssignationService {
    public ResponseEntity<ResponseTO> getAll();
    public ResponseEntity<ResponseTO> get(long id);

    public ResponseEntity<ResponseTO> getResultStudentByTest(Long studentId, Long testId);

    public ResponseEntity<ResponseTO> getResultStudent(Long studentId);

    public ResponseEntity<ResponseTO> save(TestAssignationRequestTO test);

    public ResponseEntity<ResponseTO> delete(long id);
}
