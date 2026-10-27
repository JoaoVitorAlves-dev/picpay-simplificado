package com.alves.picpay.dto.response;

import com.alves.picpay.domain.TipoTransferencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaResponse(
        String id,
        String usuario,
        BigDecimal valor,
        TipoTransferencia tipoTransferencia,
        LocalDateTime dataTransferencia
) {
}
