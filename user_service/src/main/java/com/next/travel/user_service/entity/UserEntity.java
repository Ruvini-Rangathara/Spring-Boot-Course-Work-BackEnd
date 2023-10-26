package com.next.travel.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "user")
public class UserEntity {
    @Id
    private String username;
    private String password;
    private String email;
    private String gender;
    private int age;
    private String address;
    private String contactNo;
    private String role;
    private String nicOrPassportNo;
    private byte[] nicOrPassportFront;
    private byte[] nicOrPassportBack;
    private String remark;


    @OneToMany(mappedBy = "user")
    private List<UserPromotionEntity> userPromotions = new ArrayList<>();
}
