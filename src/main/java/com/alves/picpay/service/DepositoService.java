package com.alves.picpay.service;

import com.alves.picpay.domain.Deposito;
import com.alves.picpay.domain.Usuario;
import com.alves.picpay.dto.request.DepositoRequest;
import com.alves.picpay.dto.response.DepositoResponse;
import com.alves.picpay.mapper.DepositoMapper;
import com.alves.picpay.repository.DepositoRepository;
import com.alves.picpay.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DepositoService {

    private final DepositoRepository depositoRepository;
    private final UsuarioRepository usuarioRepository;

    public DepositoService(DepositoRepository depositoRepository, UsuarioRepository usuarioRepository) {
        this.depositoRepository = depositoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Page<DepositoResponse> listar(Pageable pageable) {
        return depositoRepository.findAll(pageable).map(DepositoMapper::toDTO);
    }

    public DepositoResponse criar(DepositoRequest req) {
        Usuario usuario = usuarioRepository.findById(req.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Deposito entity = DepositoMapper.toEntity(req, usuario);




    }
}
