package com.maxima.ecommerce.services.produto;

import com.maxima.ecommerce.domain.Produto;
import com.maxima.ecommerce.dto.ItensPedidoDTO;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    List<Produto> findAll();
    Optional<Produto> findByProdutoID_IdAndProdutoID_Codigo(String id,String codigo);
    Produto save(Produto produto);
    List<ItensPedidoDTO> validarProdutosExistentes(List<ItensPedidoDTO> itensPedidoDTOS);
    Long validarItensRepetidosPedido(List<ItensPedidoDTO> itensPedidoDTOS);
}
