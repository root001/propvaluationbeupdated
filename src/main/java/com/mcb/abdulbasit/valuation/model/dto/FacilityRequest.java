package com.mcb.abdulbasit.valuation.model.dto;

import com.mcb.abdulbasit.valuation.enums.Catergory;
import com.mcb.abdulbasit.valuation.enums.FacilityPurpose;
import com.mcb.abdulbasit.valuation.enums.FacilityType;
import com.mcb.abdulbasit.valuation.enums.ValuationType;
import com.mcb.abdulbasit.valuation.model.Borrower;
import com.mcb.abdulbasit.valuation.model.Comment;
import com.mcb.abdulbasit.valuation.model.File;

import java.util.List;

public record FacilityRequest(FacilityType facilityType, Catergory catergory, FacilityPurpose purpose,
                              Integer facilityTerm, String ccy, Double amount, boolean isHousingLoan, boolean isFosRef,
                              ValuationType valuationType, String fosReferenceNo, Borrower mainBorrower,
                              List<Borrower> jointBorrower, List<File> uploadedFiles, List<Comment> comments) {
}
