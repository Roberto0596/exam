package com.robert.exam.controller;

import com.robert.exam.bean.AlumnQuestionAsnwerRequestTO;
import com.robert.exam.bean.ResponseTO;
import com.robert.exam.service.AlumnQuestionAsnwerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.namespace}/v1/answer-alumn")
public class AlumnQuestionAnswerController {
    @Autowired
    AlumnQuestionAsnwerService alumnQuestionAsnwerService;

    @GetMapping("/get")
    public ResponseEntity<ResponseTO> getAll() {
        return alumnQuestionAsnwerService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseTO> get(@PathVariable Long id) {
        return alumnQuestionAsnwerService.get(id);
    }

    @GetMapping("/evaluate")
    public ResponseEntity<ResponseTO> get(@RequestParam Long alumnId, @RequestParam Long testId) {
        return alumnQuestionAsnwerService.evaluate(alumnId, testId);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseTO> save(@RequestBody AlumnQuestionAsnwerRequestTO request) {
        return alumnQuestionAsnwerService.save(request);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseTO> save(@PathVariable Long id) {
        return alumnQuestionAsnwerService.delete(id);
    }
}
