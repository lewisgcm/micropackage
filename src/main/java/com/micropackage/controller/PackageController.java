package com.micropackage.controller;

import com.micropackage.model.Package;
import com.micropackage.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lmaitland on 26/01/2017.
 */
@RestController
@RequestMapping("/api/packages")
public class PackageController {

    private PackageService service;

    @Autowired
    public PackageController( PackageService service ) {
        this.service = service;
    }

    @RequestMapping(value = "/index.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Package> list() throws Exception {
        return service.findAll();
    }
}
