package com.next.travel.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String gender;
    private int age;
    private String address;
    private String contactNo;
    private String nicOrPassportNo;
    private byte[] nicOrPassportFront;
    private byte[] nicOrPassportBack;
    private String remark;

}