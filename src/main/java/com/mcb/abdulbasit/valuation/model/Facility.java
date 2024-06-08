package com.mcb.abdulbasit.valuation.model;

import com.mcb.abdulbasit.valuation.entity.BaseEntity;
import com.mcb.abdulbasit.valuation.enums.Catergory;
import com.mcb.abdulbasit.valuation.enums.FacilityPurpose;
import com.mcb.abdulbasit.valuation.enums.FacilityType;
import com.mcb.abdulbasit.valuation.enums.ValuationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Facility extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private FacilityType facilityType;

    @Enumerated(EnumType.STRING)
    private Catergory catergory;

    @Enumerated(EnumType.STRING)
    private FacilityPurpose purpose;
    
    private Integer facilityTerm;
    
    private String ccy;
    
    private Double amount;
    private boolean isHousingLoan;
    private boolean isFosRef;

    @Enumerated(EnumType.STRING)
    private ValuationType valuationType;
    private String fosReferenceNo;
    @OneToOne
    @JoinColumn(name = "mborrower_id")
    private Borrower mainBorrower;
    @OneToMany
    @JoinColumn(name = "jborrower_id")
    private List<Borrower> jointBorrower;
    @OneToMany
    @JoinColumn(name = "file_id")
    private List<File> uploadedFiles;
    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

}
