package com.mcb.abdulbasit.valuation.controller;

import com.mcb.abdulbasit.valuation.constant.AppConstants;
import com.mcb.abdulbasit.valuation.model.Facility;
import com.mcb.abdulbasit.valuation.model.dto.FacilityRequest;
import com.mcb.abdulbasit.valuation.model.dto.FacilityResponse;
import com.mcb.abdulbasit.valuation.service.FacilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(AppConstants.FACILITY)
@RequiredArgsConstructor
@Slf4j
public class FacilityController {

    private final FacilityService facilityService;

    /**
     * createFacility
     * @param facilityRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<FacilityResponse> createFacility(@RequestBody @Valid FacilityRequest facilityRequest,
                                                           @RequestParam("file") MultipartFile[] files){
        log.info("Creating new facility : {}", facilityRequest.toString());
        return ResponseEntity.ok(facilityService.createFacility(facilityRequest, files));
    }

    /**
     * findAllUsers
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Facility>> findAllFacilities() {
        // Update to be passing paginated response
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }

    /**
     * findFacility
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Facility> findFacility(@PathVariable Integer id) {
        if(id < 0)
            throw new IllegalArgumentException("Illegal id provided.");
        return ResponseEntity.ok(facilityService.getFacility(id) );
    }
}
