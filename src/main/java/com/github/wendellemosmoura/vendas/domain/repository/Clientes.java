package com.github.wendellemosmoura.vendas.domain.repository;

import com.github.wendellemosmoura.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {
    List<Object> findByNomeLike(String nome);

}
