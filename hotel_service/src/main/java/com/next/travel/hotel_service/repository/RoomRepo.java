package com.next.travel.hotel_service.repository;

import com.next.travel.hotel_service.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepo extends JpaRepository<RoomEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM room", nativeQuery = true)
    String getLastId();
}
