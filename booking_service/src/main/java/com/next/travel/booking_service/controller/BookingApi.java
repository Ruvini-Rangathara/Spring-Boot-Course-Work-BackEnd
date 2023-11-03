package com.next.travel.booking_service.controller;

import com.next.travel.booking_service.dto.BookingDto;
import com.next.travel.booking_service.exception.InvalidException;
import com.next.travel.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/api/v1/package")
@CrossOrigin("*")
public class BookingApi {

    private final BookingService packageService;


    @Autowired
    public BookingApi(BookingService packageService) {
        this.packageService = packageService;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addBooking(@RequestPart ("package") BookingDto bookingDto,
                                        @RequestPart("slip1") byte[] slip1,
                                        @RequestPart("slip2") byte[] slip2) {

        packageService.save(bookingDto);

        try {
            validatePackageData(bookingDto);
            System.out.println("validated in backend");
            if (packageService.existById(bookingDto.getPackageId())) {
                packageService.save(bookingDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Package not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> findBooking(@RequestHeader String id) {
        System.out.println("package controller -> get package : " + id);
        boolean isExists = packageService.existById(id);
        if (!isExists) return ResponseEntity.badRequest().body("Package not found !");
        BookingDto bookingDto = packageService.searchById(id);
        return ResponseEntity.ok(bookingDto);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBooking(@RequestPart BookingDto bookingDto) {
        validatePackageData(bookingDto);
        packageService.update(bookingDto);

        try {
            validatePackageData(bookingDto);
            System.out.println("validated in backend");
            if (packageService.existById(bookingDto.getPackageId())) {
                packageService.save(bookingDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Package not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBooking(@RequestHeader("id") String id) {
        System.out.println("Package Controller -> delete Package");
        if (!Pattern.compile("^P\\d{3,}$").matcher(id).matches())
            return ResponseEntity.badRequest().body("Invalid package id");
        try {
            packageService.delete(id);
            return ResponseEntity.ok().body("Package deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        System.out.println("Package Controller -> getAll");
        List<BookingDto> allPackages = packageService.getAll();
        System.out.println(allPackages.size());
        if (allPackages.isEmpty()) return ResponseEntity.ok().body("");
        System.out.println("done");
        return ResponseEntity.ok().body(allPackages);

    }



    @GetMapping("/check/")
    public ResponseEntity<?> existsByPackageId(@RequestHeader String id) {
        boolean isExists = packageService.existById(id);
        System.out.println("isExists = " + isExists);
        if (isExists) return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(false);
    }


    private void validatePackageData(BookingDto bookingDto) throws RuntimeException {
        if (!Pattern.compile("^P\\d{3,}$").matcher(bookingDto.getPackageId()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^\\d+\\.\\d{2}$\n").matcher(String.valueOf(bookingDto.getPackageValue())).matches()) {
            throw new InvalidException("Invalid package value!");

        } else if (!Pattern.compile("^\\d+\\.\\d{2}$\n").matcher(String.valueOf(bookingDto.getPaidValue())).matches()) {
            throw new InvalidException("Invalid paid value!");

        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(bookingDto.getNoOfNights())).matches()) {
            throw new InvalidException("Invalid No Of Nights!");

        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(bookingDto.getNoOfDays())).matches()) {
            throw new InvalidException("Invalid No Of Days!");
        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(bookingDto.getHeadCount())).matches()) {
            throw new InvalidException("Invalid head count!");
        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(bookingDto.getNoOfChildren())).matches()) {
            throw new InvalidException("Invalid no Of Children!");
        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(bookingDto.getNoOfAdults())).matches()) {
            throw new InvalidException("Invalid No Of Adults!");
        }

    }


}
