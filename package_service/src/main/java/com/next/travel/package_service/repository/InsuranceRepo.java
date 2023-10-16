package com.next.travel.package_service.repository;

import com.next.travel.package_service.dto.InsuranceDto;
import com.next.travel.package_service.entity.InsuranceEntity;
import com.next.travel.package_service.entity.PackageEntity;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceRepo extends CrudRepository<InsuranceDto,String> {

    InsuranceEntity save(InsuranceEntity insuranceEntity);

    InsuranceEntity getInsuranceById(String id);

    void deleteById(String id);
}
