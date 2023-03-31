package com.robert.exam.controller;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.StudentSaveRequestTO;
import com.robert.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.namespace}/v1/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTO> getAll() {
        return studentService.getAll();
    }

    @GetMapping(path ="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTO> get(@PathVariable Long id) {
        return studentService.get(id);
    }

    @PostMapping(path ="/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTO> save(@RequestBody StudentSaveRequestTO request) {
        return studentService.save(request);
    }

    @DeleteMapping(path ="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseTO> save(@PathVariable Long id) {
        return studentService.delete(id);
    }
}
