package com.next.travel.hotel_service.service.impl;


import com.next.travel.hotel_service.dto.OptionDto;
import com.next.travel.hotel_service.entity.OptionEntity;
import com.next.travel.hotel_service.exception.NotFoundException;
import com.next.travel.hotel_service.repository.OptionRepo;
import com.next.travel.hotel_service.service.OptionService;
import com.next.travel.hotel_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    OptionRepo optionRepo;

    @Autowired
    Convertor convertor;


    @Override
    public OptionDto save(OptionDto optionDto) {
        return convertor.getOptionDto(optionRepo.save(convertor.getOptionEntity(optionDto)));
    }

    @Override
    public OptionDto update(OptionDto optionDto) {
        return convertor.getOptionDto(optionRepo.save(convertor.getOptionEntity(optionDto)));
    }

    @Override
    public void delete(String id) {
        if(!optionRepo.existsById(id)) throw new NotFoundException("Option Not Found!");

        OptionEntity optionEntity= optionRepo.getReferenceById(id);
        optionRepo.delete(optionEntity);
    }

    @Override
    public OptionDto searchById(String id) {
        if(!optionRepo.existsById(id)) throw new NotFoundException("Option Not Found!");
        return convertor.getOptionDto(optionRepo.getReferenceById(id));
    }

    @Override
    public List<OptionDto> getAll() {
        List<OptionEntity> all = optionRepo.findAll();
        List<OptionDto> list = new ArrayList<>();
        for (OptionEntity entity: all) {
            list.add(convertor.getOptionDto(entity));
        }
        return list;
    }

    @Override
    public String getLastId() {
        return optionRepo.getLastId();
    }
}
