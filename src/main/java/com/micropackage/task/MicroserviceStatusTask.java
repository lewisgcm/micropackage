package com.micropackage.task;

import com.micropackage.service.MicroserviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lmaitland on 24/01/2017.
 */
@Component
public class MicroserviceStatusTask {

    private static final Logger log = LoggerFactory.getLogger(MicroserviceStatusTask.class);

    private MicroserviceService service;

    public MicroserviceStatusTask( MicroserviceService service ){
        this.service = service;
    }

    @Transactional
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        service.updateMicroserviceStatus();
        service.purgeOldMicroservices();
        log.info( "Finished purging and updating all microservice status" );
    }
}
