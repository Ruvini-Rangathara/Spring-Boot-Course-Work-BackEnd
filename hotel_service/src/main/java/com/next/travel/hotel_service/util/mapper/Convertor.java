package com.next.travel.hotel_service.util.mapper;


import com.next.travel.hotel_service.dto.DiscountDto;
import com.next.travel.hotel_service.dto.HotelDto;
import com.next.travel.hotel_service.dto.OptionDto;
import com.next.travel.hotel_service.dto.RequestHotelDto;
import com.next.travel.hotel_service.entity.DiscountEntity;
import com.next.travel.hotel_service.entity.HotelEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Convertor {
    @Autowired
    private final ModelMapper modelMapper;

    public Convertor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public HotelDto getHotelDto(HotelEntity hotelEntity){
        return modelMapper.map(hotelEntity, HotelDto.class);
    }

    public HotelEntity getHotelEntity(HotelDto hotelDto){
        return modelMapper.map(hotelDto, HotelEntity.class);
    }

    public DiscountDto getDiscountDto(DiscountEntity discountEntity){
        return modelMapper.map(discountEntity, DiscountDto.class);
    }

    public DiscountEntity getDiscountEntity(DiscountDto discountDto){
        return modelMapper.map(discountDto, DiscountEntity.class);
    }
}
