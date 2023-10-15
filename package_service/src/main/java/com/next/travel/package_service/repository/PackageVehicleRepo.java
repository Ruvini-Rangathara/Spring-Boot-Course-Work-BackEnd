package com.next.travel.package_service.repository;

import com.next.travel.package_service.entity.PackageVehicleEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackageVehicleRepo extends CrudRepository<PackageVehicleEntity, Long> {

    PackageVehicleEntity save(PackageVehicleEntity packageVehicleEntity);

    PackageVehicleEntity getPackageVehicleById(Long id);

    void deleteByPackageVehicleId(Long id);

    @Query(value = "{}", sort = "{id : -1}", fields = "{id : 1}")
    List<PackageVehicleEntity> findLastInsertedPackageVehicle();

    @Query("{id : ?0}")
    PackageVehicleEntity updatePackageVehicleEntityById(Long id);
}
