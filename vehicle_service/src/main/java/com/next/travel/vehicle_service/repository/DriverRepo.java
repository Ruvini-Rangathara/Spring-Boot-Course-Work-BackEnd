package com.next.travel.vehicle_service.repository;

import com.next.travel.vehicle_service.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepo extends JpaRepository<DriverEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM driver", nativeQuery = true)
    String getLastId();
}
