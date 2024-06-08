package com.mcb.abdulbasit.valuation.model.dto;

import java.time.LocalDateTime;

public record AuthResponse(String token, LocalDateTime expiresAt, Integer id, String username) {
}
