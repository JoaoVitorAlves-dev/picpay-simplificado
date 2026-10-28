package com.alves.picpay.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaResponse(
        String username,
        BigDecimal saldo,
        String email,
        String cpf,
        String telefone,
        LocalDate dataNascimento
) {
}
