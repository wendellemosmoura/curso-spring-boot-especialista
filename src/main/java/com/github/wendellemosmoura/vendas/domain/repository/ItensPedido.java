package com.github.wendellemosmoura.vendas.domain.repository;

import com.github.wendellemosmoura.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
