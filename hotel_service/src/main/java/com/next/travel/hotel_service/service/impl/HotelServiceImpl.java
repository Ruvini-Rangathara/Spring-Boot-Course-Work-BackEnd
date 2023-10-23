package com.next.travel.hotel_service.service.impl;

import com.next.travel.hotel_service.dto.HotelDto;
import com.next.travel.hotel_service.dto.OptionDto;
import com.next.travel.hotel_service.entity.HotelEntity;
import com.next.travel.hotel_service.entity.OptionEntity;
import com.next.travel.hotel_service.exception.NotFoundException;
import com.next.travel.hotel_service.repository.HotelRepo;
import com.next.travel.hotel_service.service.HotelService;
import com.next.travel.hotel_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepo hotelRepo;

    @Autowired
    Convertor convertor;
    @Override
    public HotelDto save(HotelDto hotelDto) {
        return convertor.getHotelDto(hotelRepo.save(convertor.getHotelEntity(hotelDto)));
    }

    @Override
    public HotelDto update(HotelDto hotelDto) {
        return convertor.getHotelDto(hotelRepo.save(convertor.getHotelEntity(hotelDto)));

    }

    @Override
    public void delete(String id) {
        if(!hotelRepo.existsById(id)) throw new NotFoundException("Hotel Not Found!");
        HotelEntity driverEntity = new HotelEntity();
        driverEntity.setHotelCode(id);
        hotelRepo.delete(driverEntity);
    }

    @Override
    public HotelDto searchById(String id) {
        if(!hotelRepo.existsById(id)) throw new NotFoundException("Hotel Not Found!");
        return convertor.getHotelDto(hotelRepo.getReferenceById(id));
    }

    @Override
    public List<HotelDto> getAll() {
        List<HotelEntity> all = hotelRepo.findAll();
        List<HotelDto> list = new ArrayList<>();
        for (HotelEntity entity: all) {
            list.add(convertor.getHotelDto(entity));
        }
        return list;
    }

    @Override
    public String getLastId() {
        return hotelRepo.getLastId();
    }


}
