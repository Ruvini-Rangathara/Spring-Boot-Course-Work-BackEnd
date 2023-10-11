package com.next.travel.vehicle_service.repository;

import com.next.travel.vehicle_service.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<DriverEntity, String> {
}
