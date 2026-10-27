package com.alves.picpay.repository;

import com.alves.picpay.domain.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, String> {
}
