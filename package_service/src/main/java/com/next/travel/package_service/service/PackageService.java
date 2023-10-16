package com.next.travel.package_service.service;

import com.next.travel.package_service.dto.GuideDto;
import com.next.travel.package_service.dto.PackageDto;
import com.next.travel.package_service.dto.VehicleDto;

import java.util.List;

public interface PackageService {
    PackageDto save(PackageDto packageDto);
    PackageDto update(PackageDto packageDto);
    void delete(String id);
    PackageDto searchById(String id);
    List<PackageDto> getAll();
    String getLastId();

    GuideDto getFullProfileDataOfGuide(GuideDto guideDto);

    VehicleDto getFullProfileDataOfVehicle(VehicleDto vehicleDto);
}
