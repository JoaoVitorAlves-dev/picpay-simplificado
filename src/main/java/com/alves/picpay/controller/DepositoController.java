package com.alves.picpay.controller;

import com.alves.picpay.dto.request.DepositoTypeRequest;
import com.alves.picpay.dto.response.DepositoResponse;
import com.alves.picpay.service.DepositoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    private final DepositoService depositoService;

    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DepositoResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(depositoService.listar(pageable));
    }

    @PostMapping("/depositar")
    public ResponseEntity<DepositoResponse> depositar(@RequestBody @Valid DepositoTypeRequest depositoTypeRequest, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED).body(depositoService.depositar(depositoTypeRequest, auth));
    }

}
