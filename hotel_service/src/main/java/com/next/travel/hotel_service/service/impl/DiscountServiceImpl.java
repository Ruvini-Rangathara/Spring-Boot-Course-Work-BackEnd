package com.next.travel.hotel_service.service.impl;

import com.next.travel.hotel_service.dto.DiscountDto;
import com.next.travel.hotel_service.entity.DiscountEntity;
import com.next.travel.hotel_service.exception.NotFoundException;
import com.next.travel.hotel_service.repository.DiscountRepo;
import com.next.travel.hotel_service.service.DiscountService;
import com.next.travel.hotel_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    DiscountRepo discountRepo;

    @Autowired
    Convertor convertor;


    @Override
    public DiscountDto save(DiscountDto discountDto) {
        return convertor.getDiscountDto(discountRepo.save(convertor.getDiscountEntity(discountDto)));
    }

    @Override
    public DiscountDto update(DiscountDto discountDto) {
        return convertor.getDiscountDto(discountRepo.save(convertor.getDiscountEntity(discountDto)));
    }

    @Override
    public void delete(String id) {
        if(!discountRepo.existsById(id)) throw new NotFoundException("Discount Not Found!");

        DiscountEntity discountEntity= discountRepo.getReferenceById(id);
        discountRepo.delete(discountEntity);
    }

    @Override
    public DiscountDto searchById(String id) {
        if(!discountRepo.existsById(id)) throw new NotFoundException("Discount Not Found!");
        return convertor.getDiscountDto(discountRepo.getReferenceById(id));
    }

    @Override
    public List<DiscountDto> getAll() {
        List<DiscountEntity> all = discountRepo.findAll();
        List<DiscountDto> list = new ArrayList<>();
        for (DiscountEntity entity: all) {
            list.add(convertor.getDiscountDto(entity));
        }
        return list;
    }

    @Override
    public String getLastId() {
        return discountRepo.getLastId();
    }
}
