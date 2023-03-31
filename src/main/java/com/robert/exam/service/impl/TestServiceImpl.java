package com.robert.exam.service.impl;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.bean.TestRequestTO;
import com.robert.exam.entity.Test;
import com.robert.exam.entity.TestQuestion;
import com.robert.exam.repository.TestQuestionRepository;
import com.robert.exam.repository.TestRepository;
import com.robert.exam.service.TestService;
import com.robert.exam.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class TestServiceImpl implements TestService {

    private static Logger log = LogManager.getLogger();

    @Autowired
    TestRepository testRepositoty;

    @Autowired
    TestQuestionRepository testQuestionRepository;

    @Autowired
    Helper helper;

    @Override
    public ResponseEntity<ResponseTO> getAll() {
        log.info("se inicia flujo de obtener todos los examenes");
        ResponseTO<List<Test>> responseTO = new ResponseTO<>();
        List<Test> instances = (List<Test>) testRepositoty.findAll();
        responseTO.setResource(instances);
        if(instances.isEmpty()) {
            log.info("no existen examenes");
            responseTO.setCode(1001);
            responseTO.setMessage("No hay registros por mostrar");
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        }
        log.info("flujo terminado correctamente");
        responseTO.setCode(1000);
        responseTO.setMessage("consulta realizada correctamente");
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTO> get(long id) {
        ResponseTO<Test> responseTO = new ResponseTO<>();

        Optional<Test> instance = testRepositoty.findById(id);

        if (!instance.isPresent()) {
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
    public ResponseEntity<ResponseTO> save(TestRequestTO request) {
        log.info("Inicia flujo de guardado");
        ResponseTO<Test> responseTO = new ResponseTO<>();
        try {
            var instance = new Test();

            if(request.getTest().getId() == null) {
                log.info("examen nuevo");
                instance.setCreated_at(new Date(System.currentTimeMillis()));
            } else {
                log.info("no es nuevo, buscando");
                Optional<Test> op = testRepositoty.findById(request.getTest().getId());
                if (op.isPresent()) {
                    instance = op.get();
                } else {
                    instance.setCreated_at(new Date(System.currentTimeMillis()));
                }
            }
            instance.setName(request.getTest().getName());

            log.info("se guarda el examen");
            instance = testRepositoty.save(instance);

            if(request.getTestQuestions() != null) {
                log.info("trae preguntas se procede a guardar");
                List<TestQuestion> instancesQuestion = new ArrayList<>();

                Test finalInstance = instance;
                request.getTestQuestions().forEach(item -> {
                    TestQuestion auxTestQuestion = new TestQuestion();
                    Optional<TestQuestion> testQuestionSaved = testQuestionRepository.findById(item.getId() != null ? item.getId() : 0);

                    if (testQuestionSaved.isPresent()) {
                        log.info("ya existe la pregunta, buscando");
                        auxTestQuestion = testQuestionSaved.get();
                    }

                    auxTestQuestion.setTest(finalInstance);
                    auxTestQuestion.setQuestion(item.getQuestion());
                    auxTestQuestion.setAnswer1(item.getAnswer1());
                    auxTestQuestion.setAnswer2(item.getAnswer2());
                    auxTestQuestion.setAnswer3(item.getAnswer3());
                    auxTestQuestion.setAnswer4(item.getAnswer4());
                    auxTestQuestion.setCorrectOption(item.getCorrectOption());
                    auxTestQuestion.setCreated_at(new Date(System.currentTimeMillis()));
                    auxTestQuestion.setValue(helper.assingValue(request.getTestQuestions().size()));
                    instancesQuestion.add(auxTestQuestion);
                    testQuestionRepository.save(auxTestQuestion);
                });
                log.info("preguntas que se guardaran {}", instancesQuestion.toString());
                instance.setTestQuestions(instancesQuestion);
            }

            responseTO.setCode(1000);
            responseTO.setMessage("Guardado realizado exitosamente");
            responseTO.setResource(instance);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);

        } catch (Exception e) {
            log.info("ocurrio una incidencia", e);
            responseTO.setResource(null);
            responseTO.setMessage("Ocurrio un error al guardar");
            responseTO.setCode(1005);
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<ResponseTO> delete(long id) {
        ResponseTO<Test> responseTO = new ResponseTO<>();
        testRepositoty.deleteById(id);
        responseTO.setCode(1000);
        responseTO.setMessage("Eliminado realizado exitosamente");
        responseTO.setResource(null);
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }
}
