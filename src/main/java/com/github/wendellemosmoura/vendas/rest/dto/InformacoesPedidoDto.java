package com.github.wendellemosmoura.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class InformacoesPedidoDto {
    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private List<InformacaoItemPedidoDto> itens;
}
