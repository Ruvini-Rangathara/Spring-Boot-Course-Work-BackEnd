package com.next.travel.user_service.controller;


import com.next.travel.user_service.dto.PromotionDto;
import com.next.travel.user_service.dto.UserDto;
import com.next.travel.user_service.exception.InvalidException;
import com.next.travel.user_service.service.UserService;
import com.next.travel.user_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addUser(@RequestBody UserDto userDto) {
        validateUserData(userDto);
        userService.save(userDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", userDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findUser(@PathVariable String id) {
        UserDto userDto = userService.searchById(id);
        return new ResponseEntity<>(new StandardResponse(200, "User was found!", userService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateUser(@RequestParam String id, @RequestBody UserDto userDto) {
        validateUserData(userDto);
        userService.update(userDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated User Data!", userDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteUser(@PathVariable String id) {
        userService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted User Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllUser() {
        return new ResponseEntity<>(new StandardResponse(200, "User Data List! ", userService.getAll()), HttpStatus.OK);
    }

    private void validateUserData(UserDto userDto) throws RuntimeException {
        if (!Pattern.compile("^(?=.*\\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$").matcher(userDto.getPassword()).matches()) {
            throw new InvalidException("Invalid password!");
        }
        else if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n").matcher(userDto.getEmail()).matches()) {
            throw new InvalidException("Invalid email type!");
        }
        else if (!Pattern.compile("^\\d{7}[0-9Vv]|^\\d{9}$").matcher(userDto.getNicOrPassportNo()).matches()) {
            throw new InvalidException("Invalid NIC No type!");
        }
        else if (!Pattern.compile("^[A-Z]{1,2}\\d{7}$").matcher(userDto.getNicOrPassportNo()).matches()) {
            throw new InvalidException("Invalid Passport No type!");
        }
    }

}
