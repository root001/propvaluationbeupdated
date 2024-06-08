package com.mcb.abdulbasit.valuation.model;

import com.mcb.abdulbasit.valuation.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Borrower extends BaseEntity {
    @NonNull
    private String customerNumber;
    @NonNull
    private String customerName;
    private String contactNumber;
    @NonNull
    private String email;
    private String address;

}
