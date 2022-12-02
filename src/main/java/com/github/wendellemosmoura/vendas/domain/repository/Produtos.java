package com.github.wendellemosmoura.vendas.domain.repository;


import com.github.wendellemosmoura.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
