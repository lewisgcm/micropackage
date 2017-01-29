package com.micropackage.repository;

import com.micropackage.model.MicroService;
import com.micropackage.type.StatusType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lmaitland on 23/01/2017.
 */
@Component
public interface MicroServiceRepository extends JpaRepository<MicroService, UUID> {
    MicroService findById(UUID id);

    @Modifying
    @Query("UPDATE MicroService m SET m.status = ?2 where m.lastKeepAlive < ?1")
    void updateMicroserviceStatus(Date date, StatusType statusType);

    @Modifying
    @Query("DELETE FROM MicroService m WHERE m.lastKeepAlive < ?1")
    void removeMicroserviceBeforeKeepalive(Date date);

    @Query("SELECT m FROM MicroService m WHERE m._package.name = ?1 and m._package.version = ?2 and m.region = ?3 and m.status = ?4 ORDER BY m.lastKeepAlive DESC")
    List<MicroService> findAvailableByPackageNameAndVersionAndRegionAndStatus(String name, String version, String region, StatusType status, Pageable pageable);

    @Query("SELECT m FROM MicroService m WHERE m._package.name = ?1 and m._package.version = ?2 and m.status = ?3 ORDER BY m.lastKeepAlive DESC")
    List<MicroService> findAvailableByPackageNameAndVersionAndStatus(String name, String version, StatusType status, Pageable pageable);
}