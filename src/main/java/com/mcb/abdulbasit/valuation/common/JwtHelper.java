package com.mcb.abdulbasit.valuation.common;

import com.mcb.abdulbasit.valuation.model.Users;

import java.time.LocalDateTime;

public interface JwtHelper {
    String extractUserNameFromToken(String token);

    String generateTokenForUser(Users userDetails);

    boolean authenticateByToken(String token, Users userDetails);

    LocalDateTime getTokenExpiration(String token);
}
