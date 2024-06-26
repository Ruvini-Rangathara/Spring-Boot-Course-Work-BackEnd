package com.next.travel.hotel_service.repository;

import com.next.travel.hotel_service.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepo  extends JpaRepository<DiscountEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM discount", nativeQuery = true)
    String getLastId();
}
