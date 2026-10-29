package com.alves.picpay.service;

import com.alves.picpay.domain.Conta;
import com.alves.picpay.domain.Deposito;
import com.alves.picpay.dto.request.DepositoTypeRequest;
import com.alves.picpay.dto.response.DepositoResponse;
import com.alves.picpay.mapper.DepositoMapper;
import com.alves.picpay.repository.ContaRepository;
import com.alves.picpay.repository.DepositoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DepositoService {

    private final DepositoRepository depositoRepository;
    private final ContaRepository contaRepository;

    public DepositoService(DepositoRepository depositoRepository, ContaRepository contaRepository) {
        this.depositoRepository = depositoRepository;
        this.contaRepository = contaRepository;
    }

    public Page<DepositoResponse> listar(Pageable pageable) {
        return depositoRepository.findAll(pageable).map(DepositoMapper::toDTO);
    }

    public DepositoResponse depositar(DepositoTypeRequest deposito, Authentication auth) {
        Conta contaDeposito = contaRepository.findByCpf(deposito.cpf())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Conta contaLogada = contaRepository.findByNomeUsuario(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        if (contaLogada.getSaldo().compareTo(deposito.valor()) < 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (contaLogada.getSaldo().compareTo(deposito.valor()) >= 0) {
            contaLogada.setSaldo(contaLogada.getSaldo().subtract(deposito.valor()));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Deposito deposito1 = new Deposito();
        deposito1.setUsuario(contaDeposito.getUsuario());
        deposito1.setTipoDeposito(deposito.tipoDeposito());
        deposito1.setValorDeposito(deposito.valor());
        deposito1.setNomeAutor(contaLogada.getNomeUsuario());
        deposito1.setData(LocalDateTime.now());

        contaDeposito.setSaldo(contaDeposito.getSaldo().add(deposito.valor()));
        contaRepository.save(contaDeposito);
        contaRepository.save(contaLogada);

        return DepositoMapper.toDTO(depositoRepository.save(deposito1));
    }

}
