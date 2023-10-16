package com.next.travel.user_service.service;

import com.next.travel.user_service.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    PromotionDto save(PromotionDto promotionDto);
    PromotionDto update(PromotionDto promotionDto);
    void delete(String id);
    PromotionDto searchById(String id);
    List<PromotionDto> getAll();
    String getLastId();
}
