package com.maxima.ecommerce.dto;

import com.maxima.ecommerce.domain.Pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoDTO {

    private String id;

    private Long codigoPedido;

    private BigDecimal valorTotal;

    private Integer quantidadeItens;

    private BigDecimal valorFrete;

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(Integer quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<PedidoDTO> fromListDTO(List<Pedido> pedidos){
       List<PedidoDTO> pedidoDTOS = new ArrayList();
       if(!pedidos.isEmpty() && pedidos.size() >= 1){
          pedidos.stream().forEach( pedido -> {
             PedidoDTO pedidoDTO = new PedidoDTO();

             pedidoDTO.setCodigoPedido(pedido.getCodigo());
             pedidoDTO.setValorFrete(pedido.getValorFrete());
             pedidoDTO.setQuantidadeItens(pedido.obterQuantidadeDeItens());
             pedidoDTO.setValorTotal(pedido.obterValorTotalItens());
             pedidoDTO.setId(pedido.getId());

             pedidoDTOS.add(pedidoDTO);
          });
       }
       return pedidoDTOS;
    }

    public static PedidoDTO fromDTO(Pedido pedido){
      PedidoDTO pedidoDTO = new PedidoDTO();
      if(Objects.nonNull(pedido)){
         pedidoDTO.setId(pedido.getId());
         pedidoDTO.setCodigoPedido(pedido.getCodigo());
         pedidoDTO.setValorTotal(pedido.obterValorTotalItens());
         pedidoDTO.setQuantidadeItens(pedido.obterQuantidadeDeItens());
         pedidoDTO.setValorFrete(pedido.getValorFrete());
      }
      return pedidoDTO;
    }

}
