package com.micropackage.repository;

import com.micropackage.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

/**
 * Created by lmaitland on 23/01/2017.
 */
@Component
public interface PackageRepository extends JpaRepository<Package, UUID> {
    @Query("SELECT p FROM Package p WHERE p.name = ?1 AND p.version = ?2")
    Package findByNameAndVersion(String name, String version);

    @Query("UPDATE Package p SET p.name = ?1, p.version = ?2, p.dependencies = ?3 WHERE p.id = ?4")
    Package updateWithDependencies(String name, String version, Set<Package> packages, UUID id);
}
