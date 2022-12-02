package com.github.wendellemosmoura.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDto {
    private Integer produto;
    private Integer quantidade;
}
