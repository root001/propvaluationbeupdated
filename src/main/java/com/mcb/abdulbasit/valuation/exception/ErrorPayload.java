package com.mcb.abdulbasit.valuation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorPayload {
    private String code;
    private String msg;
}
