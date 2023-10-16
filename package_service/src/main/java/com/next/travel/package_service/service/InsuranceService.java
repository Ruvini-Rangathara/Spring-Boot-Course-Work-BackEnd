package com.next.travel.package_service.service;

import com.next.travel.package_service.dto.InsuranceDto;
import com.next.travel.package_service.dto.PackageDto;

import java.util.List;

public interface InsuranceService {
    InsuranceDto save(InsuranceDto insuranceDto);
    InsuranceDto update(InsuranceDto insuranceDto);
    void delete(String id);
    InsuranceDto searchById(String id);
    List<InsuranceDto> getAll();
    String getLastId();
}
