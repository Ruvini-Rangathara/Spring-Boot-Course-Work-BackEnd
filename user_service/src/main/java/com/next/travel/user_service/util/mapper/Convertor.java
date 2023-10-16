package com.next.travel.user_service.util.mapper;

import com.next.travel.user_service.dto.PromotionDto;
import com.next.travel.user_service.dto.UserDto;
import com.next.travel.user_service.dto.UserPromotionDto;
import com.next.travel.user_service.entity.PromotionEntity;
import com.next.travel.user_service.entity.UserEntity;
import com.next.travel.user_service.entity.UserPromotionEntity;
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


    public UserDto getUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity getUserEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }



    public PromotionDto getPromotionDto(PromotionEntity promotionEntity){
        return modelMapper.map(promotionEntity, PromotionDto.class);
    }

    public PromotionEntity getPromotionEntity(PromotionDto promotionDto){
        return modelMapper.map(promotionDto, PromotionEntity.class);
    }



    public UserPromotionDto getUserPromotionDto(UserPromotionEntity userPromotionEntity){
        return modelMapper.map(userPromotionEntity, UserPromotionDto.class);
    }

    public UserPromotionEntity getUserPromotionEntity(UserPromotionDto userPromotionDto){
        return modelMapper.map(userPromotionDto, UserPromotionEntity.class);
    }


}