package com.next.travel.auth_service.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements UserDetails {

    @Id
    private String userId;
    private String name;
    private String nic;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String nicFrontImage;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String nicBackImage;
    private String email;
    private String phone;
    private String address;
    private String username;
    private String userRole;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String profile;

    @Column(columnDefinition = "LONGTEXT")
    private String password;

    public User(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define the authorities (roles) for the user here if needed.
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
