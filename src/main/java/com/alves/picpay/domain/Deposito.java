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
    private String id;

    @Column(name = "valor_deposito", nullable = false)
    private BigDecimal valorDeposito = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, name = "nome_autor")
    private String nomeAutor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_deposito", nullable = false)
    private TipoDeposito tipoDeposito;

    @CreationTimestamp
    @Column(name = "data_deposito", nullable = false)
    private LocalDateTime data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValorDeposito() {
        return valorDeposito;
    }

    public void setValorDeposito(BigDecimal valorDeposito) {
        this.valorDeposito = valorDeposito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
