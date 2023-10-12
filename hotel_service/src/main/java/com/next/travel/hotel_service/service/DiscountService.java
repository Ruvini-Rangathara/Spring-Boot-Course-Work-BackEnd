package com.next.travel.hotel_service.service;

import com.next.travel.hotel_service.dto.DiscountDto;
import java.util.List;

public interface DiscountService {
    DiscountDto save(DiscountDto discountDto);
    DiscountDto update(DiscountDto discountDto);
    void delete(String id);
    DiscountDto searchById(String id);
    List<DiscountDto> getAll();
    String getLastId();
}
