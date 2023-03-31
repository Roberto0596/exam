package com.robert.exam.service.impl;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.TestAssignationRequestTO;
import com.robert.exam.entity.Student;
import com.robert.exam.entity.Test;
import com.robert.exam.entity.TestAssignation;
import com.robert.exam.entity.ZoneTime;
import com.robert.exam.exception.EmailException;
import com.robert.exam.repository.StudentRepository;
import com.robert.exam.repository.TestAssignationRepository;
import com.robert.exam.repository.TestRepository;
import com.robert.exam.repository.ZoneTimeRepository;
import com.robert.exam.service.TestAssignationService;
import com.robert.exam.util.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TestAssignationServiceImpl  implements TestAssignationService {

    private static Logger log = LogManager.getLogger();

    @Autowired
    TestAssignationRepository testAssignationRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ZoneTimeRepository timeRepository;

    @Autowired
    TestRepository testRepositoty;

    @Autowired
    Helper helper;

    @Override
    public ResponseEntity<ResponseTO> getAll() {
        ResponseTO<List<TestAssignation>> responseTO = new ResponseTO<>();
        List<TestAssignation> instances = (List<TestAssignation>) testAssignationRepository.findAll();

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
        ResponseTO<TestAssignation> responseTO = new ResponseTO<>();

        Optional<TestAssignation> instance = testAssignationRepository.findById(id);

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
    public ResponseEntity<ResponseTO> getResultStudentByTest(Long studentId, Long testId) {
        ResponseTO<List<TestAssignation>> responseTO = new ResponseTO<>();

        List<TestAssignation> instance = testAssignationRepository.getByAlumnAndTest(studentId, testId);

        if (instance.isEmpty()) {
            responseTO.setCode(1001);
            responseTO.setMessage("No se encontró el registro solicitado");
            responseTO.setResource(null);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        }

        responseTO.setCode(1000);
        responseTO.setMessage("Consulta realizada correctamente");
        responseTO.setResource(instance);

        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTO> getResultStudent(Long studentId) {
        ResponseTO<List<TestAssignation>> responseTO = new ResponseTO<>();

        List<TestAssignation> instance = testAssignationRepository.getByAlumn(studentId);

        if (instance.isEmpty()) {
            responseTO.setCode(1001);
            responseTO.setMessage("No se encontró el registro solicitado");
            responseTO.setResource(null);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        }

        responseTO.setCode(1000);
        responseTO.setMessage("Consulta realizada correctamente");
        responseTO.setResource(instance);
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTO> save(TestAssignationRequestTO requestTO) {
        log.info("request {}", requestTO);
        ResponseTO<TestAssignation> responseTO = new ResponseTO<>();
        try {
            TestAssignation instance = new TestAssignation();

            if (requestTO.getId() != null) {
                Optional<TestAssignation> op = testAssignationRepository.findById(Long.parseLong(requestTO.getId()));
                if (op.isPresent()) {
                    instance = op.get();
                } else {
                    instance.setCreated_at(new Date(System.currentTimeMillis()));
                }
            } else {
                instance.setCreated_at(new Date(System.currentTimeMillis()));
            }

            Optional<Student> student = studentRepository.findById(requestTO.getStudent_id());

            if(student.isEmpty()) {
                responseTO.setCode(1001);
                responseTO.setMessage("El estudiante no existe");
                responseTO.setResource(null);
                return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
            }

            instance.setStudent(student.get());

            Optional<Test> test = testRepositoty.findById(requestTO.getTest_id());

            if(test.isEmpty()) {
                responseTO.setCode(1002);
                responseTO.setMessage("El examen no existe");
                responseTO.setResource(null);
                return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
            }
            instance.setTest(test.get());

            if(requestTO.getZone_id() == null) {
                responseTO.setCode(1003);
                responseTO.setMessage("La zona horaria es requerida");
                responseTO.setResource(null);
                return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
            }

            Optional<ZoneTime> timeZone = timeRepository.findById(requestTO.getZone_id());
            if(timeZone.isEmpty()) {
                responseTO.setCode(1003);
                responseTO.setMessage("La zona horaria no existe");
                responseTO.setResource(null);
                return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
            }

            instance.setTimeZone(timeZone.get());

            if (requestTO.getApplicationDate() == null) {
                responseTO.setCode(1005);
                responseTO.setMessage("La fecha de aplicacion del examen es requerida");
                return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
            }

            instance.setApplicationDate(Timestamp.valueOf(helper.getDateByTimeZone(instance.getTimeZone().getName(), requestTO.getApplicationDate())));

            instance.setGeneral_qualification(requestTO.getGeneral_qualification());

            try {
                helper.sendEmail(instance, "Examen asignado");
            } catch (EmailException e) {
                log.info("No se pudo enviar el correo");
            }

            instance = testAssignationRepository.save(instance);

            responseTO.setCode(1000);
            responseTO.setMessage("Guardado realizado exitosamente");
            responseTO.setResource(instance);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
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
            testAssignationRepository.deleteById(id);
            responseTO.setCode(1000);
            responseTO.setMessage("Eliminado realizado exitosamente");
            responseTO.setResource(null);
        } catch (Exception e) {
            log.info("Ocurrio una incidencia {}", e);
            responseTO.setCode(1005);
            responseTO.setMessage("No se pudo eliminar el registro");
            responseTO.setResource(null);
        }
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }
}
