package com.robert.exam.controller;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.TestRequestTO;
import com.robert.exam.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.namespace}/v1/test")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTO> getAll() {
        return testService.getAll();
    }

    @GetMapping(path ="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTO> get(@PathVariable Long id) {
        return testService.get(id);
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTO> save(@RequestBody TestRequestTO request) {
        return testService.save(request);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTO> save(@PathVariable Long id) {
        return testService.delete(id);
    }
}
