package com.alves.picpay.controller;

import com.alves.picpay.dto.response.DepositoResponse;
import com.alves.picpay.service.DepositoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
