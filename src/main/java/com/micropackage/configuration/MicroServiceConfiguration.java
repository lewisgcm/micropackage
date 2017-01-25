package com.micropackage.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * micropackage
 * Created by Lewis on 25/01/2017.
 * (C) Lewis Maitland 2017
 */
@Component
@Configuration
public class MicroServiceConfiguration {

    @Value("${microservice.status.purge:500000}")
    public final Integer STATUS_PURGE_MILLISECONDS = 0;

    @Value("${microservice.status.down:150000}")
    public final Integer STATUS_DOWN_MILLISECONDS = 0;

    @Value("${microservice.status.timeout:15000}")
    public final Integer STATUS_TIMEOUT_MILLISECONDS = 0;
}
