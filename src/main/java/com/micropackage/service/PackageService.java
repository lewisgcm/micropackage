package com.micropackage.service;

import com.micropackage.model.Package;
import com.micropackage.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
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
        Set<Package> dependencies = new HashSet<>();
        //Register Dependencies if defined
        if( aPackage.getDependencies() != null ) {
                    aPackage
                            .getDependencies()
                            .stream()
                            .map( d -> createOrFind(d) )
                            .forEach( d -> dependencies.add(d) );
        }
        aPackage.setDependencies( dependencies );
        Package existing = createOrFind( aPackage );
        return existing;
    }

    public Package createOrFind(Package aPackage){
        Package existing = repository.findByNameAndVersion( aPackage.getName(), aPackage.getVersion() );
        if( existing != null ) {
            aPackage.setId( existing.getId() );
        }
        existing = repository.save( aPackage );
        return existing;
    }
}
