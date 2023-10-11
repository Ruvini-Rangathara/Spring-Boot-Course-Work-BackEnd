package com.next.travel.guide_service.service.impl;

import com.next.travel.guide_service.dto.GuideDto;
import com.next.travel.guide_service.entity.GuideEntity;
import com.next.travel.guide_service.repository.GuideRepo;
import com.next.travel.guide_service.service.GuideService;
import com.next.travel.guide_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GuideServiceImpl implements GuideService {
    @Autowired
    GuideRepo guideRepo;

    @Autowired
    Convertor convertor;


    @Override
    public GuideDto save(GuideDto guideDto) {
        return convertor.getGuideDto(guideRepo.save(convertor.getGuideEntity(guideDto)));
    }

    @Override
    public GuideDto update(GuideDto guideDto) {
        return convertor.getGuideDto(guideRepo.save(convertor.getGuideEntity(guideDto)));
    }

    @Override
    public void delete(String id) {
        GuideEntity guideEntity = new GuideEntity();
        guideEntity.setGuideId(id);
        guideRepo.delete(guideEntity);
    }

    @Override
    public GuideDto searchById(String id) {
        return convertor.getGuideDto(guideRepo.getReferenceById(id));
    }
}
