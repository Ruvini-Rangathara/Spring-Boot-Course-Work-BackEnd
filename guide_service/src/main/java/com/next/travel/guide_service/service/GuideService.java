package com.next.travel.guide_service.service;

import com.next.travel.guide_service.dto.GuideDto;

public interface GuideService {
    GuideDto save(GuideDto guideDto);
    GuideDto update(GuideDto guideDto);
    void delete(String id);
    GuideDto searchById(String id);

}
