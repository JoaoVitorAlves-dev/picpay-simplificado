package com.alves.picpay.dto.response;

import com.alves.picpay.domain.TipoDeposito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DepositoResponse(
        BigDecimal valorDeposito,
        String usuario,
        TipoDeposito tipoDeposito,
        LocalDateTime data
) {
}
