package com.mcb.abdulbasit.valuation.model.dto;

import com.mcb.abdulbasit.valuation.enums.Catergory;
import com.mcb.abdulbasit.valuation.enums.FacilityPurpose;
import com.mcb.abdulbasit.valuation.enums.FacilityType;
import com.mcb.abdulbasit.valuation.enums.ValuationType;
import com.mcb.abdulbasit.valuation.model.Borrower;
import com.mcb.abdulbasit.valuation.model.Comment;
import com.mcb.abdulbasit.valuation.model.File;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FacilityRequest(@NotNull String facilityType, @NotNull Integer category, @NotNull int purpose,
                              Integer facilityTerm, String ccy, Double amount, boolean housingLoan, boolean isFosRef,
                              @NotNull String valuationType, String fosReferenceNo, int userid, BorrowerRequest mainBorrower,
                              List<BorrowerRequest> jointBorrower, List<CommentRequest> comments) {
}
