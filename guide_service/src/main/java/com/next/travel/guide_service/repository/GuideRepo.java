package com.next.travel.guide_service.repository;

import com.next.travel.guide_service.entity.GuideEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuideRepo extends JpaRepository<GuideEntity, String> {

    @Query("SELECT MAX (g.guideId) FROM guide g")
    String getLastId();
}
