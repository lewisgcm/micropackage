package com.micropackage.repository;

import com.micropackage.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by lmaitland on 23/01/2017.
 */
@Component
public interface PackageRepository extends JpaRepository<Package, UUID> {
}
