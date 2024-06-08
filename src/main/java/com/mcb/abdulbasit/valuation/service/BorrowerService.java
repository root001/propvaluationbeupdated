package com.mcb.abdulbasit.valuation.service;

import ch.qos.logback.core.pattern.color.BoldWhiteCompositeConverter;
import com.mcb.abdulbasit.valuation.model.Borrower;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BorrowerService {

    public Borrower createBorrower(Borrower borrower){
        if(ObjectUtils.isEmpty(borrower) )
            throw new UsernameNotFoundException("no user");
        return borrower;
    }
}
