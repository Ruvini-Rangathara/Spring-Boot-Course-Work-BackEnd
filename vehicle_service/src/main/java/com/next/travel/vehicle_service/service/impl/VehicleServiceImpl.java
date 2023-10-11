package com.next.travel.vehicle_service.service.impl;

import com.next.travel.guide_service.dto.GuideDto;
import com.next.travel.guide_service.entity.GuideEntity;
import com.next.travel.guide_service.exception.NotFoundException;
import com.next.travel.guide_service.repository.GuideRepo;
import com.next.travel.guide_service.service.GuideService;
import com.next.travel.guide_service.util.mapper.Convertor;
import com.next.travel.vehicle_service.dto.VehicleDto;
import com.next.travel.vehicle_service.entity.VehicleEntity;
import com.next.travel.vehicle_service.exception.NotFoundException;
import com.next.travel.vehicle_service.repository.VehicleRepo;
import com.next.travel.vehicle_service.service.VehicleService;
import com.next.travel.vehicle_service.util.mapper.Convertor;
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
    public void delete(String id) {
        if(!vehicleRepo.existsById(id)) throw new NotFoundException("Guide Not Found!");
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setVehicleId(id);
        vehicleRepo.delete(vehicleEntity);
    }

    @Override
    public VehicleDto searchById(String id) {
        if(!vehicleRepo.existsById(id)) throw new NotFoundException("Guide Not Found!");
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
}
