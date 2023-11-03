package com.next.travel.booking_service.service;

import com.next.travel.booking_service.dto.BookingDto;
import com.next.travel.booking_service.entity.BookingEntity;

import java.util.List;

public interface BookingService {
    BookingEntity save(BookingDto bookingDto);
    BookingDto update(BookingDto bookingDto);
    void delete(String id);
    BookingDto searchById(String id);
    List<BookingDto> getAll();
    boolean existById(String id);




}
