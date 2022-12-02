package com.github.wendellemosmoura.vendas.rest.controller;

import com.github.wendellemosmoura.vendas.domain.entity.Pedido;
import com.github.wendellemosmoura.vendas.domain.repository.ItensPedido;
import com.github.wendellemosmoura.vendas.rest.dto.InformacaoItemPedidoDto;
import com.github.wendellemosmoura.vendas.rest.dto.InformacoesPedidoDto;
import com.github.wendellemosmoura.vendas.rest.dto.PedidoDto;
import com.github.wendellemosmoura.vendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InformacoesPedidoDto getById(@PathVariable Integer id) {
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> (converter(p)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacoesPedidoDto converter(Pedido pedido) {
        return InformacoesPedidoDto
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .itensPedido(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDto> conv(List<ItensPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacaoItemPedidoDto
                        .builder()
                        .descricaoProduto()
                        .precoUnitario()
                        .quantidade()
                        .build()
        ).collect(Collectors.toList());
    }
}
