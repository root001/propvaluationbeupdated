package com.mcb.abdulbasit.valuation.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum Catergory {
    APARTMENT("Apartment",25000),
    PBWMHOUSING("PBWM Housing",25010);

    private String name;
    private Integer id;
}
