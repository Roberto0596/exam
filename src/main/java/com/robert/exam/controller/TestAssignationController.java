package com.robert.exam.controller;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.TestAssignationRequestTO;
import com.robert.exam.service.TestAssignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.namespace}/v1/test-assignation")
public class TestAssignationController {
    @Autowired
    TestAssignationService testAssignationService;

    @GetMapping("/get")
    public ResponseEntity<ResponseTO> getAll() {
        return testAssignationService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseTO> get(@PathVariable Long id) {
        return testAssignationService.get(id);
    }

    @GetMapping("/alumn-result")
    public ResponseEntity<ResponseTO> getResultByTest(@RequestParam Long alumnId, @RequestParam Long testId) {
        return testAssignationService.getResultStudentByTest(alumnId, testId);
    }

    @GetMapping("/alumn-result/all")
    public ResponseEntity<ResponseTO> getResultByAlumn(@RequestParam Long alumnId) {
        return testAssignationService.getResultStudent(alumnId);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseTO> save(@RequestBody TestAssignationRequestTO request) {
        return testAssignationService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseTO> save(@PathVariable Long id) {
        return testAssignationService.delete(id);
    }

}
