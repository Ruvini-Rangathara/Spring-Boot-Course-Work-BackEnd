package com.next.travel.auth_service.util.mapper;


import com.next.travel.auth_service.dto.UserDTO;
import com.next.travel.auth_service.dto.util.UserRole;
import com.next.travel.auth_service.entity.User;
import com.next.travel.auth_service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;


@Component
@RequiredArgsConstructor
public class Convertor {
    private final ModelMapper modelMapper;

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        try {
            if (user.getUserRole().equalsIgnoreCase("CUSTOMER")) {
                userDTO.setProfile(decodeImage(user.getProfile()));
            } else if (user.getUserRole().equalsIgnoreCase("USER") || user.getUserRole().equalsIgnoreCase("ADMIN")) {
                userDTO.setNicFrontImage(decodeImage(user.getNicFrontImage()));
                userDTO.setNicBackImage(decodeImage(user.getNicBackImage()));
            }
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return userDTO;
    }

    public User getUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        try {
            if (userDTO.getUserRole().equals(UserRole.CUSTOMER)) {
                user.setProfile(encodeImage(userDTO.getProfile()));
            } else if (userDTO.getUserRole().equals(UserRole.USER) || userDTO.getUserRole().equals(UserRole.ADMIN)) {
                user.setNicFrontImage(encodeImage(userDTO.getNicFrontImage()));
                user.setNicBackImage(encodeImage(userDTO.getNicBackImage()));
            }
        } catch (IOException e) {
            throw new NotFoundException("Image not found");
        }
        return user;
    }

    private String encodeImage(byte[] imageByte) throws IOException {
        return Base64.getEncoder().encodeToString(imageByte);
    }

    private byte[] decodeImage(String imageString) throws IOException {
        return Base64.getDecoder().decode(imageString);
    }
}
