package com.next.travel.hotel_service.service;


import com.next.travel.hotel_service.dto.OptionDto;

import java.util.List;

public interface OptionService {
    OptionDto save(OptionDto optionDto);
    OptionDto update(OptionDto optionDto);
    void delete(String id);
    OptionDto searchById(String id);
    List<OptionDto> getAll();
    String getLastId();

}
