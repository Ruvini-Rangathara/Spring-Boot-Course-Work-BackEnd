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
        return convertor.getVehicleDto(vehicleRepo.save(convertor.getVehicleEntity(vehicleDto)));
    }

    @Override
    public VehicleDto update(VehicleDto vehicleDto) {
        return convertor.getVehicleDto(vehicleRepo.save(convertor.getVehicleEntity(vehicleDto)));
    }

    @Override
    public boolean delete(String id) {
        if(!vehicleRepo.existsById(id)) throw new NotFoundException("Vehicle Not Found!");

        VehicleEntity vehicleEntity= vehicleRepo.getReferenceById(id);

        driverService.delete(vehicleEntity.getDriver().getDriverId());
        vehicleRepo.delete(vehicleEntity);
        return true;
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
    public String getNewId() {
        String lastId = vehicleRepo.getLastId();
        if (lastId == null) return "V0001";
        String[] split = lastId.split("[V]");
        int lastDigits = Integer.parseInt(split[1]);
        lastDigits++;
        return (String.format("V%04d", lastDigits));
    }

    @Override
    public boolean existById(String id) {
        return vehicleRepo.existsById(id);
    }
}
