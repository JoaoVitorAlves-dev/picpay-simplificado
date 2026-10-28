package com.alves.picpay.mapper;

import com.alves.picpay.domain.Conta;
import com.alves.picpay.domain.Usuario;
import com.alves.picpay.dto.request.ContaRequest;
import com.alves.picpay.dto.response.ContaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContaMapper {

    public Conta toEntity(ContaRequest req, Usuario usuario) {
        Conta conta = new Conta();
        conta.setEmail(req.email());
        conta.setCpf(req.cpf());
        conta.setDataNascimento(req.dataNascimento());
        conta.setSaldo(req.saldo());
        conta.setUsuario(usuario);
        return conta;
    }

    public ContaResponse toDTO(Conta conta) {
        return new ContaResponse(
                conta.getUsuario().getUsername(), conta.getSaldo(), conta.getEmail(),
                conta.getCpf(), conta.getTelefone(), conta.getDataNascimento()
        );
    }

}
