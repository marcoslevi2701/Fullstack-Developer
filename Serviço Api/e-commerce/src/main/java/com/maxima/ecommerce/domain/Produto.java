package com.maxima.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTOS")
public class Produto {

    @EmbeddedId
    private ProdutoID produtoID;

    private String nome;

    private BigDecimal precoUnitario;

    private String imagemUrl;

    public Produto(String id ,String codigo, String nome, BigDecimal precoUnitario, String imagemUrl) {
        this.produtoID = new ProdutoID();
        this.produtoID.setId(id);
        this.produtoID.setCodigo(codigo);
        this.nome           = nome;
        this.precoUnitario  = precoUnitario;
        this.imagemUrl      = imagemUrl;
    }

    public Produto() {
       this.produtoID = new ProdutoID();
    }

    public void setProdutoID(ProdutoID produtoID) {
        this.produtoID = produtoID;
    }

    public ProdutoID getProdutoID() {
        return produtoID;
    }

    @JsonProperty("id")
    public String obterId(){
      return this.produtoID.getId();
    }

    @JsonProperty("codigo")
    public String obterCodigo(){
      return this.produtoID.getCodigo();
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




}
