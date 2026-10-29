package com.alves.picpay.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DepositoTypeRequest(
        @NotBlank String cpf
) {
}
