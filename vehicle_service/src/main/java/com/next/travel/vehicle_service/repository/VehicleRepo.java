package com.next.travel.vehicle_service.repository;

import com.next.travel.vehicle_service.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepo extends JpaRepository<VehicleEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM vehicle", nativeQuery = true)
    String getLastId();
}
