package com.alves.picpay.mapper;

import com.alves.picpay.domain.Deposito;
import com.alves.picpay.domain.TipoDeposito;
import com.alves.picpay.domain.Usuario;
import com.alves.picpay.dto.request.DepositoRequest;
import com.alves.picpay.dto.response.DepositoResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class DepositoMapper {

    public Deposito toEntity(DepositoRequest req, TipoDeposito tipoDeposito, Usuario usuario) {
        Deposito deposito = new Deposito();
        deposito.setNomeAutor(req.nomeAutor());
        deposito.setValorDeposito(req.valorDeposito());
        deposito.setData(LocalDateTime.now());
        deposito.setTipoDeposito(tipoDeposito);
        deposito.setUsuario(usuario);
        return deposito;
    }

    public DepositoResponse toDTO(Deposito deposito) {
        return new DepositoResponse(
                deposito.getId(), deposito.getValorDeposito(), deposito.getUsuario().getUsername(), deposito.getTipoDeposito(), deposito.getData()
        );
    }

}
