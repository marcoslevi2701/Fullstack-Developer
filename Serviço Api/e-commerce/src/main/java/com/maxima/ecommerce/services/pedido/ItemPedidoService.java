package com.maxima.ecommerce.services.pedido;

import com.maxima.ecommerce.domain.Item;

import java.util.List;

public interface ItemPedidoService {
    Item save(Item item);
    List<Item> findAllByItemID_Pedido(String id);
}
