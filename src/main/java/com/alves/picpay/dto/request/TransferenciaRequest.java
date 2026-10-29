package com.alves.picpay.dto.request;

import com.alves.picpay.domain.TipoTransferencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaRequest(
        @NotBlank String cpf,
        @NotNull @Positive BigDecimal valorTransferencia,
        @NotNull TipoTransferencia tipoTransferencia
) {
}
