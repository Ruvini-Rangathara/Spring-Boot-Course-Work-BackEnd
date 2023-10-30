package com.next.travel.user_service.controller;

import com.next.travel.user_service.dto.UserDto;
import com.next.travel.user_service.payload.exceptions.UserValidationException;
import com.next.travel.user_service.payload.responses.MessageResponse;
import com.next.travel.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserApi {

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@RequestPart("nic_front") byte[] nic_front,
                                      @RequestPart("nic_back") byte[] nic_back,
                                      @RequestPart("user") UserDto userDto) {
        System.out.println("register");
        try {
            userDto.setRole("ROLE_USER");
            validateUserdata(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), null));
        }
        Boolean existsUserByUsername = userService.existById(userDto.getUsername());

        userDto.setNicOrPassportFront(nic_front);
        userDto.setNicOrPassportBack(nic_back);
        if (existsUserByUsername)
            return ResponseEntity.badRequest().body(new MessageResponse("Username already exists", null));
        return userService.save(userDto) ? ResponseEntity.ok().body(new MessageResponse("User registration successful", null)) : ResponseEntity.badRequest().body(new MessageResponse("User registration failed", null));
    }

    private void validateUserdata(UserDto userDTO) throws RuntimeException {
        if (!Pattern.compile("^([a-zA-Z]+( [a-zA-Z]+)*)$").matcher(userDTO.getUsername()).matches()) {
            System.out.println("invalid username");
            throw new UserValidationException("Invalid userDTO name type!");
        } else if (!(Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$").matcher(userDTO.getNicOrPassportNo()).matches() | Pattern.compile("^(\\d{4})(\\d{3})(\\d{4})(\\d{1})$").matcher(userDTO.getNicOrPassportNo()).matches())) {
            System.out.println("invalid nic pattern");
            throw new UserValidationException("invalid nic pattern");
        } else if (!(Pattern.compile("^\\d+$").matcher(String.valueOf(userDTO.getAge())).matches() && userDTO.getAge() > 0)) {
            System.out.println("invalid age");
            throw new UserValidationException("invalid age");
        } else if (!(userDTO.getGender().equalsIgnoreCase("male") || userDTO.getGender().equalsIgnoreCase("female"))) {
            System.out.println("invalid gender");
            throw new UserValidationException("invalid gender");
        } else if (!Pattern.compile("^([a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,})$").matcher(userDTO.getEmail()).matches()) {
            System.out.println("invalid email");
            throw new UserValidationException("Invalid email address!");
        } else if (!Pattern.compile("^\\d{10}$").matcher(userDTO.getContactNo()).matches()) {
            System.out.println("invalid contact number");
            throw new UserValidationException("Invalid contact number!");
        } else if (userDTO.getPassword() == null) {
            System.out.println("invalid password");
            throw new UserValidationException("Password is null");
        } else if (userDTO.getRole() == null) {
            System.out.println("invalid role");
            throw new UserValidationException("invalid role");
        }
    }

    @GetMapping("/check/")
    public ResponseEntity<?> checkUsername(@RequestHeader String username) {
        boolean existsUserByUsername = userService.existById(username);
        if (!existsUserByUsername) return ResponseEntity.ok(true);
        return ResponseEntity.badRequest().body(new MessageResponse("Username already exists", null));
    }


    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserByUsername(@RequestHeader("username") String username) {
        if (!Pattern.compile("^U\\d{3,}$").matcher(username).matches())
            throw new UserValidationException("Invalid username type!");
        boolean existsUserByUsername = userService.existById(username);
        if (existsUserByUsername) {
            return userService.delete(username) ? ResponseEntity.ok().body(new MessageResponse("User deleted successfully", null)) : ResponseEntity.ok().body(new MessageResponse("User deletion failed", null));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("User not found", null));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUserById(@RequestHeader String username) {
        System.out.println("user controller -> get user by username: " + username);
        boolean isExists = userService.existById(username);
        if (!isExists) return ResponseEntity.badRequest().body("User not found !");
        UserDto userDto = userService.searchById(username);
        return ResponseEntity.ok(userDto);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUser(@RequestPart("nic_front") byte[] nic_front,
                                        @RequestPart("nic_back") byte[] nic_back,
                                        @RequestPart("user") UserDto userDto) {

        userDto.setNicOrPassportFront(nic_front);
        userDto.setNicOrPassportBack(nic_back);

        try {
            validateUserdata(userDto);
            System.out.println("validated in backend");
            if (userService.existById(userDto.getUsername())) {
                userService.save(userDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("User not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        System.out.println("User Controller -> getAll");
        List<UserDto> allUsers = userService.getAll();
        System.out.println(allUsers.size());
        if (allUsers.isEmpty()) return ResponseEntity.ok().body("");
        System.out.println("done");
        return ResponseEntity.ok().body(allUsers);

    }


}
