package dev.pedro.foodflow_api.config;

import lombok.Builder;

@Builder
public record JwtUserData(Long userId, String email) {
}
