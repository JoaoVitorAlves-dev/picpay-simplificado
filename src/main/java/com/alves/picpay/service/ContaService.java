package com.alves.picpay.service;

import com.alves.picpay.domain.Conta;
import com.alves.picpay.domain.RefreshToken;
import com.alves.picpay.domain.RoleUsuario;
import com.alves.picpay.domain.Usuario;
import com.alves.picpay.dto.request.ContaRequest;
import com.alves.picpay.dto.request.LoginRequest;
import com.alves.picpay.dto.request.RefreshTokenRequest;
import com.alves.picpay.dto.request.RegisterRequest;
import com.alves.picpay.dto.response.ContaResponse;
import com.alves.picpay.dto.response.RefreshTokenResponse;
import com.alves.picpay.mapper.ContaMapper;
import com.alves.picpay.repository.ContaRepository;
import com.alves.picpay.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRefreshService tokenRefreshService;
    private final TokenService tokenService;

    public ContaService(ContaRepository contaRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenRefreshService tokenRefreshService, TokenService tokenService) {
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenRefreshService = tokenRefreshService;
        this.tokenService = tokenService;
    }

    public Page<ContaResponse> listar(Pageable pageable) {
        return contaRepository.findAll(pageable).map(ContaMapper::toDTO);
    }

    public RefreshTokenResponse refresh(RefreshTokenRequest req) {
        RefreshToken refreshToken = tokenRefreshService.validarERotacionar(req.accessToken());

        String token = tokenService.gerarToken(refreshToken.getUsuario().getUsername());

        return new RefreshTokenResponse(token, refreshToken.getToken());
    }

    public void criar(RegisterRequest registerRequest, ContaRequest req) {
        if (usuarioRepository.existsById(req.usuarioId()) || usuarioRepository.existsByUsername(registerRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registerRequest.username());
        usuario.setPassword(passwordEncoder.encode(registerRequest.password()));
        usuario.setRoleUsuario(RoleUsuario.COMUM);
        usuarioRepository.save(usuario);
        Conta entity = ContaMapper.toEntity(req, usuario);
        contaRepository.save(entity);
    }

    public RefreshTokenResponse login(LoginRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));

        Usuario usuario = usuarioRepository.findByUsername(req.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String accessToken = tokenService.gerarToken(usuario.getUsername());
        String refresh = tokenRefreshService.validarERotacionar(accessToken).getToken();
        
        return new RefreshTokenResponse(accessToken, refresh);
    }

}
