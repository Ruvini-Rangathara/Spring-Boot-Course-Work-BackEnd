package com.next.travel.user_service.service.impl;

import com.next.travel.user_service.dto.UserDto;
import com.next.travel.user_service.entity.UserEntity;
import com.next.travel.user_service.payload.exceptions.NotFoundException;
import com.next.travel.user_service.repository.UserRepo;
import com.next.travel.user_service.service.UserService;
import com.next.travel.user_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    Convertor convertor;

    @Override
    public boolean save(UserDto userDto) {

        convertor.getUserDto(userRepo.save(convertor.getUserEntity(userDto)));
        return true;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return convertor.getUserDto(userRepo.save(convertor.getUserEntity(userDto)));
    }

    @Override
    public boolean delete(String id) {
        if (!userRepo.existsById(id)) throw new NotFoundException("User Not Found!");

        UserEntity userEntity = userRepo.getReferenceById(id);
        return userRepo.deleteUserByUsername(userEntity.getUsername());
    }

    @Override
    public UserDto searchById(String id) {
        if (!userRepo.existsById(id)) throw new NotFoundException("User Not Found!");
        return convertor.getUserDto(userRepo.getReferenceById(id));

    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> all = userRepo.findAll();
        List<UserDto> list = new ArrayList<>();
        for (UserEntity entity : all) {
            list.add(convertor.getUserDto(entity));
        }
        return list;
    }


    @Override
    public boolean existById(String id) {
        return userRepo.existsById(id);
    }
}
