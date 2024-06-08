package com.mcb.abdulbasit.valuation.service;

import com.mcb.abdulbasit.valuation.repository.FacilityRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FacilityServiceTest {

    @InjectMocks
    FacilityService facilityService;

    @Mock
    FacilityRepository facilityRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFacilityReferenceFormatWhenNoLatestInRepository(){
        //expected format : YYYY/MM/XXXX
        var expected = "2024/06/0001";
        when(facilityRepository.findFosReferenceNo()).thenReturn(StringUtils.EMPTY);

        var actual = facilityService.createFacilityReference();

        assertEquals(expected, actual);
    }

    @Test
    void testFacilityReferenceFormatWhenLatestInRepositoryExists(){
        //expected format : YYYY/MM/XXXX
        var expected = "2024/06/0002";
        when(facilityRepository.findFosReferenceNo()).thenReturn("2024/06/0001");

        var actual = facilityService.createFacilityReference();

        assertEquals(expected, actual);
    }
}