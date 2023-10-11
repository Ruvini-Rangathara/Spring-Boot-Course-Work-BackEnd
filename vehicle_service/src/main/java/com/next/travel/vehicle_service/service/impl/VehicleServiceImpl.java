package com.next.travel.vehicle_service.service.impl;

import com.next.travel.vehicle_service.dto.VehicleDto;
import com.next.travel.vehicle_service.entity.DriverEntity;
import com.next.travel.vehicle_service.entity.VehicleEntity;
import com.next.travel.vehicle_service.exception.NotFoundException;
import com.next.travel.vehicle_service.repository.DriverRepo;
import com.next.travel.vehicle_service.repository.VehicleRepo;
import com.next.travel.vehicle_service.service.DriverService;
import com.next.travel.vehicle_service.service.VehicleService;
import com.next.travel.vehicle_service.util.mapper.Convertor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    DriverService driverService;

    @Autowired
    Convertor convertor;


    @Override
    public VehicleDto save(VehicleDto vehicleDto) {
        driverService.save(vehicleDto.getDriverDto());
        return convertor.getVehicleDto(vehicleRepo.save(convertor.getVehicleEntity(vehicleDto)));
    }

    @Override
    public VehicleDto update(VehicleDto vehicleDto) {
        driverService.save(vehicleDto.getDriverDto());
        return convertor.getVehicleDto(vehicleRepo.save(convertor.getVehicleEntity(vehicleDto)));
    }

    @Override
    public void delete(String id) {
        if(!vehicleRepo.existsById(id)) throw new NotFoundException("Vehicle Not Found!");

        VehicleEntity vehicleEntity= vehicleRepo.getReferenceById(id);

        driverService.delete(vehicleEntity.getDriver().getDriverId());
        vehicleRepo.delete(vehicleEntity);
    }

    @Override
    public VehicleDto searchById(String id) {
        if(!vehicleRepo.existsById(id)) throw new NotFoundException("Vehicle Not Found!");
        return convertor.getVehicleDto(vehicleRepo.getReferenceById(id));
    }

    @Override
    public List<VehicleDto> getAll() {
        List<VehicleEntity> all = vehicleRepo.findAll();
        List<VehicleDto> list = new ArrayList<>();
        for (VehicleEntity entity: all) {
            list.add(convertor.getVehicleDto(entity));
        }
        return list;
    }

    @Override
    public String getLastId() {
        return vehicleRepo.getLastId();
    }
}
