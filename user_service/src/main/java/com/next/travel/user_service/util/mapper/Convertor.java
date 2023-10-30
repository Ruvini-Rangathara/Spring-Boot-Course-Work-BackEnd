package com.next.travel.user_service.util.mapper;

import com.next.travel.user_service.dto.UserDto;
import com.next.travel.user_service.entity.UserEntity;
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

}