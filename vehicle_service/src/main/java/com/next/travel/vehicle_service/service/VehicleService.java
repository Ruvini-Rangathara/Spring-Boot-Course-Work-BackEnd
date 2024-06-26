package com.next.travel.vehicle_service.service;


import com.next.travel.vehicle_service.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    VehicleDto save(VehicleDto vehicleDto);
    VehicleDto update(VehicleDto vehicleDto);
    boolean delete(String id);
    VehicleDto searchById(String id);
    List<VehicleDto> getAll();
    String getNewId();
    boolean existById(String id);
}
