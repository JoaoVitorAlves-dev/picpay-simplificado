package com.alves.picpay.controller;

import com.alves.picpay.dto.request.RegisterRequest;
import com.alves.picpay.dto.response.ContaResponse;
import com.alves.picpay.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest req) {
        contaService.criar(req);

        return ResponseEntity.status(HttpStatus.CREATED).body()
    }

}
