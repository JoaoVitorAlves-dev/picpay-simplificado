package com.alves.picpay.dto.request;

import com.alves.picpay.domain.TipoDeposito;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositoTypeRequest(
        @NotBlank String nomeAutor,
        @NotBlank String cpf,
        @NotNull TipoDeposito tipoDeposito,
        @NotNull @Positive BigDecimal valor
) {
}
