package com.mcb.abdulbasit.valuation.controller;

import com.mcb.abdulbasit.valuation.constant.AppConstants;
import com.mcb.abdulbasit.valuation.model.dto.AuthRequest;
import com.mcb.abdulbasit.valuation.model.dto.AuthResponse;
import com.mcb.abdulbasit.valuation.service.AuthService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.AUTH)
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping(AppConstants.LOGIN_ENDPOINT)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest authRequest) {
        log.info("username {} has value, fetch params", authRequest.username());
        if(ObjectUtils.isEmpty(authRequest) || StringUtils.isEmpty(authRequest.username())
                || StringUtils.isEmpty(authRequest.password()) )
            throw new IllegalArgumentException("Illegal Credentials provided.");
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
