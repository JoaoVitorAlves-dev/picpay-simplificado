package com.alves.picpay.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaRequest(
        Long usuarioId,
        BigDecimal saldo,
        String email,
        String telefone,
        String cpf,
        LocalDate dataNascimento
) {
}
