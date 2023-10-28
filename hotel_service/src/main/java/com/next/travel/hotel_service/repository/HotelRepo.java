package com.next.travel.hotel_service.repository;


import com.next.travel.hotel_service.entity.HotelEntity;
import com.next.travel.hotel_service.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepo extends JpaRepository<HotelEntity, String> {
    @Query("SELECT MAX (h.hotelCode) FROM hotel h")
    String getNewId();


}
