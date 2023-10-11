package com.next.travel.hotel_service.service.impl;


import com.next.travel.hotel_service.dto.RoomDto;
import com.next.travel.hotel_service.entity.RoomEntity;
import com.next.travel.hotel_service.exception.NotFoundException;
import com.next.travel.hotel_service.repository.RoomRepo;
import com.next.travel.hotel_service.service.RoomService;
import com.next.travel.hotel_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepo roomRepo;



    @Autowired
    Convertor convertor;


    @Override
    public RoomDto save(RoomDto roomDto) {
        return convertor.getRoomDto(roomRepo.save(convertor.getRoomEntity(roomDto)));
    }

    @Override
    public RoomDto update(RoomDto roomDto) {
        return convertor.getRoomDto(roomRepo.save(convertor.getRoomEntity(roomDto)));
    }

    @Override
    public void delete(String id) {
        if(!roomRepo.existsById(id)) throw new NotFoundException("Room Not Found!");

        RoomEntity roomEntity= roomRepo.getReferenceById(id);
        roomRepo.delete(roomEntity);
    }

    @Override
    public RoomDto searchById(String id) {
        if(!roomRepo.existsById(id)) throw new NotFoundException("Room Not Found!");
        return convertor.getRoomDto(roomRepo.getReferenceById(id));
    }

    @Override
    public List<RoomDto> getAll() {
        List<RoomEntity> all = roomRepo.findAll();
        List<RoomDto> list = new ArrayList<>();
        for (RoomEntity entity: all) {
            list.add(convertor.getRoomDto(entity));
        }
        return list;
    }

    @Override
    public String getLastId() {
        return roomRepo.getLastId();
    }
}
