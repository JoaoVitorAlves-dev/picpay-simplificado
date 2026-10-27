package com.alves.picpay.service;

import com.alves.picpay.domain.RefreshToken;
import com.alves.picpay.domain.RoleUsuario;
import com.alves.picpay.domain.Usuario;
import com.alves.picpay.dto.request.LoginRequest;
import com.alves.picpay.dto.request.RefreshTokenRequest;
import com.alves.picpay.dto.request.RegisterRequest;
import com.alves.picpay.dto.response.RefreshTokenResponse;
import com.alves.picpay.repository.RefreshTokenRepository;
import com.alves.picpay.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRefreshService tokenRefreshService;
    private final TokenService tokenService;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenRefreshService tokenRefreshService, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenRefreshService = tokenRefreshService;
        this.tokenService = tokenService;
    }

    public RefreshTokenResponse refresh(RefreshTokenRequest req) {
        RefreshToken refreshToken = tokenRefreshService.validarERotacionar(req.accessToken());

        String token = tokenService.gerarToken(refreshToken.getUsuario().getUsername());

        return new RefreshTokenResponse(token, refreshToken.getToken());
    }

    public void register(RegisterRequest req) {
        if (usuarioRepository.existsByUsername(req.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username já existe.");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(req.username());
        usuario.setPassword(passwordEncoder.encode(req.password()));
        usuario.setRoleUsuario(RoleUsuario.COMUM);
        usuarioRepository.save(usuario);
    }

    public RefreshTokenResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        Usuario usuario = usuarioRepository.findByUsername(loginRequest.username()).orElseThrow();

        String token = tokenService.gerarToken(usuario.getUsername());
        String refresh = tokenRefreshService.validarERotacionar(token).getToken();

        return new RefreshTokenResponse(token, refresh);
    }

    public void apagarPorToken(String token) {
        tokenRefreshService.deletarPorToken(token);
    }

}
