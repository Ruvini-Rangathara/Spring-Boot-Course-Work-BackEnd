package com.next.travel.user_service.service.impl;

import com.next.travel.user_service.dto.UserPromotionDto;
import com.next.travel.user_service.entity.UserPromotionEntity;
import com.next.travel.user_service.exception.NotFoundException;
import com.next.travel.user_service.repository.UserPromotionRepo;
import com.next.travel.user_service.service.UserPromotionService;
import com.next.travel.user_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserPromotionServiceImpl implements UserPromotionService {
    @Autowired
    UserPromotionRepo userPromotionRepo;

    @Autowired
    Convertor convertor;

    @Override
    public UserPromotionDto save(UserPromotionDto userPromotionDto) {
        return convertor.getUserPromotionDto(userPromotionRepo.save(convertor.getUserPromotionEntity(userPromotionDto)));
    }

    @Override
    public UserPromotionDto update(UserPromotionDto userPromotionDto) {
        return convertor.getUserPromotionDto(userPromotionRepo.save(convertor.getUserPromotionEntity(userPromotionDto)));
    }

    @Override
    public void delete(int id) {
        if(!userPromotionRepo.existsById(id)) throw new NotFoundException("User-Promotion Not Found!");

        UserPromotionEntity userPromotionEntity= userPromotionRepo.getReferenceById(id);
        userPromotionRepo.delete(userPromotionEntity);
    }

    @Override
    public UserPromotionDto searchById(int id) {
        if(!userPromotionRepo.existsById(id)) throw new NotFoundException("User-Promotion Not Found!");
        return convertor.getUserPromotionDto(userPromotionRepo.getReferenceById(id));

    }

    @Override
    public List<UserPromotionDto> getAll() {
        List<UserPromotionEntity> all = userPromotionRepo.findAll();
        List<UserPromotionDto> list = new ArrayList<>();
        for (UserPromotionEntity entity: all) {
            list.add(convertor.getUserPromotionDto(entity));
        }
        return list;
    }

    @Override
    public Integer getLastId() {
        return userPromotionRepo.getLastId();
    }
}
