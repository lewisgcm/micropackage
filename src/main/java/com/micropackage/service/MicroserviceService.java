package com.micropackage.service;

import com.micropackage.configuration.MicroServiceConfiguration;
import com.micropackage.model.MicroService;
import com.micropackage.model.Package;
import com.micropackage.repository.MicroServiceRepository;
import com.micropackage.type.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lmaitland on 23/01/2017.
 */
@Service
public class MicroServiceService {

    private MicroServiceRepository repository;
    private MicroServiceConfiguration configuration;

    @Autowired
    public MicroServiceService(
            MicroServiceRepository repository,
            MicroServiceConfiguration configuration ) {
        this.configuration = configuration;
        this.repository = repository;
    }

    public MicroService create(Package _package, URL location) {
        MicroService microservice = new MicroService();
        microservice.setId( UUID.randomUUID() );
        microservice.setLastKeepAlive( new Date() );
        microservice.setPackage( _package );
        microservice.setStatus( StatusType.UP );
        microservice.setLocation( location );
        microservice = save( microservice );
        return microservice;
    }

    public MicroService findById(UUID id) {
        return repository.findById( id );
    }

    public MicroService save(MicroService microservice) {
        return repository.save( microservice );
    }

    public List<MicroService> findAll() {
        return repository.findAll();
    }

    //Updates microservice status based on keep alive
    //Purge older
    @Transactional
    public void updateAndPurgeMicroservices() {
        repository.updateMicroserviceStatus(
                new Date(System.currentTimeMillis()-configuration.STATUS_TIMEOUT_MILLISECONDS),
                StatusType.TIMEOUT
        );

        repository.updateMicroserviceStatus(
                new Date(System.currentTimeMillis()-configuration.STATUS_DOWN_MILLISECONDS),
                StatusType.DOWN
        );
        repository.removeMicroserviceBeforeKeepalive( new Date(
                System.currentTimeMillis()-configuration.STATUS_PURGE_MILLISECONDS
        ) );
    }
}
