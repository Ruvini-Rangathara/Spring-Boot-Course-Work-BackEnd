package com.next.travel.package_service.util;

import com.next.travel.package_service.dto.InsuranceDto;
import com.next.travel.package_service.dto.PackageDto;
import com.next.travel.package_service.entity.InsuranceEntity;
import com.next.travel.package_service.entity.PackageEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Convertor {

    private final ModelMapper modelMapper;

    Convertor(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public PackageDto getPackageDTO(PackageEntity packageEntity){
        return modelMapper.map(packageEntity, PackageDto.class);
    }
    public PackageEntity getPackageEntity(PackageDto packageDto){
        return modelMapper.map(packageDto, PackageEntity.class);
    }

    public InsuranceDto getInsuranceDTO(InsuranceEntity insuranceEntity){
        return modelMapper.map(insuranceEntity, InsuranceDto.class);
    }
    public InsuranceEntity getInsuranceEntity(InsuranceDto insuranceDto){
        return modelMapper.map(insuranceDto, InsuranceEntity.class);
    }




}
