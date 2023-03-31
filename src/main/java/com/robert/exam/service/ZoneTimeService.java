package com.robert.exam.service;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.entity.ZoneTime;
import org.springframework.http.ResponseEntity;

public interface ZoneTimeService {
    public ResponseEntity<ResponseTO> getAll();
    public ResponseEntity<ResponseTO> get(long id);

    public ResponseEntity<ResponseTO> save(ZoneTime test);

    public ResponseEntity<ResponseTO> delete(long id);
}
