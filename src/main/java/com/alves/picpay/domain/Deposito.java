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

    @Column(name = "valor_deposito", nullable = false)
    private BigDecimal valorDeposito;

    @Column(nullable = false, name = "nome_autor")
    private String nomeAutor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_deposito", nullable = false)
    private TipoDeposito tipoDeposito;

    @CreationTimestamp
    @Column(name = "data_deposito", nullable = false)
    private LocalDateTime data;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public BigDecimal getValorDeposito() {
        return valorDeposito;
    }

    public void setValorDeposito(BigDecimal valorDeposito) {
        this.valorDeposito = valorDeposito;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public TipoDeposito getTipoDeposito() {
        return tipoDeposito;
    }

    public void setTipoDeposito(TipoDeposito tipoDeposito) {
        this.tipoDeposito = tipoDeposito;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
