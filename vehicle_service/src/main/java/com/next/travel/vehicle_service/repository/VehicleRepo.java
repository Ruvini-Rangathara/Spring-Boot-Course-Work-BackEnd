package com.next.travel.vehicle_service.repository;

import com.next.travel.vehicle_service.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<VehicleEntity, String> {
}
