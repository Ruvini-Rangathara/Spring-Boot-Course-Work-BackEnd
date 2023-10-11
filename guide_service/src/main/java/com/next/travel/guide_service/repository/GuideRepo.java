package com.next.travel.guide_service.repository;

import com.next.travel.guide_service.entity.GuideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepo extends JpaRepository<GuideEntity, String> {
}
