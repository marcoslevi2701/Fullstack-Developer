package com.maxima.ecommerce.services.pedido;

import com.maxima.ecommerce.domain.Pedido;
import com.maxima.ecommerce.dto.PedidoDtoInfo;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> findAll();
    Optional<Pedido> findById(String id);
    Pedido save(PedidoDtoInfo pedidoDtoInfo);
    Long gerarCodigoPedido();
}
