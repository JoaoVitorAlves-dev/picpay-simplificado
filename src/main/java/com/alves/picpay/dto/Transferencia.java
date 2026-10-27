package com.alves.picpay.dto;

import com.alves.picpay.domain.TipoTransferencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transferencia(
        String id,
        String usuario,
        BigDecimal valor,
        TipoTransferencia tipoTransferencia,
        LocalDateTime dataTransferencia
) {
}
