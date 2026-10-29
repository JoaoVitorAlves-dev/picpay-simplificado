package com.alves.picpay.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank String username,
        @NotBlank @Size(min = 6) String password,
        @NotNull @PositiveOrZero BigDecimal saldo,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank String cpf,
        @NotNull LocalDate dataNascimento
) {
}
