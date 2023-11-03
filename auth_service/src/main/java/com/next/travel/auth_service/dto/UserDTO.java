package com.next.travel.auth_service.dto;

import com.next.travel.auth_service.dto.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String userId;
    private String name;
    private String nic;
    private String email;
    private String address;
    private String username;
    private String password;
    private UserRole userRole;
    private String phone;

    // customer
    private byte[] profile;
    // user
    private byte[] nicFrontImage;
    private byte[] nicBackImage;

}
