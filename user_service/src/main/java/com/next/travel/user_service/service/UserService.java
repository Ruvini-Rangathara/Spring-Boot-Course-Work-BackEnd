package com.next.travel.user_service.service;

import com.next.travel.user_service.dto.UserDto;

import java.util.List;

public interface UserService {
    boolean save(UserDto userDto);
    UserDto update(UserDto userDto);
    boolean delete(String id);
    UserDto searchById(String id);
    List<UserDto> getAll();
    String getLastId();
    boolean existById(String id);
}
