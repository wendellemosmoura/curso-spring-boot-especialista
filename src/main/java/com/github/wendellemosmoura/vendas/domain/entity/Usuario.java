package com.github.wendellemosmoura.vendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String  login;

    @Column
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String  senha;

    @Column
    private boolean admin;
}

