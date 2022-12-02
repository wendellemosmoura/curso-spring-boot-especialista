package com.github.wendellemosmoura.vendas.service.impl;

import com.github.wendellemosmoura.vendas.domain.entity.Cliente;
import com.github.wendellemosmoura.vendas.domain.entity.ItemPedido;
import com.github.wendellemosmoura.vendas.domain.entity.Pedido;
import com.github.wendellemosmoura.vendas.domain.entity.Produto;
import com.github.wendellemosmoura.vendas.domain.repository.Clientes;
import com.github.wendellemosmoura.vendas.domain.repository.ItensPedido;
import com.github.wendellemosmoura.vendas.domain.repository.Pedidos;
import com.github.wendellemosmoura.vendas.domain.repository.Produtos;
import com.github.wendellemosmoura.vendas.exception.RegraNegocioException;
import com.github.wendellemosmoura.vendas.rest.dto.ItemPedidoDto;
import com.github.wendellemosmoura.vendas.rest.dto.PedidoDto;
import com.github.wendellemosmoura.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDto dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    @Override
    public Pedido obterPedidoCompleto(Integer id) {
        return null;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDto> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }
        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
