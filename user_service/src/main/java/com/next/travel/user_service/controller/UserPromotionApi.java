package com.next.travel.user_service.controller;


import com.next.travel.user_service.dto.PromotionDto;
import com.next.travel.user_service.dto.UserPromotionDto;
import com.next.travel.user_service.service.PromotionService;
import com.next.travel.user_service.service.UserPromotionService;
import com.next.travel.user_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-promotion")
public class UserPromotionApi {


    private final UserPromotionService userPromotionService;

    @Autowired
    public UserPromotionApi(UserPromotionService userPromotionService) {
        this.userPromotionService = userPromotionService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addUserPromotion(@RequestBody UserPromotionDto userPromotionDto) {
        userPromotionService.save(userPromotionDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", userPromotionDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findUserPromotion(@PathVariable int id) {
        UserPromotionDto userPromotionDto = userPromotionService.searchById(id);
        return new ResponseEntity<>(new StandardResponse(200, "User-Promotion was found!", userPromotionService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateUserPromotion(@RequestParam int id, @RequestBody UserPromotionDto userPromotionDto) {
        userPromotionService.update(userPromotionDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated User-Promotion Data!", userPromotionDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteUserPromotion(@PathVariable int id) {
        userPromotionService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted User-Promotion Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllUserPromotion() {
        return new ResponseEntity<>(new StandardResponse(200, "User-Promotion Data List! ", userPromotionService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/username")
    public ResponseEntity<StandardResponse> findAllUserPromotionByUsername() {

        return new ResponseEntity<>(new StandardResponse(200, "User-Promotion Data List! ", userPromotionService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getNewId() {
        int lastId = userPromotionService.getLastId();

        return new ResponseEntity<>(new StandardResponse(200, "New Promotion code! ", ++lastId), HttpStatus.OK);
    }

}
