package com.micropackage.service;

import com.micropackage.Application;
import com.micropackage.model.Package;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * micropackage
 * Created by Lewis on 26/01/2017.
 * (C) Lewis Maitland 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class PackageServiceTests {

    @Autowired
    protected PackageService service;

    @Test
    public void registerNewPackageNoDependencies() {
        Package aPackage = new Package();
        aPackage.setName( "test" );
        aPackage.setVersion( "1.0.0" );
        Package newPackage = service.createOrFind( aPackage );

        Assert.assertEquals( newPackage.getName(), aPackage.getName() );
        Assert.assertEquals( newPackage.getVersion(), aPackage.getVersion() );
        Assert.assertNotEquals( newPackage.getId(), null );
    }

    @Test
    public void registerNewPackageWithDependencies() {
        Package aPackage = new Package();
        aPackage.setName( "test" );
        aPackage.setVersion( "1.0.0" );
        Set<Package> deps = new HashSet<>();

        aPackage.setDependencies( deps );
        Package newPackage = service.createOrFind( aPackage );

        Assert.assertEquals( newPackage.getName(), aPackage.getName() );
        Assert.assertEquals( newPackage.getVersion(), aPackage.getVersion() );
        Assert.assertNotEquals( newPackage.getId(), null );
    }
}
