package com.robert.exam.service.impl;

import com.robert.exam.bean.ResponseTO;
import com.robert.exam.entity.Test;
import com.robert.exam.entity.ZoneTime;
import com.robert.exam.repository.ZoneTimeRepository;
import com.robert.exam.service.ZoneTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneTimeImpl implements ZoneTimeService {
    @Autowired
    ZoneTimeRepository zoneTimeRepository;

    @Override
    public ResponseEntity<ResponseTO> getAll() {
        ResponseTO<List<ZoneTime>> responseTO = new ResponseTO<>();
        List<ZoneTime> instances = (List<ZoneTime>) zoneTimeRepository.findAll();
        responseTO.setResource(instances);
        if(instances.isEmpty()) {
            responseTO.setCode(1001);
            responseTO.setMessage("No hay registros por mostrar");
            return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
        }

        responseTO.setCode(1000);
        responseTO.setMessage("consulta realizada correctamente");
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTO> get(long id) {
        ResponseTO<ZoneTime> responseTO = new ResponseTO<>();

        Optional<ZoneTime> instance = zoneTimeRepository.findById(id);

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
    public ResponseEntity<ResponseTO> save(ZoneTime zoneTime) {
        ResponseTO<ZoneTime> responseTO = new ResponseTO<>();
        if(zoneTime.getId() == null) {
            zoneTime.setCreated_at(new Date(System.currentTimeMillis()));
        }
        ZoneTime instance = zoneTimeRepository.save(zoneTime);
        responseTO.setCode(1000);
        responseTO.setMessage("Guardado realizado exitosamente");
        responseTO.setResource(instance);
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTO> delete(long id) {
        ResponseTO<Test> responseTO = new ResponseTO<>();
        zoneTimeRepository.deleteById(id);
        responseTO.setCode(1000);
        responseTO.setMessage("Eliminado realizado exitosamente");
        responseTO.setResource(null);
        return new ResponseEntity<ResponseTO>(responseTO, HttpStatus.OK);
    }
}
