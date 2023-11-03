package com.next.travel.guide_service.controller;

import com.next.travel.guide_service.dto.GuideDto;
import com.next.travel.guide_service.exception.InvalidException;
import com.next.travel.guide_service.service.GuideService;
import com.next.travel.guide_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/guide")
@CrossOrigin(origins = "http://localhost:63342")
public class GuideApi {

    private final GuideService guideService;

    @Autowired
    public GuideApi(GuideService guideService) {
        this.guideService = guideService;
    }


    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart("nic_front") byte[] nic_front,
                                  @RequestPart("nic_back") byte[] nic_back,
                                  @RequestPart("guide_id_front") byte[] guide_id_front,
                                  @RequestPart("guide_id_back") byte[] guide_id_back,
                                  @RequestPart("profile") byte[] guide_img,
                                  @RequestPart("guide") GuideDto guide) {

        System.out.println("GuideController -> " + guide.toString());

        guide.setPhoto(guide_img);
        guide.setNicFrontImage(nic_front);
        guide.setNicBackImage(nic_back);
        guide.setGuidIdFrontImage(guide_id_front);
        guide.setGuideIdBackImage(guide_id_back);


        try {
            System.out.println("in back end try before validate");
            validateGuideDetails(guide);
            System.out.println("validated");
            if (guideService.existById(guide.getGuideId())) {

                System.out.println("exists");
                return ResponseEntity.badRequest().body("Guide already exists!");
            }
            guideService.save(guide);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private void validateGuideDetails(GuideDto guide) {
        if (!Pattern.compile("^G\\d{3,}$").matcher(guide.getGuideId()).matches()){
            throw new RuntimeException("Invalid driver id");
        }else {
            System.out.println("valid id");
        }

        if (!Pattern.compile("^[A-Za-z.]+$").matcher(guide.getName()).matches()){
            throw new RuntimeException("Invalid driver name");
        }else {
            System.out.println("valid name");
        }

        if (!Pattern.compile("^-?\\d+$").matcher(guide.getContactNo()).matches()){
            throw new RuntimeException("Invalid driver contact number");
        }else{
            System.out.println("valid contact");
        }

        if (!Pattern.compile("^(Male|Female)$").matcher(guide.getGender()).matches()){
            throw new RuntimeException("Invalid gender type");
        }else{
            System.out.println("valid gender");
        }

        if (!Pattern.compile("^[a-zA-Z0-9\\s]+$").matcher(guide.getAddress()).matches()){
            throw new RuntimeException("Invalid address");
        }else{
            System.out.println("valid address");
        }
        try {
            if (!Pattern.compile("^\\d+$").matcher(String.valueOf(guide.getAge())).matches()){
                throw new RuntimeException("Invalid age");
            }else{
                System.out.println("valid age");
            }
            if (!Pattern.compile("^\\d+(\\.\\d+)?$").matcher(String.valueOf(guide.getManDayValue())).matches()){
                throw new RuntimeException("invalid man day value!");
            }else{
                System.out.println("valid man day value");
            }

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

//        if (guide.getPhoto() == null || guide.getNicFrontImage() == null || guide.getNicBackImage() == null || guide.getGuidIdFrontImage() == null || guide.getGuideIdBackImage() == null)
//            throw new RuntimeException("Invalid or empty image found in the list.");


    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getNewGuideID() {
        String guideId = guideService.getNewId();
        System.out.println("get new id :  "+guideId);

        return ResponseEntity.ok(guideId);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/getAll")
    public ResponseEntity<List<GuideDto>> getAll() {

        System.out.println("Guide Controller -> getAll");
        List<GuideDto> allGuides = guideService.getAll();

        System.out.println(allGuides.size());

        for (GuideDto guideDto : allGuides) {
            System.out.println(guideDto.getGuideId());
        }
        if (allGuides.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return a 204 No Content response
        }

        return ResponseEntity.ok().body(allGuides);

    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateGuide(@RequestPart("nic_front") byte[] nic_front,
                                   @RequestPart("nic_back") byte[] nic_back,
                                   @RequestPart("guide_id_front") byte[] guide_id_front,
                                   @RequestPart("guide_id_back") byte[] guide_id_back,
                                   @RequestPart("profile") byte[] guide_img,
                                   @RequestPart("guide") GuideDto guide) {
        guide.setPhoto(guide_img);
        guide.setNicFrontImage(nic_front);
        guide.setNicBackImage(nic_back);
        guide.setGuidIdFrontImage(guide_id_front);
        guide.setGuideIdBackImage(guide_id_back);
        try {
            validateGuideDetails(guide);
            System.out.println("validated in backend");
            if (guideService.existById(guide.getGuideId())) {
                guideService.save(guide);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Guide not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGuide(@RequestHeader("id") String id) {
        System.out.println("GuideController -> deleteGuide");
        if (!Pattern.compile("^G\\d{3,}$").matcher(id).matches())
            return ResponseEntity.badRequest().body("Invalid guide id");
        try {
            guideService.delete(id);
            return ResponseEntity.ok().body("Guide deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getGuideById(@RequestHeader String id) {
        System.out.println("guide controller -> get guide by guide id: " + id);
        boolean isExists = guideService.existById(id);
        if (!isExists) return ResponseEntity.badRequest().body("Guide not found !");
        GuideDto guide = guideService.searchById(id);
        System.out.println("Contact No of "+guide.getGuideId()+" : "+guide.getContactNo());
        return ResponseEntity.ok(guide);
    }

    @GetMapping("/check/")
    public ResponseEntity<?> existsByGuideId(@RequestHeader String guide_id) {
        boolean isExists = guideService.existById(guide_id);
        if (isExists) return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(false);
    }

}



