package com.micropackage.model;

/**
 * micropackage
 * Created by Lewis on 25/01/2017.
 * (C) Lewis Maitland 2017
 */
public class RegisterModel {

    private Package _package;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Package getPackage() {
        return _package;
    }

    public void setPackage(Package _package) {
        this._package = _package;
    }
}
