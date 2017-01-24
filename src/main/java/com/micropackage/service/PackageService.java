package com.micropackage.service;

import com.micropackage.model.Package;
import com.micropackage.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lmaitland on 23/01/2017.
 */
@Service
public class PackageService {

    private PackageRepository repository;

    @Autowired
    public PackageService(PackageRepository repository) {
        this.repository = repository;
    }

    public Package save(Package aPackage) {
        return repository.save( aPackage );
    }
}
