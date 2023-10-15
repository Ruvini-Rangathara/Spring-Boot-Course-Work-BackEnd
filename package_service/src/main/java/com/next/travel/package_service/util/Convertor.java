package com.next.travel.package_service.util;

import com.next.travel.package_service.dto.PackageDto;
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

    public PackageVehicleDto getPackageVehicleDTO(PackageVehicleEntity packageVehicleEntity){
        return modelMapper.map(packageVehicleEntity, PackageVehicleDto.class);
    }
    public PackageVehicleEntity getPackageVehicleEntity(PackageVehicleDto packageVehicleDto){
        return modelMapper.map(packageVehicleDto, PackageVehicleEntity.class);
    }

    public PackageRoomDto getPackageRoomDTO(PackageRoomEntity packageRoomEntity){
        return modelMapper.map(packageRoomEntity, PackageRoomDto.class);
    }
    public PackageRoomEntity getPackageRoomEntity(PackageRoomDto packageRoomDto){
        return modelMapper.map(packageRoomDto, PackageRoomEntity.class);
    }


}
