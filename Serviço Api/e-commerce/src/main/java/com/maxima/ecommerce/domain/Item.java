package com.maxima.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
public class Item {

    @JsonIgnore
    @EmbeddedId
    private ItemID itemID;

    private Integer quantidade;

    public Item(Pedido pedido,Produto produto,Integer quantidade) {
       this.itemID          = new ItemID();
       this.quantidade      = quantidade;
       this.itemID.setPedido(pedido);
       this.itemID.setProduto(produto);
    }

    public Item(){
       super();
    }

    @JsonProperty("Produto")
    public Produto obterProduto(){
       return this.itemID.getProduto();
    }

    @Transient
    @JsonProperty("precoFinal")
    public BigDecimal obterPrecoTotalItem(){
       return obterProduto().getPrecoUnitario().multiply(new BigDecimal(getQuantidade()));
    }

    public Integer getQuantidade() {
       return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
       this.quantidade = quantidade;
    }


}
