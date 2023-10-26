package com.next.travel.user_service.repository;

import com.next.travel.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo  extends JpaRepository<UserEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM user", nativeQuery = true)
    String getLastId();

    boolean deleteUserByUsername(String username);
}
