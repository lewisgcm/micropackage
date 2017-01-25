package com.micropackage.service;

import com.micropackage.model.Package;
import com.micropackage.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    public Package register(Package aPackage){
        Package existing = createOrFind( aPackage );
        //Register Dependencies if defined
        if( aPackage.getDependencies() != null ) {
            existing.setDependencies(
                    aPackage
                        .getDependencies()
                        .stream()
                        .map( d -> createOrFind(d) )
                        .collect(Collectors.toSet()) );
        }
        return existing;
    }

    public Package createOrFind(Package aPackage){
        Package existing = repository.findByNameAndVersion( aPackage.getName(), aPackage.getVersion() );
        if( existing == null ) {
            existing = repository.save( aPackage );
        }
        return existing;
    }
}
