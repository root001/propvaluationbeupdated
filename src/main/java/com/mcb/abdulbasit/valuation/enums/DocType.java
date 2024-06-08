package com.mcb.abdulbasit.valuation.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum DocType {
    TITLE("Title Deed"),
    NIC("National Identity Card"),
    PERMIT("Building Permits"),
    BIRTH("Birth Certificate"),
    MARRIAGE("Marriage Certificate"),
    QUOTE("Quotation"),
    LETTERINTENT("Letter of Intent");

    private String name;
}
