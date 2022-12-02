package com.github.wendellemosmoura.vendas.domain.repository;

import com.github.wendellemosmoura.vendas.domain.entity.Cliente;
import com.github.wendellemosmoura.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    Optional<Pedido> findByIdFetchItens(Integer id);
}
