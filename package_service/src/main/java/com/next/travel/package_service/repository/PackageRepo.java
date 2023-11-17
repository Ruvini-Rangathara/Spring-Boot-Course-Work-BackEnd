package com.next.travel.package_service.repository;

import com.next.travel.package_service.entity.PackageEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepo extends CrudRepository<PackageEntity,String> {

//    Boolean save(PackageEntity packageEntity);

    PackageEntity getPackageByPackageId(String id);

    void deleteById(String id);

    @Query(value = "{}", sort = "{packageId: -1}", fields = "{packageId: 1}")
    List<PackageEntity> findLastInsertedId();

    List<PackageEntity> findAllByStatus(String status);
}
