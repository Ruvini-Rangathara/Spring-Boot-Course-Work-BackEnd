package com.next.travel.vehicle_service.repository;

import com.next.travel.vehicle_service.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepo extends JpaRepository<DriverEntity, String> {
    @Query("SELECT MAX (d.driverId) FROM driver d")
    String getLastId();
}
