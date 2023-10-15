package com.next.travel.package_service.service;

import com.next.travel.package_service.dto.PackageDto;
import com.next.travel.package_service.dto.PackageRoomDto;

import java.util.List;

public interface PackageRoomService {
    PackageRoomDto save(PackageRoomDto packageRoomDto);
    PackageRoomDto update(PackageRoomDto packageRoomDto);
    void delete(Long id);
    PackageRoomDto searchById(Long id);
    List<PackageRoomDto> getAll();
    String getLastId();
}
