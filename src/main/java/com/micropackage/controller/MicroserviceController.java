package com.micropackage.controller;

import com.micropackage.model.MicroService;
import com.micropackage.model.Package;
import com.micropackage.service.MicroServiceService;
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
public class MicroServiceController {

    private MicroServiceService service;
    private PackageService packageService;

    @Autowired
    public MicroServiceController(
            MicroServiceService service,
            PackageService packageService ) {
        this.service = service;
        this.packageService = packageService;
    }

    @RequestMapping(value = "/index.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public MicroService register(Package aPackage, HttpServletRequest request) throws Exception {

        InetAddress address = InetAddress.getByName( request.getRemoteAddr() );
        String url;

        if( address instanceof Inet4Address ) {
            url = "http://" + request.getRemoteAddr();
        } else {
            url = "http://[" + request.getRemoteAddr() + "]";
        }
        Package saved = packageService.save( aPackage );
        MicroService microservice = service.create( saved, new URL( url ) );
        return microservice;
    }

    @RequestMapping(value = "/index.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MicroService> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/keepalive/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void keepAlive(@PathVariable UUID id) {
        MicroService microservice = service.findById( id );
        microservice.setStatus( StatusType.UP );
        microservice.setLastKeepAlive( new Date() );
        service.save( microservice );
    }
}
