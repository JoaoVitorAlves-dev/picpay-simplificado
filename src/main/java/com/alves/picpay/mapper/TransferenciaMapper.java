package com.alves.picpay.mapper;

import com.alves.picpay.domain.Transferencia;
import com.alves.picpay.domain.Usuario;
import com.alves.picpay.dto.request.TransferenciaRequest;
import com.alves.picpay.dto.response.TransferenciaResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class TransferenciaMapper {

    public Transferencia toEntity(TransferenciaRequest req, Usuario usuario) {
        Transferencia transferencia = new Transferencia();
        transferencia.setNomeAutor(req.nomeAutor());
        transferencia.setDataTransferencia(LocalDateTime.now());
        transferencia.setTipoTransferencia(req.tipoTransferencia());
        transferencia.setValor(req.valorTransferencia());
        transferencia.setUsuario(usuario);
        return transferencia;
    }

    public TransferenciaResponse toDTO(Transferencia transferencia) {
        return new TransferenciaResponse(
                transferencia.getId(), transferencia.getUsuario().getUsername(), transferencia.getValor() ,transferencia.getTipoTransferencia(), transferencia.getDataTransferencia()
        );
    }

}
