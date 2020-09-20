package com.maxima.ecommerce.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutoID implements Serializable {

    private String id;

    private String codigo;

    public ProdutoID(String id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public ProdutoID(){

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoID produtoID = (ProdutoID) o;
        return Objects.equals(id, produtoID.id) &&
                Objects.equals(codigo, produtoID.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }
}
