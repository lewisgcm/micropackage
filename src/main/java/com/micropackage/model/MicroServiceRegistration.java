package com.micropackage.model;

import java.net.URL;
import java.util.Set;

/**
 * micropackage
 * Created by Lewis on 25/01/2017.
 * (C) Lewis Maitland 2017
 */
public class MicroServiceRegistration {

    private Package aPackage;
    private Set<Package> dependencies;
    private URL location;
    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public Set<Package> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<Package> dependencies) {
        this.dependencies = dependencies;
    }

    public Package getPackage() {
        return aPackage;
    }

    public void setPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
