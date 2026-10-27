package com.alves.picpay.dto;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
