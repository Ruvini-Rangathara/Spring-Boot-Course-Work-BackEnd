package com.next.travel.user_service.repository;

import com.next.travel.user_service.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PromotionRepo extends JpaRepository<PromotionEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM promotion", nativeQuery = true)
    String getLastId();
}
