package com.micropackage.service;

import com.micropackage.model.Microservice;
import com.micropackage.repository.MicroserviceRepository;
import com.micropackage.type.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lmaitland on 23/01/2017.
 */
@Service
public class MicroserviceService {

    public static final int MICROSERVICE_PURGE_MILLISECONDS = 60*60*60*2; // 2 HOURS
    public static final int MICROSERVICE_DOWN_MILLISECONDS = 60*60*60; //1 HOUR
    public static final int MICROSERVICE_TIMEOUT_MILLISECONDS = 60*60*30; // 30 Minutes

    private MicroserviceRepository repository;

    @Autowired
    public MicroserviceService( MicroserviceRepository repository) {
        this.repository = repository;
    }

    public Microservice findById(UUID id) {
        return repository.findById( id );
    }

    public Microservice save(Microservice microservice) {
        return repository.save( microservice );
    }

    public List<Microservice> findAll() {
        return repository.findAll();
    }

    //Updates microservice status based on keep alive
    public void updateMicroserviceStatus() {
        repository.updateMicroserviceStatus(
                new Date(System.currentTimeMillis()-MICROSERVICE_TIMEOUT_MILLISECONDS),
                StatusType.TIMEOUT
        );

        repository.updateMicroserviceStatus(
                new Date(System.currentTimeMillis()-MICROSERVICE_DOWN_MILLISECONDS),
                StatusType.DOWN
        );
    }

    public void purgeOldMicroservices() {
        repository.removeMicroserviceBeforeKeepalive( new Date(
                System.currentTimeMillis()-MICROSERVICE_PURGE_MILLISECONDS
        ) );
    }
}
