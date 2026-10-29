package com.alves.picpay.dto.request;

import com.alves.picpay.domain.TipoDeposito;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepositoTypeRequest(
        @NotBlank String cpf,
        @NotNull TipoDeposito tipoDeposito
) {
}
