package com.next.travel.package_service.service.impl;

import com.next.travel.package_service.dto.PromotionDto;
import com.next.travel.package_service.entity.PromotionEntity;
import com.next.travel.package_service.exception.NotFoundException;
import com.next.travel.package_service.repository.PromotionRepo;
import com.next.travel.package_service.service.PromotionService;
import com.next.travel.package_service.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionRepo promotionRepo;

    @Autowired
    Convertor convertor;

    @Override
    public PromotionDto save(PromotionDto promotionDto) {
        return convertor.getPromotionDto(promotionRepo.save(convertor.getPromotionEntity(promotionDto)));
    }

    @Override
    public PromotionDto update(PromotionDto promotionDto) {
        return convertor.getPromotionDto(promotionRepo.save(convertor.getPromotionEntity(promotionDto)));
    }

    @Override
    public void delete(String id) {
        if(!promotionRepo.existsById(id)) throw new NotFoundException("Promotion Not Found!");

//        PromotionEntity promotionEntity= promotionRepo.getReferenceById(id);
//        promotionRepo.delete(promotionEntity);
    }

    @Override
    public PromotionDto searchById(String id) {
        if(!promotionRepo.existsById(id)) throw new NotFoundException("Promotion Not Found!");
//        return convertor.getPromotionDto(promotionRepo.getReferenceById(id));
        return null;

    }

    @Override
    public List<PromotionDto> getAll() {
//        List<PromotionEntity> all = promotionRepo.findAll();
//        List<PromotionDto> list = new ArrayList<>();
//        for (PromotionEntity entity: all) {
//            list.add(convertor.getPromotionDto(entity));
//        }
        return null;
    }

    @Override
    public String getLastId() {
        return promotionRepo.getLastId();
    }
}
