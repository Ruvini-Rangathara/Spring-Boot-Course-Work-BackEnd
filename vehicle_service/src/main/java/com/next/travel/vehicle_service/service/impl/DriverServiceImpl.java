package com.next.travel.vehicle_service.service.impl;

import com.next.travel.vehicle_service.dto.DriverDto;
import com.next.travel.vehicle_service.dto.VehicleDto;
import com.next.travel.vehicle_service.entity.DriverEntity;
import com.next.travel.vehicle_service.entity.VehicleEntity;
import com.next.travel.vehicle_service.exception.NotFoundException;
import com.next.travel.vehicle_service.repository.DriverRepo;
import com.next.travel.vehicle_service.service.DriverService;
import com.next.travel.vehicle_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverRepo driverRepo;

    @Autowired
    Convertor convertor;
    @Override
    public DriverDto save(DriverDto driverDto) {
        return convertor.getDriverDto(driverRepo.save(convertor.getDriverEntity(driverDto)));
    }

    @Override
    public DriverDto update(DriverDto driverDto) {
        return convertor.getDriverDto(driverRepo.save(convertor.getDriverEntity(driverDto)));

    }

    @Override
    public void delete(String id) {
        if(!driverRepo.existsById(id)) throw new NotFoundException("Driver Not Found!");
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setDriverId(id);
        driverRepo.delete(driverEntity);
    }

    @Override
    public DriverDto searchById(String id) {
        if(!driverRepo.existsById(id)) throw new NotFoundException("Driver Not Found!");
        return convertor.getDriverDto(driverRepo.getReferenceById(id));
    }

    @Override
    public List<DriverDto> getAll() {
        List<DriverEntity> all = driverRepo.findAll();
        List<DriverDto> list = new ArrayList<>();
        for (DriverEntity entity: all) {
            list.add(convertor.getDriverDto(entity));
        }
        return list;
    }
}
