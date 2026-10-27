package com.alves.picpay.dto.request;

import com.alves.picpay.domain.TipoTransferencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaRequest(
        Long usuarioId,
        BigDecimal valorTransferencia,
        String nomeAutor,
        TipoTransferencia tipoTransferencia,
        LocalDateTime dataTransferencia
) {
}
