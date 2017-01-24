package com.micropackage.model;
import sun.misc.Version;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lmaitland on 22/01/2017.
 */
@Entity()
public class Package implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String name;

    @Transient()
    private Version version;

    @Transient()
    private Set<Package> dependencies;

    @Transient()
    private Set<Microservice> microservices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Set<Package> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<Package> dependencies) {
        this.dependencies = dependencies;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Microservice> getMicroservices() {
        return microservices;
    }

    public void setMicroservices(Set<Microservice> microservices) {
        this.microservices = microservices;
    }
}
