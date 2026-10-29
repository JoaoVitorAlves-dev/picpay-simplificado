package com.alves.picpay.controller;

import com.alves.picpay.dto.request.TransferenciaRequest;
import com.alves.picpay.dto.response.TransferenciaResponse;
import com.alves.picpay.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<TransferenciaResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(transferenciaService.listar(pageable));
    }

    @PostMapping("/transferir")
    public ResponseEntity<TransferenciaResponse> transferir(@RequestBody @Valid TransferenciaRequest request, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaService.transferenciar(request, auth));
    }

}
