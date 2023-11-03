package com.next.travel.booking_service.service.impl;


import com.next.travel.booking_service.dto.BookingDto;
import com.next.travel.booking_service.entity.BookingEntity;
import com.next.travel.booking_service.exception.NotFoundException;
import com.next.travel.booking_service.repository.BookingRepo;
import com.next.travel.booking_service.service.BookingService;
import com.next.travel.booking_service.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepo packageRepo;

    @Autowired
    Convertor convertor;

    @Override
    public BookingEntity save(BookingDto bookingDto) {
        try {
            return packageRepo.save(convertor.getBookingEntity(bookingDto));
        } catch (Exception e) {
            throw new NotFoundException("Not Found!");
        }
    }

    @Override
    public BookingDto update(BookingDto bookingDto) {
        if (!packageRepo.existsById(bookingDto.getPackageId())) throw new NotFoundException("Not Found!");

        return convertor.getBookingDTO(packageRepo.save(convertor.getBookingEntity(bookingDto)));
    }

    @Override
    public void delete(String id) {
        if (!packageRepo.existsById(id)) throw new NotFoundException("Not Found!");
        packageRepo.deleteById(id);
    }

    @Override
    public BookingDto searchById(String id) {
        if (!packageRepo.existsById(id)) throw new NotFoundException("Not Found!");
        return convertor.getBookingDTO(packageRepo.getReferenceById(id));
    }

    @Override
    public List<BookingDto> getAll() {
        List<BookingEntity> all = packageRepo.findAll();
        List<BookingDto> list = new ArrayList<>();
        for (BookingEntity entity : all) {
            list.add(convertor.getBookingDTO(entity));
        }
        return list;
    }


    @Override
    public boolean existById(String id) {
        return packageRepo.existsById(id);
    }



}
