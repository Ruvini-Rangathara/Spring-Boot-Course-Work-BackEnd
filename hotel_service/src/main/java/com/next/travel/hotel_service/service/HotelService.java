package com.next.travel.hotel_service.service;

import com.next.travel.hotel_service.dto.HotelDto;
import com.next.travel.hotel_service.dto.OptionDto;

import java.util.List;

public interface HotelService {
    HotelDto save(HotelDto hotelDto);
    HotelDto update(HotelDto driverDto);
    void delete(String id);
    HotelDto searchById(String id);
    List<HotelDto> getAll();
    String getLastId();
    boolean existById(String id);

}
