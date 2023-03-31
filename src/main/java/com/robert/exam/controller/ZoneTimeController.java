package com.robert.exam.controller;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.entity.ZoneTime;
import com.robert.exam.service.ZoneTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.namespace}/v1/zonetime")
public class ZoneTimeController {
    @Autowired
    ZoneTimeService zoneTimeService;

    @GetMapping("/get")
    public ResponseEntity<ResponseTO> getAll() {
        return zoneTimeService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseTO> get(@PathVariable Long id) {
        return zoneTimeService.get(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseTO> save(@RequestBody ZoneTime request) {
        return zoneTimeService.save(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseTO> save(@PathVariable Long id) {
        return zoneTimeService.delete(id);
    }
}
