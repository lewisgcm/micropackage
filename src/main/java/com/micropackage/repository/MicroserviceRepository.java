package com.micropackage.repository;

import com.micropackage.model.Microservice;
import com.micropackage.type.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lmaitland on 23/01/2017.
 */
@Component
public interface MicroserviceRepository extends JpaRepository<Microservice, UUID> {
    Microservice findById(UUID id);

    @Modifying
    @Query("UPDATE Microservice m SET m.status = ?2 where m.lastKeepAlive < ?1")
    void updateMicroserviceStatus(Date date, StatusType statusType);

    @Modifying
    @Query("DELETE FROM Microservice m WHERE m.lastKeepAlive < ?1")
    void removeMicroserviceBeforeKeepalive( Date date );
}