package com.github.wendellemosmoura.vendas.rest.controller;

import com.github.wendellemosmoura.vendas.domain.entity.Pedido;
import com.github.wendellemosmoura.vendas.rest.dto.PedidoDto;
import com.github.wendellemosmoura.vendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDto dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }
}
