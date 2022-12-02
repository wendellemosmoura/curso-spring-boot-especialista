package com.github.wendellemosmoura.vendas.service;

import com.github.wendellemosmoura.vendas.domain.entity.Pedido;
import com.github.wendellemosmoura.vendas.rest.dto.PedidoDto;

public interface PedidoService {
    Pedido salvar(PedidoDto dto);

    Pedido obterPedidoCompleto(Integer id);
}
