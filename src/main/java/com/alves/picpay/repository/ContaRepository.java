package com.alves.picpay.repository;

import com.alves.picpay.domain.Conta;
import com.alves.picpay.dto.response.ContaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {

    Optional<Conta> findByNomeUsuario(String username);

    Boolean existsByNomeUsuario(String username);

    Page<ContaResponse> findAllBy(Pageable pageable);

    Boolean existsByCpf(String cpf);

    Boolean existsByTelefone(String cpf);

}
