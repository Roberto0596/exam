package com.robert.exam.service;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.StudentSaveRequestTO;
import com.robert.exam.entity.Student;
import com.robert.exam.entity.Test;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    public ResponseEntity<ResponseTO> getAll();
    public ResponseEntity<ResponseTO> get(long id);

    public ResponseEntity<ResponseTO> save(StudentSaveRequestTO test);

    public ResponseEntity<ResponseTO> delete(long id);
}
