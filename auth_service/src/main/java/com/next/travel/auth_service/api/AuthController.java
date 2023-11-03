package com.next.travel.auth_service.api;

import com.next.travel.auth_service.dto.AuthRequestDTO;
import com.next.travel.auth_service.dto.UserDTO;
import com.next.travel.auth_service.dto.util.UserRole;
import com.next.travel.auth_service.exception.InvalidException;
import com.next.travel.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("{userId:^U\\d{3,}$}")
    ResponseEntity<?> getSelectedUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getSelectedUser(userId));
    }

    @GetMapping("{userName:^[a-z]{5,15}$}")
    ResponseEntity<?> getSelectedUserByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(userService.getSelectedUserByUserName(userName));
    }

    @GetMapping
    ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody AuthRequestDTO authRequestDTO) {
        System.out.println(authRequestDTO);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authenticate.isAuthenticated()) {
            return ResponseEntity.ok(userService.generateToken(authRequestDTO.getUsername()));
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestPart UserDTO user,
            @RequestPart(required = false) byte[] profile,//customer
            @RequestPart(required = false) byte[] nicFrontImage,//admin
            @RequestPart(required = false) byte[] nicBackImage
    ) {
        validateUser(user);

        user.setNicFrontImage(nicFrontImage);
        user.setNicBackImage(nicBackImage);
        user.setProfile(profile);

        return ResponseEntity.ok(userService.saveUser(user));
    }


    void validateUser(UserDTO user){
        if (user.getUserRole() == null)
            throw new InvalidException("InValid role");
        if (user.getName() == null || !Pattern.matches("^[a-zA-Z.+=@\\-_\\s]{3,50}$", user.getName()))
            throw new InvalidException("InValid name");
        if (user.getNic() == null || !Pattern.matches("^[0-9]{9}[vVxX]||[0-9]{12}$", user.getNic()))
            throw new InvalidException("InValid nic");
        if (user.getEmail() == null || !Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", user.getEmail()))
            throw new InvalidException("InValid email");
        if (user.getAddress() == null)
            throw new InvalidException("InValid address");
        if (user.getUsername() == null || !Pattern.matches("^[a-z]{5,15}$", user.getUsername()))
            throw new InvalidException("InValid userName, use only simple letter for username");
        if (user.getPassword() == null || !Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", user.getPassword()))
            throw new InvalidException("InValid password");
    }

    @PutMapping(value = "{userId:^U\\d{3,}$}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> updateUser(
            @RequestPart UserDTO user,
            @RequestPart(required = false) byte[] profile,//customer

            @RequestPart(required = false) byte[] nicFrontImage,//admins
            @RequestPart(required = false) byte[] nicBackImage,//admins
            @RequestPart(required = false) String phone//admins
    ) {

        validateUser(user);

        user.setNicFrontImage(nicFrontImage);
        user.setNicBackImage(nicBackImage);
        user.setProfile(profile);

        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("{userId:^U\\d{3,}$}")
    ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted");
    }
}
