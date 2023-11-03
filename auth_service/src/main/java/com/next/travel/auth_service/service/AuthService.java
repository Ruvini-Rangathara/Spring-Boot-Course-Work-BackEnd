package com.next.travel.auth_service.service;


import com.next.travel.auth_service.dto.UserDTO;

import java.util.List;

public interface AuthService {
    UserDTO saveUser(UserDTO userDTO);

    String generateToken(String userName);

    void validateToken(String token);

    List<UserDTO> getAllUser();

    UserDTO getSelectedUser(String userId);

    UserDTO getSelectedUserByUserName(String userName);

    void updateUser(UserDTO userDTO);

    void deleteUser(String userId);
}
