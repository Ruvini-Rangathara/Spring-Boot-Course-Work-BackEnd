package com.next.travel.package_service.service.impl;

import com.next.travel.package_service.dto.InsuranceDto;
import com.next.travel.package_service.dto.PackageDto;
import com.next.travel.package_service.entity.InsuranceEntity;
import com.next.travel.package_service.entity.PackageEntity;
import com.next.travel.package_service.exception.NotFoundException;
import com.next.travel.package_service.repository.InsuranceRepo;
import com.next.travel.package_service.repository.PackageRepo;
import com.next.travel.package_service.service.InsuranceService;
import com.next.travel.package_service.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    InsuranceRepo insuranceRepo;

    @Autowired
    Convertor convertor;

    @Override
    public InsuranceDto save(InsuranceDto insuranceDto) {
        return convertor.getInsuranceDTO(insuranceRepo.save(convertor.getInsuranceEntity(insuranceDto)));
    }

    @Override
    public InsuranceDto update(InsuranceDto insuranceDto) {
        if (!insuranceRepo.existsById(insuranceDto.getId())) throw new NotFoundException("Not Found!");
        return null;
    }

    @Override
    public void delete(String id) {
        if (!insuranceRepo.existsById(id)) throw new NotFoundException("Not Found!");
        insuranceRepo.deleteById(id);
    }

    @Override
    public InsuranceDto searchById(String id) {
        if (!insuranceRepo.existsById(id)) throw new NotFoundException("Not Found!");
        return convertor.getInsuranceDTO(insuranceRepo.getInsuranceById(id));
    }

    @Override
    public List<InsuranceDto> getAll() {
        Iterable<InsuranceEntity> all = insuranceRepo.findAll();
        List<InsuranceDto> list = new ArrayList<>();
        for (InsuranceEntity entity : all) {
            list.add(convertor.getInsuranceDTO(entity));
        }
        return list;
    }

    @Override
    public String getLastId() {
        return null;
    }
}
