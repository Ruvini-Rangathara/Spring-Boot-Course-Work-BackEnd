package com.next.travel.hotel_service.repository;


import com.next.travel.hotel_service.entity.HotelEntity;
import com.next.travel.hotel_service.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepo extends JpaRepository<HotelEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM hotel", nativeQuery = true)
    String getLastId();

    @Query(value = "SELECT r FROM room r JOIN hotel h ON r.hotel_id = h.hotel_id WHERE h.hotel_code = :hotelCode AND h.category = :category", nativeQuery = true)
    List<RoomEntity> getRoomsByHotelCodeAndCategory(@Param("hotelCode") String hotelCode, @Param("category") String category);

}
