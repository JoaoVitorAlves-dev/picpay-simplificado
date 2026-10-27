package com.alves.picpay.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role_usuario")
    private RoleUsuario roleUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleUsuario getRoleUsuario() {
        return roleUsuario;
    }

    public void setRoleUsuario(RoleUsuario roleUsuario) {
        this.roleUsuario = roleUsuario;
    }
}
