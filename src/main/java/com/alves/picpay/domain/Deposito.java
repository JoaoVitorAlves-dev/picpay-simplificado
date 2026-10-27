package com.alves.picpay.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_depositos")
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String UUID;

    private BigDecimal valorDeposito;

    private String nomeAutor;

//    private TipoDeposito tipoDeposito;

    @CreationTimestamp
    private LocalDateTime data;


}
