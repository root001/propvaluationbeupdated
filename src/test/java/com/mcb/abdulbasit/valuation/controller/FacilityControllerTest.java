package com.mcb.abdulbasit.valuation.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.abdulbasit.valuation.config.AppTestConfig;
import com.mcb.abdulbasit.valuation.exception.ResponseExceptionHandler;
import com.mcb.abdulbasit.valuation.model.Facility;
import com.mcb.abdulbasit.valuation.model.Users;
import com.mcb.abdulbasit.valuation.service.FacilityService;
import com.mcb.abdulbasit.valuation.service.UserService;
import com.mcb.abdulbasit.valuation.util.EasyRandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {FacilityController.class})
@AutoConfigureMockMvc
@ContextConfiguration(classes = {AppTestConfig.class, ResponseExceptionHandler.class})
class FacilityControllerTest {

    @MockBean
    private FacilityService facilityService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private final static Integer someid = 4;

    @BeforeEach
    void setUp() {
        ((MockServletContext) mockMvc.getDispatcherServlet().getServletContext()).setContextPath("/api");
    }

    @Test
    @WithMockUser(username = "test_user", password = "admin")
    void given_that_facility_exist_return_all_facilities() throws Exception {
        List<Facility> facilityList = EasyRandomUtils.mock(Facility.class, 3);

        when(facilityService.getAllFacilities()).thenReturn(facilityList);
        final var result = mockMvc.perform(get("/api/v1/facility")
                        .contextPath("/api")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        final var responseList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Facility>>() {
        });

        Assertions.assertThat(responseList.size()).isEqualTo(facilityList.size());
    }

}