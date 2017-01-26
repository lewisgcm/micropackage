package com.micropackage.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    private String name;
    private String version;

    @ManyToMany
    private Set<Package> dependencies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
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
}
