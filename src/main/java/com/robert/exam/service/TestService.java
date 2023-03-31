package com.robert.exam.service;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.TestRequestTO;
import com.robert.exam.entity.Test;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface TestService {
    public ResponseEntity<ResponseTO> getAll();
    public ResponseEntity<ResponseTO> get(long id);

    public ResponseEntity<ResponseTO> save(TestRequestTO test);

    public ResponseEntity<ResponseTO> delete(long id);
}
