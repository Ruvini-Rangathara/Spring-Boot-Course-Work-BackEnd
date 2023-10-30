package com.next.travel.package_service.service;


import com.next.travel.package_service.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    PromotionDto save(PromotionDto promotionDto);
    PromotionDto update(PromotionDto promotionDto);
    void delete(String id);
    PromotionDto searchById(String id);
    List<PromotionDto> getAll();
    String getLastId();
}
