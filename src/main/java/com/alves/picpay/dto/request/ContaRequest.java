package com.alves.picpay.dto.request;

import java.math.BigDecimal;

public record ContaRequest(
        String nomeUsuario,
        Long usuarioId,
        BigDecimal saldo,
        String email,
        String telefone,
        String cpf,


) {
}
