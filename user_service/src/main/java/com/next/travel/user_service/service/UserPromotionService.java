package com.next.travel.user_service.service;

import com.next.travel.user_service.dto.UserPromotionDto;

import java.util.List;

public interface UserPromotionService {
    UserPromotionDto save(UserPromotionDto userPromotionDto);
    UserPromotionDto update(UserPromotionDto userPromotionDto);
    void delete(int id);
    UserPromotionDto searchById(int id);
    List<UserPromotionDto> getAll();
    Integer getLastId();
}
