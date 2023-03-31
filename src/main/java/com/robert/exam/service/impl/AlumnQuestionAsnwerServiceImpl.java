package com.robert.exam.service.impl;

import com.robert.exam.bean.AlumnQuestionAsnwerRequestTO;
import com.robert.exam.bean.ResponseTO;
import com.robert.exam.entity.*;
import com.robert.exam.repository.*;
import com.robert.exam.service.AlumnQuestionAsnwerService;
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
public class AlumnQuestionAsnwerServiceImpl implements AlumnQuestionAsnwerService {

    private static Logger log = LogManager.getLogger();

    @Autowired
    TestAssignationRepository testAssignationRepository;

    @Autowired
    TestQuestionRepository testQuestionRepository;

    @Autowired
    AlumnQuestionAnswerRepository alumnQuestionAnswerRepository;

    @Override
    public ResponseEntity<ResponseTO> getAll() {
        ResponseTO<List<AlumnQuestionAnswer>> responseTO = new ResponseTO<>();
        List<AlumnQuestionAnswer> instances = (List<AlumnQuestionAnswer>) alumnQuestionAnswerRepository.findAll();

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
        ResponseTO<AlumnQuestionAnswer> responseTO = new ResponseTO<>();

        Optional<AlumnQuestionAnswer> instance = alumnQuestionAnswerRepository.findById(id);

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
    public ResponseEntity<ResponseTO> evaluate(Long studentId, Long testId) {
        ResponseTO<List<TestAssignation>> responseTO = new ResponseTO<>();

        List<TestAssignation> instance = testAssignationRepository.getByAlumnAndTest(studentId, testId);

        if (instance.isEmpty()) {
            responseTO.setCode(1001);
            responseTO.setMessage("No se encontró el registro solicitado");
            responseTO.setResource(null);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        }

        instance.forEach(item -> {
            try {
                Double totalPoints = item.getAlumnQuestionAnswers().stream()
                        .filter(f -> f.getTestQuestion().getCorrectOption() == f.getAnswer())
                        .mapToDouble(aux -> aux.getTestQuestion().getValue()).sum();

                item.setGeneral_qualification(totalPoints);
                testAssignationRepository.save(item);
            } catch (Exception e) {
                log.info("Ocurrio una incidencia", e);
                log.info("Registro afectado {}", item);
            }
        });

        responseTO.setCode(1000);
        responseTO.setMessage("Se calificó correctamente");
        responseTO.setResource(instance);
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTO> save(AlumnQuestionAsnwerRequestTO requestTO) {
        ResponseTO<AlumnQuestionAnswer> responseTO = new ResponseTO<>();
        try {
            AlumnQuestionAnswer instance = new AlumnQuestionAnswer();

            if (requestTO.getId() != null) {
                Optional<AlumnQuestionAnswer> op = alumnQuestionAnswerRepository.findById(Long.valueOf(requestTO.getId()));
                if (op.isPresent()) {
                    instance = op.get();
                }
            }

            if(instance.getCreated_at() == null) {
                instance.setCreated_at(new Date(System.currentTimeMillis()));
            }

            Optional<TestAssignation> testAssignation = testAssignationRepository.findById(requestTO.getTestAssignationId());

            if(testAssignation.isEmpty()) {
                responseTO.setCode(1001);
                responseTO.setMessage("No se ha asignado este examen al alumno");
                responseTO.setResource(null);
                return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
            }

            instance.setTestAssignation(testAssignation.get());

            Optional<TestQuestion> question = testQuestionRepository.findById(requestTO.getTestQuestionId());

            if(question.isEmpty()) {
                responseTO.setCode(1002);
                responseTO.setMessage("El alumno no ha respondido esta pregunta");
                responseTO.setResource(null);
                return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
            }
            instance.setTestQuestion(question.get());

            instance.setAnswer(requestTO.getAnswer());

            instance = alumnQuestionAnswerRepository.save(instance);
            responseTO.setCode(1000);
            responseTO.setMessage("Guardado realizado exitosamente");
            responseTO.setResource(instance);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Ocurrio una incidencia {}", e);
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
            alumnQuestionAnswerRepository.deleteById(id);
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
