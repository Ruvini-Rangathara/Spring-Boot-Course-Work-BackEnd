package com.next.travel.vehicle_service.service;

import com.next.travel.vehicle_service.dto.DriverDto;

import java.util.List;

public interface DriverService {
    DriverDto save(DriverDto driverDto);
    DriverDto update(DriverDto driverDto);
    boolean delete(String id);
    DriverDto searchById(String id);
    List<DriverDto> getAll();
    String getNewId();
    boolean existById(String id);

}
