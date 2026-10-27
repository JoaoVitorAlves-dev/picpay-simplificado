package com.alves.picpay.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, name = "valor_transferencia")
    private BigDecimal valor;

    @Column(name = "nome_autor", nullable = false)
    private String nomeAutor;

    @Column(name = "tipo_transferencia", nullable = false)
    private TipoTransferencia tipoTransferencia;

    @CreationTimestamp
    @Column(name = "data_transferencia", nullable = false)
    private LocalDateTime dataTransferencia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public TipoTransferencia getTipoTransferencia() {
        return tipoTransferencia;
    }

    public void setTipoTransferencia(TipoTransferencia tipoTransferencia) {
        this.tipoTransferencia = tipoTransferencia;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }
}
