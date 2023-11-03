package com.next.travel.auth_service.repository;


import com.next.travel.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByNic(String nic);
    List<User> findAll();
}
