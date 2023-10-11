package com.next.travel.vehicle_service.util.mapper;


import com.next.travel.vehicle_service.dto.DriverDto;
import com.next.travel.vehicle_service.dto.VehicleDto;
import com.next.travel.vehicle_service.entity.DriverEntity;
import com.next.travel.vehicle_service.entity.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Convertor {
    @Autowired
    private final ModelMapper modelMapper;

    public Convertor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public VehicleDto getVehicleDto(VehicleEntity vehicleEntity){
        return modelMapper.map(vehicleEntity, VehicleDto.class);
    }

    public VehicleEntity getVehicleEntity(VehicleDto vehicleDto){
        return modelMapper.map(vehicleDto, VehicleEntity.class);
    }

    public DriverDto getDriverDto(DriverEntity driverEntity){
        return modelMapper.map(driverEntity, DriverDto.class);
    }

    public DriverEntity getDriverEntity(DriverDto driverDto){
        return modelMapper.map(driverDto, DriverEntity.class);
    }

}
