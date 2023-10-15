package com.next.travel.package_service.repository;

import com.next.travel.package_service.entity.PackageEntity;
import com.next.travel.package_service.entity.PackageRoomEntity;
import com.next.travel.package_service.entity.PackageVehicleEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackageRoomRepo extends CrudRepository<PackageRoomEntity, Long> {

    PackageRoomEntity save(PackageRoomEntity packageRoomEntity);

    PackageRoomEntity getPackageRoomById(Long id);

    void deleteByPackageRoomId(Long id);

    @Query(value = "{}", sort = "{id : -1}", fields = "{id : 1}")
    List<PackageRoomEntity> findLastInsertedPackageRoom();

    @Query("{id : ?0}")
    PackageEntity updatePackageRoomEntityById(Long id);
}
