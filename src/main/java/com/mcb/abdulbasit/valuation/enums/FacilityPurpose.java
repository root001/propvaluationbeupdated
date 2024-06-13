package com.mcb.abdulbasit.valuation.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public enum FacilityPurpose {
    REPARATION(1),
    INHERITANCE(2),
    CONSTRUCTION(3);

    private final int value;

    FacilityPurpose(int value) {
        this.value = value;
    }

    public static FacilityPurpose fromValue(int value) {
        for (FacilityPurpose facilityPurpose : FacilityPurpose.values()) {
            if (facilityPurpose.value == value) {
                return facilityPurpose;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
