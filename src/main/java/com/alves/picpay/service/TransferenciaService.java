package com.alves.picpay.service;

import com.alves.picpay.domain.Conta;
import com.alves.picpay.domain.Transferencia;
import com.alves.picpay.dto.request.TransferenciaRequest;
import com.alves.picpay.dto.response.TransferenciaResponse;
import com.alves.picpay.mapper.TransferenciaMapper;
import com.alves.picpay.repository.ContaRepository;
import com.alves.picpay.repository.TransferenciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, ContaRepository contaRepository) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaRepository = contaRepository;
    }

    public Page<TransferenciaResponse> listar(Pageable pageable) {
        return transferenciaRepository.findAll(pageable).map(TransferenciaMapper::toDTO);
    }

    public TransferenciaResponse transferenciar(TransferenciaRequest request, Authentication auth) {
        Conta contaTransferencia = contaRepository.findByCpf(request.cpf())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Conta contaLogada = contaRepository.findByNomeUsuario(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        if (contaLogada.getSaldo().compareTo(request.valorTransferencia()) < 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (contaLogada.getSaldo().compareTo(request.valorTransferencia()) >= 0) {
            contaLogada.setSaldo(contaLogada.getSaldo().subtract(request.valorTransferencia()));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        contaTransferencia.setSaldo(contaTransferencia.getSaldo().add(request.valorTransferencia()));
        contaLogada.setSaldo(contaTransferencia.getSaldo().subtract(request.valorTransferencia()));

        contaRepository.save(contaTransferencia);
        contaRepository.save(contaLogada);

        Transferencia transferencia = new Transferencia();
        transferencia.setNomeAutor(contaLogada.getNomeUsuario());
        transferencia.setUsuario(contaTransferencia.getUsuario());
        transferencia.setValor(request.valorTransferencia());
        transferencia.setTipoTransferencia(request.tipoTransferencia());
        transferencia.setDataTransferencia(LocalDateTime.now());
        transferencia.setNomeAutor(contaLogada.getNomeUsuario());

        return TransferenciaMapper.toDTO(transferenciaRepository.save(transferencia));
    }

}
