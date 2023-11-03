package com.next.travel.auth_service.service.impl;

import com.next.travel.auth_service.dto.UserDTO;
import com.next.travel.auth_service.entity.User;
import com.next.travel.auth_service.exception.DuplicateException;
import com.next.travel.auth_service.exception.NotFoundException;
import com.next.travel.auth_service.repository.UserDao;
import com.next.travel.auth_service.service.AuthService;
import com.next.travel.auth_service.service.JwtService;
import com.next.travel.auth_service.util.mapper.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final Convertor convertor;

//    @Value("${booking-service-endpoint}")
//    private String bookingDataEndPoint;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDao.findByNic(userDTO.getNic()).isPresent())
            throw new DuplicateException("Nic duplicated");
        if (userDao.findByUsername(userDTO.getUsername()).isPresent())
            throw new DuplicateException("userName duplicated");

        String userId;
        do {
            userId = String.format("U%S", UUID.randomUUID());
        } while (userDao.findById(userId).isPresent());
        userDTO.setUserId(userId);

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return convertor.getUserDTO(userDao.save(convertor.getUser(userDTO)));
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userDao.findAll().stream().map(convertor::getUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return convertor.getUserDTO(userDao.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public UserDTO getSelectedUserByUserName(String userName) {
        return convertor.getUserDTO(userDao.findByUsername(userName).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userDao.findById(userDTO.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        Optional<User> optionalUserForNic = userDao.findByNic(userDTO.getNic());
        if (optionalUserForNic.isPresent() && !optionalUserForNic.get().getUserId().equals(userDTO.getUserId()))
            throw new DuplicateException("Duplicate user nic");
        Optional<User> optionalUserForUserName = userDao.findByUsername(userDTO.getUsername());
        if (optionalUserForUserName.isPresent() && !optionalUserForUserName.get().getUserId().equals(userDTO.getUserId()))
            throw new DuplicateException("Duplicate userName");
        userDao.save(convertor.getUser(userDTO));
    }

    @Override
    public void deleteUser(String userId) {
//        User user = userDao.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
//        String userRole = user.getUserRole();
//        if (userRole != null && userRole.equals("CUSTOMER")) {
//            RestTemplate restTemplate = new RestTemplate();
//            List<UserBookingDTO> dtos = restTemplate.getForObject(bookingDataEndPoint + "/" + userId, List.class);
//            assert dtos != null;
//            if (!dtos.isEmpty())
//                throw new InUseException("User have booking");
//        }
//        userDao.deleteById(userId);
    }

}
