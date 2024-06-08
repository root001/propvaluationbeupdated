package com.mcb.abdulbasit.valuation.service;

import ch.qos.logback.core.pattern.color.BoldWhiteCompositeConverter;
import com.mcb.abdulbasit.valuation.model.Borrower;
import org.springframework.stereotype.Service;

@Service
public class BorrowerService {

    public Borrower createBorrower(Borrower borrower){
        return borrower;
    }
}
