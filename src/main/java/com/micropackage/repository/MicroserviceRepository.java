package com.micropackage.repository;

import com.micropackage.model.MicroService;
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
public interface MicroServiceRepository extends JpaRepository<MicroService, UUID> {
    MicroService findById(UUID id);

    @Modifying
    @Query("UPDATE MicroService m SET m.status = ?2 where m.lastKeepAlive < ?1")
    void updateMicroserviceStatus(Date date, StatusType statusType);

    @Modifying
    @Query("DELETE FROM MicroService m WHERE m.lastKeepAlive < ?1")
    void removeMicroserviceBeforeKeepalive( Date date );
}