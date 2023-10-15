package com.next.travel.package_service.service;

import com.next.travel.package_service.dto.PackageRoomDto;
import com.next.travel.package_service.dto.PackageVehicleDto;

import java.util.List;

public interface PackageVehicleService {
    PackageVehicleDto save(PackageVehicleDto packageVehicleDto);
    PackageVehicleDto update(PackageVehicleDto packageVehicleDto);
    void delete(Long id);
    PackageVehicleDto searchById(Long id);
    List<PackageVehicleDto> getAll();
    String getLastId();
}
