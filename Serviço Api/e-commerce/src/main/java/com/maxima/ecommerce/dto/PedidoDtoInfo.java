package com.maxima.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDtoInfo {

    private Long codigoPedido;
    private String idCliente;
    private BigDecimal valorFrete;
    private List<ItensPedidoDTO> items;

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public List<ItensPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItensPedidoDTO> items) {
        this.items = items;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
