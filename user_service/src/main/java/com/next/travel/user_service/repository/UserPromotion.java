package com.next.travel.user_service.repository;

import com.next.travel.user_service.entity.UserPromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserPromotion   extends JpaRepository<UserPromotionEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM userPromotion", nativeQuery = true)
    int getLastId();
}
