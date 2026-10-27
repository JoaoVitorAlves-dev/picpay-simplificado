package com.alves.picpay.service;

import com.alves.picpay.domain.RefreshToken;
import com.alves.picpay.domain.Usuario;
import com.alves.picpay.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class TokenRefreshService {

    @Value("${jwt.expiration.in.days}")
    private long expirationInDays;

    private final RefreshTokenRepository refreshTokenRepository;

    public TokenRefreshService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public RefreshToken criar(Usuario usuario) {
        refreshTokenRepository.deleteByUsuario(usuario);

        RefreshToken rt = new RefreshToken();
        rt.setExpiraEm(Instant.now().plus(expirationInDays, ChronoUnit.DAYS));
        rt.setToken(UUID.randomUUID().toString());
        rt.setUsuario(usuario);
        return refreshTokenRepository.save(rt);
    }

    @Transactional
    public RefreshToken validarERotacionar(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (refreshToken.getExpiraEm().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token expirado.");
        }

        return criar(refreshToken.getUsuario());
    }

    @Transactional
    public void deletarPorToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(refreshTokenRepository::delete);
    }

}
