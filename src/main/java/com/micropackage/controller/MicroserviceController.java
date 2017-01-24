package com.micropackage.controller;

import com.micropackage.model.Microservice;
import com.micropackage.model.Package;
import com.micropackage.service.MicroserviceService;
import com.micropackage.service.PackageService;
import com.micropackage.type.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lmaitland on 23/01/2017.
 */
@RestController()
@RequestMapping("/api/services/")
public class MicroserviceController {

    private MicroserviceService service;
    private PackageService packageService;

    @Autowired
    public MicroserviceController(
            MicroserviceService service,
            PackageService packageService ) {
        this.service = service;
        this.packageService = packageService;
    }

    @RequestMapping(value = "/index.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Microservice register(Package aPackage, HttpServletRequest request) throws Exception {
        Package saved = packageService.save( aPackage );
        Microservice microservice = new Microservice();
        microservice.setId( UUID.randomUUID() );
        microservice.setLastKeepAlive( new Date() );
        microservice.setPackage( saved );
        microservice.setStatus( StatusType.UP );
        InetAddress address = InetAddress.getByName( request.getRemoteAddr() );
        String url;

        if( address instanceof Inet4Address ) {
            url = "http://" + request.getRemoteAddr();
        } else {
            url = "http://[" + request.getRemoteAddr() + "]";
        }

        microservice.setLocation( new URL( url ) );
        microservice = service.save( microservice );
        return microservice;
    }

    @RequestMapping(value = "/index.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Microservice> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/keepalive/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void keepAlive(@PathVariable UUID id) {
        Microservice microservice = service.findById( id );
        microservice.setStatus( StatusType.UP );
        microservice.setLastKeepAlive( new Date() );
        service.save( microservice );
    }
}
