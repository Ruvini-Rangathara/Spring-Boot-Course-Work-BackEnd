package com.next.travel.hotel_service.repository;

import com.next.travel.hotel_service.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepo  extends JpaRepository<RoomEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM discount", nativeQuery = true)
    String getLastId();
}
