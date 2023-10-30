package com.next.travel.hotel_service.repository;


import com.next.travel.hotel_service.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepo extends JpaRepository<HotelEntity, String> {
    @Query("SELECT MAX (h.hotelCode) FROM hotel h")
    String getNewId();


}
