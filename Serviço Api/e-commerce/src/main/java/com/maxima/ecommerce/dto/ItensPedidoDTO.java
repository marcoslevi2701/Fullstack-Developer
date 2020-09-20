package com.maxima.ecommerce.dto;

public class ItensPedidoDTO {
    private String  id_produto;
    private String  codigo_produto;
    private Integer quantidade;

    public ItensPedidoDTO(String id_produto, String codigo_produto, Integer quantidade) {
        this.id_produto = id_produto;
        this.codigo_produto = codigo_produto;
        this.quantidade = quantidade;
    }

    public ItensPedidoDTO() {
    }

    public String getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(String codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public String getId_produto() {
        return id_produto;
    }

    public void setId_produto(String id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


}
