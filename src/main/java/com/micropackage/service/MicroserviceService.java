package com.micropackage.service;

import com.micropackage.configuration.MicroServiceConfiguration;
import com.micropackage.model.MicroService;
import com.micropackage.model.MicroServiceRegistration;
import com.micropackage.repository.MicroServiceRepository;
import com.micropackage.type.StatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lmaitland on 23/01/2017.
 */
@Service
public class MicroServiceService {

    private static final Logger log = LoggerFactory.getLogger(MicroServiceService.class);

    private MicroServiceRepository repository;
    private MicroServiceConfiguration configuration;
    private PackageService packageService;

    @Autowired
    public MicroServiceService(
            MicroServiceRepository repository,
            MicroServiceConfiguration configuration,
            PackageService packageService ) {
        this.configuration = configuration;
        this.repository = repository;
        this.packageService = packageService;
    }

    public MicroService register(MicroServiceRegistration registration) {
        MicroService microservice = new MicroService();
        microservice.setId( UUID.randomUUID() );
        microservice.setLastKeepAlive( new Date() );
        microservice.setPackage( packageService.register( registration.getPackage() ) );
        microservice.setStatus( StatusType.UP );
        microservice.setLocation( registration.getLocation() );
        microservice.setRegion( registration.getRegion() );
        microservice = repository.save( microservice );
        log.info(
                "Registering new MicroService %s, package %s@%s",
                microservice.getId(),
                microservice.getPackage().getName(),
                microservice.getPackage().getVersion()
        );
        return microservice;
    }

    public Boolean keepAlive(UUID id) {
        MicroService microservice = repository.findById( id );
        if(microservice == null ) {
            return false;
        }

        microservice.setStatus( StatusType.UP );
        microservice.setLastKeepAlive( new Date() );
        repository.save( microservice );
        log.info( "MicroService %s received keep-alive", id );
        return true;
    }

    public Boolean delete(UUID id) {
        MicroService microservice = repository.findById( id );
        if(microservice == null ) {
            return false;
        }
        repository.delete( microservice );
        log.info( "MicroService %s removed", id );
        return true;
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
