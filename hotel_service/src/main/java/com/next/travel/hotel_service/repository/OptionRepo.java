package com.next.travel.hotel_service.repository;

import com.next.travel.hotel_service.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OptionRepo extends JpaRepository<OptionEntity, String> {
    @Query(value = "SELECT LAST_INSERT_ID() AS last_id FROM option", nativeQuery = true)
    String getLastId();
}
