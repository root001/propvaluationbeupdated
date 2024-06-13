package com.mcb.abdulbasit.valuation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.abdulbasit.valuation.constant.AppConstants;
import com.mcb.abdulbasit.valuation.enums.Catergory;
import com.mcb.abdulbasit.valuation.enums.FacilityPurpose;
import com.mcb.abdulbasit.valuation.enums.FacilityType;
import com.mcb.abdulbasit.valuation.enums.ValuationType;
import com.mcb.abdulbasit.valuation.model.Borrower;
import com.mcb.abdulbasit.valuation.model.Comment;
import com.mcb.abdulbasit.valuation.model.Facility;
import com.mcb.abdulbasit.valuation.model.File;
import com.mcb.abdulbasit.valuation.model.dto.*;
import com.mcb.abdulbasit.valuation.service.FacilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(AppConstants.FACILITY)
@RequiredArgsConstructor
@Slf4j
public class FacilityController {

    private final FacilityService facilityService;

    /**
     * createFacility
     *
     * @param facilityRequest
     * @return
     */
    @PostMapping(consumes = "multipart/form-data")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<FacilityResponse> createFacility(
            @RequestPart("files") MultipartFile[] files, @RequestPart FacilityRequest facilityRequest) {
        log.info("Creating new facility : {}", facilityRequest.toString());
        return ResponseEntity.ok(facilityService.createFacility(facilityRequest, files));
    }

    /**
     * findAllUsers
     *
     * @return
     */
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Facility>> findAllFacilities() {
        // Update to be passing paginated response
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }

    @GetMapping("/facilities")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<FacilitiesResponse>> findFacilities() {
        // Update to be passing paginated response
        List<FacilitiesResponse> response = new ArrayList<>();
        facilityService.getAllFacilities().forEach( facility ->{
            FacilitiesResponse facilityResponse = new FacilitiesResponse(facility.getId(), facility.getCreatedAt().toString()
                    , facility.getUser().getFullname(), facility.getFosReferenceNo(), facility.getCreatedAt().toString(),
                    facility.getUpdatedAt() != null ? facility.getUpdatedAt().toString() : "");
            response.add(facilityResponse);
        });
        return ResponseEntity.ok(response);
    }

    /**
     * findFacility
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Facility> findFacility(@PathVariable Integer id) {
        if (id < 0)
            throw new IllegalArgumentException("Illegal id provided.");
        return ResponseEntity.ok(facilityService.getFacility(id));
    }

}
