package com.mcb.abdulbasit.valuation.service;

import com.mcb.abdulbasit.valuation.enums.*;
import com.mcb.abdulbasit.valuation.exception.EntityNotFoundException;
import com.mcb.abdulbasit.valuation.model.Borrower;
import com.mcb.abdulbasit.valuation.model.Comment;
import com.mcb.abdulbasit.valuation.model.Facility;
import com.mcb.abdulbasit.valuation.model.dto.FacilityRequest;
import com.mcb.abdulbasit.valuation.model.dto.FacilityResponse;
import com.mcb.abdulbasit.valuation.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository facilityRepository;

    private final BorrowerService borrowerService;
    private final FileService fileService;
    private final CommentService commentService;
    private final UserService userService;

    /**
     * createFacility
     *
     * @param facilityRequest
     * @return FacilityResponse
     */
    public FacilityResponse createFacility(FacilityRequest facilityRequest, MultipartFile[] files) {
        if (ObjectUtils.isEmpty(facilityRequest)) {
            throw new IllegalArgumentException("Illegal facility object passed");
        }
        String[] docType = {DocType.NIC.name()};
        Borrower mainBorrower = Borrower.builder()
                .customerName(facilityRequest.mainBorrower().customerName())
                .customerNumber(facilityRequest.mainBorrower().customerNumber())
                .email(facilityRequest.mainBorrower().email())
                .contactNumber(facilityRequest.mainBorrower().contactNumber())
                .address(facilityRequest.mainBorrower().address())
                .build();
        List<Borrower> jointBorrowers = new ArrayList<>();
        facilityRequest.jointBorrower().forEach(borrower -> {
            Borrower jointBorrower = Borrower.builder()
                    .customerName(borrower.customerName())
                    .customerNumber(borrower.customerNumber())
                    .email(borrower.email())
                    .contactNumber(borrower.contactNumber())
                    .address(borrower.address())
                    .build();
            jointBorrowers.add(borrowerService.createBorrower(jointBorrower));
        });

        List<Comment> facilityComments = new ArrayList<>();
        facilityRequest.comments().forEach(comment -> {
            facilityComments.add(commentService.createComment(Comment.builder().body(comment.body()).build()));
        });

        Facility facility = Facility.builder()
                .facilityType(FacilityType.valueOf(facilityRequest.facilityType().toUpperCase()))
                .facilityTerm(facilityRequest.facilityTerm())
                .ccy(facilityRequest.ccy())
                .amount(facilityRequest.amount())
                .catergory(Catergory.fromId(facilityRequest.category()))
                .fosReferenceNo(createFacilityReference())
                .valuationType(ValuationType.valueOf(facilityRequest.valuationType().toUpperCase()))
                .purpose(FacilityPurpose.fromValue(facilityRequest.purpose()))
                .isHousingLoan(facilityRequest.housingLoan())
                .isFosRef(facilityRequest.isFosRef())
                .mainBorrower(borrowerService.createBorrower(mainBorrower))
                .comments(facilityComments)
                .jointBorrower(jointBorrowers)
                .uploadedFiles(fileService.fileUpload(files, docType))
                .user(userService.getUser(facilityRequest.userid()))
                .build();
        //map request model to facility object, then persist.
        Facility createdFac = facilityRepository.save(facility);
        if (ObjectUtils.isNotEmpty(createdFac)) {
            return new FacilityResponse("Facility creation successful", createdFac.getId());
        }
        return new FacilityResponse("Facility creation failed", null);
    }

    /**
     * getFacility
     *
     * @param id
     * @return Facility
     */
    public Facility getFacility(Integer id) {
        if (id > 0)
            throw new IllegalArgumentException("facility id cannot be null or empty.");
        Optional<Facility> facility = facilityRepository.findById(id);
        if (!facility.isPresent()) {
            // return no user custom error msg
            throw new EntityNotFoundException("Facility not found");
        }
        return facility.get();
    }

    /**
     * getAllFacilities
     *
     * @return List<Facility>
     */
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public String createFacilityReference() {
        int currYear = LocalDate.now().getYear();
        int currMonth = LocalDate.now().getMonthValue();

        DecimalFormat mFormat = new DecimalFormat("00");
        String monthStr = mFormat.format(Double.valueOf(currMonth));

        String lastFacilityReference = facilityRepository.findFosReferenceNo();

        if (StringUtils.isNotEmpty(lastFacilityReference)) {
            String lastFacilitySeq = lastFacilityReference.substring(lastFacilityReference.lastIndexOf("/") + 1);

            int nextFacilityRefSeq = Integer.parseInt(lastFacilitySeq) + 1;

            String facilityRefBuilder = currYear + "/" + monthStr + "/";

            return facilityRefBuilder + String.format("%04d", nextFacilityRefSeq);
        }

        return currYear + "/" + monthStr + "/0001";
    }
}
