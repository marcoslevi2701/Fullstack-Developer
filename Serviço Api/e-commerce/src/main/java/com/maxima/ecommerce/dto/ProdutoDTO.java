package com.maxima.ecommerce.dto;

import com.maxima.ecommerce.domain.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoDTO {

    private String id;

    private String codigo;

    private String nome;

    private BigDecimal precoUnitario;

    private String imagemUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public static ProdutoDTO fromDTO(Produto produto){
      ProdutoDTO produtoDTO = new ProdutoDTO();

      if(Objects.nonNull(produto)){
         produtoDTO.setId(produto.obterId());
         produtoDTO.setCodigo(produto.obterCodigo());
         produtoDTO.setNome(produto.getNome());
         produtoDTO.setPrecoUnitario(produto.getPrecoUnitario());
         produtoDTO.setImagemUrl(produto.getImagemUrl());
      }

      return  produtoDTO;
    }

    public static List<ProdutoDTO> fromListDTO(List<Produto> produtos){
       List<ProdutoDTO> produtoDTOS = new ArrayList();

       if(!produtos.isEmpty() && produtos.size() >= 1){
           produtos.forEach( produto -> {
             ProdutoDTO produtoDTO = new ProdutoDTO();

             produtoDTO.setId(produto.obterId());
             produtoDTO.setCodigo(produto.obterCodigo());
             produtoDTO.setNome(produto.getNome());
             produtoDTO.setPrecoUnitario(produto.getPrecoUnitario());
             produtoDTO.setImagemUrl(produto.getImagemUrl());

             produtoDTOS.add(produtoDTO);
          });
       }

       return produtoDTOS;
    }

}
