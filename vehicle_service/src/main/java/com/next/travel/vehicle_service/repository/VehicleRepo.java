package com.next.travel.vehicle_service.repository;

import com.next.travel.vehicle_service.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepo extends JpaRepository<VehicleEntity, String> {
    @Query("SELECT MAX (v.vehicleId) FROM vehicle v")
    String getLastId();
}
