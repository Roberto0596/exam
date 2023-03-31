package com.robert.exam.service.impl;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.StudentSaveRequestTO;
import com.robert.exam.entity.Student;
import com.robert.exam.entity.Test;
import com.robert.exam.entity.ZoneTime;
import com.robert.exam.repository.StudentRepository;
import com.robert.exam.repository.ZoneTimeRepository;
import com.robert.exam.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static Logger log = LogManager.getLogger();

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ZoneTimeRepository timeRepository;

    @Override
    public ResponseEntity<ResponseTO> getAll() {
        ResponseTO<List<Student>> responseTO = new ResponseTO<>();
        List<Student> instances = (List<Student>) studentRepository.findAll();

        if(instances.isEmpty()) {
            responseTO.setCode(1001);
            responseTO.setMessage("No hay registros por mostrar");
            responseTO.setResource(null);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        }

        responseTO.setResource(instances);
        responseTO.setCode(1000);
        responseTO.setMessage("consulta realizada correctamente");
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTO> get(long id) {
        ResponseTO<Student> responseTO = new ResponseTO<>();

        Optional<Student> instance = studentRepository.findById(id);

        if (instance.isEmpty()) {
            responseTO.setCode(1001);
            responseTO.setMessage("No se encontró el registro solicitado");
            responseTO.setResource(null);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        }

        responseTO.setCode(1000);
        responseTO.setMessage("Consulta realizada correctamente");
        responseTO.setResource(instance.get());
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTO> save(StudentSaveRequestTO student) {
        log.info("se inicia flujo de guardado");
        log.info("Request {}", student);
        ResponseTO<Student> responseTO = new ResponseTO<>();
        try {
            var instance = new Student();

            if (student.getId() != null) {
                log.info("se verifica si existe el alumno");
                Optional<Student> op = studentRepository.findById(Long.parseLong(student.getId()));
                instance.setId(Long.valueOf(student.getId()));
                if (op.isPresent()) {
                    log.info("el alumno existe");
                    instance = op.get();
                }
            }

            if(instance.getCreated_at() == null) {
                instance.setCreated_at(new Date(System.currentTimeMillis()));
            }

            instance.setAge(student.getAge());
            instance.setName(student.getName());
            instance.setEmail(student.getEmail());
            instance.setLastname(student.getLastname());

            if(student.getTimeZoneId() != null) {
                log.info("tiene zona horaria");
                Optional<ZoneTime> timeZone = timeRepository.findById(student.getTimeZoneId());

                if (timeZone.isPresent()) {
                    instance.setTimeZone(timeZone.get());
                }
            }
            log.info("alumno a guardar {}", instance);
            instance = studentRepository.save(instance);
            log.info("termina flujo");
            responseTO.setCode(1000);
            responseTO.setMessage("Guardado realizado exitosamente");
            responseTO.setResource(instance);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        } catch (Exception e) {
            log.info("ocurrio un error {}", e);
            responseTO.setResource(null);
            responseTO.setMessage("Ocurrio un error al guardar");
            responseTO.setCode(1005);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseTO> delete(long id) {
        ResponseTO<Test> responseTO = new ResponseTO<>();
        try {
            studentRepository.deleteById(id);
            responseTO.setCode(1000);
            responseTO.setMessage("Eliminado realizado exitosamente");
            responseTO.setResource(null);
        } catch (Exception e) {
            responseTO.setCode(1005);
            responseTO.setMessage("No se pudo eliminar el registro");
            responseTO.setResource(null);
        }
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }
}
