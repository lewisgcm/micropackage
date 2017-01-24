package com.micropackage.model;

import com.micropackage.type.StatusType;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lmaitland on 22/01/2017.
 */
@Entity
public class Microservice implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private StatusType status;

    @Transient
    private Package _package;

    private URL location;

    private Date lastKeepAlive;

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public Date getLastKeepAlive() {
        return lastKeepAlive;
    }

    public void setLastKeepAlive(Date lastKeepAlive) {
        this.lastKeepAlive = lastKeepAlive;
    }

    public Package getPackage() {
        return _package;
    }

    public void setPackage(Package _package) {
        this._package = _package;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}