package com.next.travel.guide_service.service;

import com.next.travel.guide_service.dto.GuideDto;

import java.util.List;

public interface GuideService {
    GuideDto save(GuideDto guideDto);
    GuideDto update(GuideDto guideDto);
    void delete(String id);
    GuideDto searchById(String id);
    List<GuideDto> getAll();
    String getLastId();
}
