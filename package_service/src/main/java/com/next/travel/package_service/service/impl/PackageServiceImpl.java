package com.next.travel.package_service.service.impl;

import com.next.travel.package_service.dto.*;
import com.next.travel.package_service.entity.PackageEntity;
import com.next.travel.package_service.exception.NotFoundException;
import com.next.travel.package_service.repository.PackageRepo;
import com.next.travel.package_service.service.PackageService;
import com.next.travel.package_service.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PackageServiceImpl implements PackageService {
    @Autowired
    PackageRepo packageRepo;

    @Autowired
    Convertor convertor;

    @Override
    public PackageEntity save(PackageDto packageDto) {
        try {
            return packageRepo.save(convertor.getPackageEntity(packageDto));
        } catch (Exception e) {
            throw new NotFoundException("Not Found!");
        }
    }

    @Override
    public PackageDto update(PackageDto packageDto) {
        if (!packageRepo.existsById(packageDto.getPackageId())) throw new NotFoundException("Not Found!");

//        return convertor.getPackageDTO(packageRepo.update(convertor.getPackageEntity(packageDto)));
        return null;
    }

    @Override
    public void delete(String id) {
        if (!packageRepo.existsById(id)) throw new NotFoundException("Not Found!");
        packageRepo.deleteById(id);
    }

    @Override
    public PackageDto searchById(String id) {
        if (!packageRepo.existsById(id)) throw new NotFoundException("Not Found!");
        return convertor.getPackageDTO(packageRepo.getPackageByPackageId(id));
    }

    @Override
    public List<PackageDto> getAll() {
        Iterable<PackageEntity> all = packageRepo.findAll();
        List<PackageDto> list = new ArrayList<>();
        for (PackageEntity entity : all) {
            list.add(convertor.getPackageDTO(entity));
        }
        return list;
    }

    @Override
    public String getNewId() {

        List<PackageEntity> list = packageRepo.findLastInsertedId();
        String lastInsertedId;
        if (list.get(0) != null) {
            lastInsertedId = list.get(0).getPackageId();
            System.out.println("Last inserted document _id: " + lastInsertedId);
            String[] split = lastInsertedId.split("[P]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            return (String.format("P%04d", lastDigits));
        } else {
            System.out.println("No documents in the collection.");
            return "P0001";
        }

    }

    @Override
    public boolean existById(String id) {
        return packageRepo.existsById(id);
    }

    @Override
    public List<PackageDto> getAllByStatus(String status) {
        List<PackageEntity> all = packageRepo.findAllByStatus(status);
        List<PackageDto> list = new ArrayList<>();
        for (PackageEntity entity : all) {
            list.add(convertor.getPackageDTO(entity));
        }
        return list;
    }

    @Override
    public GuideDto getFullProfileDataOfGuide(GuideDto modifiedGuide) {
//        GuideDto guideDto1 = packageRepo.findById(guideDto.getGuideId());
//        guideDto1.setGuideId(guideDto.getGuideId());
//        modifiedGuide.setOrderValue(orderData.getOrderValue());
        return modifiedGuide;
    }

    @Override
    public VehicleDto getFullProfileDataOfVehicle(VehicleDto vehicleDto) {
        return vehicleDto;
    }

    @Override
    public HotelDto getFullProfileDataOfHotel(HotelDto hotelDto) {
        return hotelDto;
    }

}
