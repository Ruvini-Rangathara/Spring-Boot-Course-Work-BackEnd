package com.next.travel.guide_service.service.impl;

import com.next.travel.guide_service.dto.GuideDto;
import com.next.travel.guide_service.entity.GuideEntity;
import com.next.travel.guide_service.exception.NotFoundException;
import com.next.travel.guide_service.repository.GuideRepo;
import com.next.travel.guide_service.service.GuideService;
import com.next.travel.guide_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        if(!guideRepo.existsById(id)) throw new NotFoundException("Guide Not Found!");
        GuideEntity guideEntity = new GuideEntity();
        guideEntity.setGuideId(id);
        guideRepo.delete(guideEntity);
    }

    @Override
    public GuideDto searchById(String id) {
        if(!guideRepo.existsById(id)) throw new NotFoundException("Guide Not Found!");
        return convertor.getGuideDto(guideRepo.getReferenceById(id));
    }

    @Override
    public List<GuideDto> getAll() {
        List<GuideEntity> all = guideRepo.findAll();
        List<GuideDto> list = new ArrayList<>();
        for (GuideEntity entity: all) {
            list.add(convertor.getGuideDto(entity));
        }
        return list;
    }

    @Override
    public String getNewId() {

        String lastId = guideRepo.getLastId();
        if (lastId == null) return "G0001";
        String[] split = lastId.split("[G]");
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("G%04d", lastDigits));
    }

    @Override
    public boolean existById(String id) {
        return guideRepo.existsById(id);
    }
}
