package com.robert.exam.service;

import com.robert.exam.bean.AlumnQuestionAsnwerRequestTO;
import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.StudentSaveRequestTO;
import org.springframework.http.ResponseEntity;

public interface AlumnQuestionAsnwerService {
    public ResponseEntity<ResponseTO> getAll();
    public ResponseEntity<ResponseTO> get(long id);

    public ResponseEntity<ResponseTO> save(AlumnQuestionAsnwerRequestTO test);

    public ResponseEntity<ResponseTO> delete(long id);

    abstract ResponseEntity<ResponseTO> evaluate(Long studentId, Long testId);
}
