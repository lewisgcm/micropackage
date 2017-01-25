package com.micropackage.task;

import com.micropackage.service.MicroServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by lmaitland on 24/01/2017.
 */
@Component
public class MicroServiceStatusTask {

    private static final Logger log = LoggerFactory.getLogger(MicroServiceStatusTask.class);

    private MicroServiceService service;

    @Autowired
    public MicroServiceStatusTask(MicroServiceService service ){
        this.service = service;
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        service.updateAndPurgeMicroservices();
        log.info( "Finished purging and updating all microservice status" );
    }
}
