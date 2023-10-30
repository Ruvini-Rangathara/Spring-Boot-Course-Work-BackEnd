package com.next.travel.package_service.repository;


import com.next.travel.package_service.entity.PromotionEntity;
import org.springframework.data.repository.CrudRepository;

public interface PromotionRepo extends CrudRepository<PromotionEntity, String> {
    String getLastId();
}
