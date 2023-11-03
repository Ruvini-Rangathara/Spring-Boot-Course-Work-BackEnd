package com.next.travel.booking_service.util;


import com.next.travel.booking_service.dto.BookingDto;
import com.next.travel.booking_service.entity.BookingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Convertor {

    private final ModelMapper modelMapper;

    Convertor(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public BookingDto getBookingDTO(BookingEntity packageEntity){
        return modelMapper.map(packageEntity, BookingDto.class);
    }
    public BookingEntity getBookingEntity(BookingDto bookingDto){
        return modelMapper.map(bookingDto, BookingEntity.class);
    }




}
