package com.github.wendellemosmoura.vendas.service;

import com.github.wendellemosmoura.vendas.domain.entity.Pedido;
import com.github.wendellemosmoura.vendas.rest.dto.PedidoDto;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDto dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
