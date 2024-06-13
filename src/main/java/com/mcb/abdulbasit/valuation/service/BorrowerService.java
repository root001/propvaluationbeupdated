package com.mcb.abdulbasit.valuation.service;

import ch.qos.logback.core.pattern.color.BoldWhiteCompositeConverter;
import com.mcb.abdulbasit.valuation.model.Borrower;
import com.mcb.abdulbasit.valuation.repository.BorrowerRepository;
import com.mcb.abdulbasit.valuation.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public Borrower createBorrower(Borrower borrower){
        if(ObjectUtils.isEmpty(borrower) )
            throw new UsernameNotFoundException("no user");

        return  borrowerRepository.save(borrower);
    }
}
