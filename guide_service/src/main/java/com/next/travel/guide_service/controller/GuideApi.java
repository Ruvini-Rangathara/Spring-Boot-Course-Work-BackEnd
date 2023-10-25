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
@CrossOrigin(origins = "*")
public class GuideApi {

    private final GuideService guideService;

    @Autowired
    public GuideApi(GuideService guideService) {
        this.guideService = guideService;
    }


    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart("nic_front") byte[] nic_front, @RequestPart("nic_back") byte[] nic_back, @RequestPart("guide_id_front") byte[] guide_id_front, @RequestPart("guide_id_back") byte[] guide_id_back, @RequestPart("profile") byte[] guide_img, @RequestPart("guide") GuideDto guide) {

        System.out.println("GuideController -> " + guide);

        guide.setPhoto(guide_img);
        guide.setNicFrontImage(nic_front);
        guide.setNicBackImage(nic_back);
        guide.setGuidIdFrontImage(guide_id_front);
        guide.setGuideIdBackImage(guide_id_back);
        System.out.println("GuideController -> " + guide);
        try {
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
        if (!Pattern.compile("^G\\d{3,}$").matcher(guide.getGuideId()).matches())
            throw new RuntimeException("Invalid driver id");
        if (!Pattern.compile("^[a-zA-Z '-]+$").matcher(guide.getName()).matches())
            throw new RuntimeException("Invalid driver name");
        if (!Pattern.compile("^\\d{10}$").matcher(guide.getContactNo()).matches())
            throw new RuntimeException("Invalid driver contact number");
        if (!Pattern.compile("^(Male|Female)$").matcher(guide.getGender()).matches())
            throw new RuntimeException("Invalid gender type");
        if (!Pattern.compile("^[a-zA-Z0-9\\s]+$").matcher(guide.getAddress()).matches())
            throw new RuntimeException("Invalid address");
        try {
            if (!Pattern.compile("^\\d+$").matcher(String.valueOf(guide.getAge())).matches())
                throw new RuntimeException("Invalid seat capacity!");
            if (!Pattern.compile("^\\d+(\\.\\d+)?$").matcher(String.valueOf(guide.getManDayValue())).matches())
                throw new RuntimeException("invalid price per day!");
        } catch (NumberFormatException e) {
            throw new RuntimeException("invalid price per day!");
        }

        if (guide.getPhoto() == null || guide.getNicFrontImage() == null || guide.getNicBackImage() == null || guide.getGuidIdFrontImage() == null || guide.getGuideIdBackImage() == null)
            throw new RuntimeException("Invalid or empty image found in the list.");


    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getOngoingGuideID() {
        System.out.println("get last id");
        String guideId = guideService.getLastId();
        return ResponseEntity.ok(guideId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        System.out.println("Guide Controller -> getAll");
        List<GuideDto> allGuides = guideService.getAll();
        System.out.println(allGuides.size());
        if (allGuides.isEmpty()) return ResponseEntity.ok().body("");
        System.out.println("done");
        return ResponseEntity.ok().body(allGuides);

    }

    @PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> patch(@RequestPart("nic_front") byte[] nic_front, @RequestPart("nic_back") byte[] nic_back, @RequestPart("guide_id_front") byte[] guide_id_front, @RequestPart("guide_id_back") byte[] guide_id_back, @RequestPart("profile") byte[] guide_img, @RequestPart("guide") GuideDto guide) {
        guide.setPhoto(guide_img);
        guide.setNicFrontImage(nic_front);
        guide.setNicBackImage(nic_back);
        guide.setGuidIdFrontImage(guide_id_front);
        guide.setGuideIdBackImage(guide_id_back);
        try {
            validateGuideDetails(guide);
            System.out.println("validated");
            if (guideService.existById(guide.getGuideId())) {
                guideService.save(guide);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Guide not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
        System.out.println("VehicleController -> getVehicleByVehicleID: " + id);
        boolean isExists = guideService.existById(id);
        if (!isExists) return ResponseEntity.badRequest().body("Guide not found !");
        GuideDto guide = guideService.searchById(id);
        return ResponseEntity.ok(guide);
    }
}
