package com.next.travel.package_service.service;

import com.next.travel.package_service.dto.*;
import com.next.travel.package_service.entity.PackageEntity;

import java.util.List;

public interface PackageService {
    PackageEntity save(PackageDto packageDto);
    PackageDto update(PackageDto packageDto);
    void delete(String id);
    PackageDto searchById(String id);
    List<PackageDto> getAll();
    String getNewId();
    boolean existById(String id);

    List<PackageDto> getAllByStatus(String status);




    GuideDto getFullProfileDataOfGuide(GuideDto guideDto);

    VehicleDto getFullProfileDataOfVehicle(VehicleDto vehicleDto);

    HotelDto getFullProfileDataOfHotel(HotelDto hotelDto);

}
