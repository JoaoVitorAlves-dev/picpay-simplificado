package com.alves.picpay.dto.request;

import com.alves.picpay.domain.TipoDeposito;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DepositoRequest(
        @NotNull BigDecimal valorDeposito,
        @NotNull Long usuarioId,
        @NotBlank String nomeAutor,
        @NotNull @Positive TipoDeposito tipoDeposito,
        @NotNull LocalDateTime data
) {
}
