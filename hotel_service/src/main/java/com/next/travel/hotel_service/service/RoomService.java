package com.next.travel.hotel_service.service;


import com.next.travel.hotel_service.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto save(RoomDto vehicleDto);
    RoomDto update(RoomDto vehicleDto);
    void delete(String id);
    RoomDto searchById(String id);
    List<RoomDto> getAll();
    String getLastId();
}
