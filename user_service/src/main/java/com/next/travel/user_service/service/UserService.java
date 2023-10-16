package com.next.travel.user_service.service;

import com.next.travel.user_service.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(String id);
    UserDto searchById(String id);
    List<UserDto> getAll();
    String getLastId();
}
