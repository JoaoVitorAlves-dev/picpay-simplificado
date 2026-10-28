package com.alves.picpay.controller;

import com.alves.picpay.dto.request.LoginRequest;
import com.alves.picpay.dto.request.RefreshTokenRequest;
import com.alves.picpay.dto.request.RegisterRequest;
import com.alves.picpay.dto.response.ContaResponse;
import com.alves.picpay.dto.response.RefreshTokenResponse;
import com.alves.picpay.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta/auth")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ContaResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(contaService.listar(pageable));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest req) {
        return ResponseEntity.ok(contaService.refresh(req));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest req) {
        contaService.criar(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<RefreshTokenResponse> login(@RequestBody @Valid LoginRequest req) {
        return ResponseEntity.ok(contaService.login(req));
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid LoginRequest req, Authentication auth) {
        contaService.atualizar(id, req, auth);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/apagarPorToken")
    public ResponseEntity<Void> apagarPorToken(@RequestBody @Valid RefreshTokenRequest req) {
        contaService.apagarPorToken(req);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
