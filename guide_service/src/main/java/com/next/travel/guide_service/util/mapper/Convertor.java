package com.next.travel.guide_service.util.mapper;

import com.next.travel.guide_service.dto.GuideDto;
import com.next.travel.guide_service.entity.GuideEntity;
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


    public GuideDto getGuideDto(GuideEntity guideEntity){
        return modelMapper.map(guideEntity, GuideDto.class);
    }

    public GuideEntity getGuideEntity(GuideDto guideDto){
        return modelMapper.map(guideDto, GuideEntity.class);
    }

}
