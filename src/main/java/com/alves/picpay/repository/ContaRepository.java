package com.alves.picpay.repository;

import com.alves.picpay.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {

    Optional<Conta> findByNomeUsuario(String username);

    Boolean existsByNomeUsuario(String username);

}
