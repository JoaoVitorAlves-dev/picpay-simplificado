package com.alves.picpay.mapper;

import com.alves.picpay.domain.Deposito;
import com.alves.picpay.domain.Usuario;
import com.alves.picpay.dto.request.DepositoTypeRequest;
import com.alves.picpay.dto.response.DepositoResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class DepositoMapper {

    public Deposito toEntity(DepositoTypeRequest req, Usuario usuario) {
        Deposito deposito = new Deposito();
        deposito.setNomeAutor(req.nomeAutor());
        deposito.setValorDeposito(req.valor());
        deposito.setData(LocalDateTime.now());
        deposito.setTipoDeposito(req.tipoDeposito());
        deposito.setUsuario(usuario);
        return deposito;
    }

    public DepositoResponse toDTO(Deposito deposito) {
        return new DepositoResponse(
                deposito.getId(), deposito.getValorDeposito(), deposito.getUsuario().getUsername(), deposito.getTipoDeposito(), deposito.getData()
        );
    }

}
