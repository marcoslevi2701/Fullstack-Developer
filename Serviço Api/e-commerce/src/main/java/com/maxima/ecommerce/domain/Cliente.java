package com.maxima.ecommerce.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTES")
public class Cliente {

    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;

    private String codigo;

    private String nome;

    public Cliente(String id ,String codigo, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
    }

    public Cliente(){
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

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
}
