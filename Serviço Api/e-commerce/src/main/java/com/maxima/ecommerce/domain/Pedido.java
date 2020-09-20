package com.maxima.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;

    private Long codigo;

    @ManyToOne
    private Cliente cliente;

    private BigDecimal valorFrete;

    @OneToMany(mappedBy = "itemID.pedido")
    private List<Item> items;

    public Pedido() {
      this.items = new ArrayList();
    }

    @JsonProperty("valorTotal")
    public BigDecimal obterValorTotalItens(){
        Double valor = this.items.stream()
                .mapToDouble(item -> item.obterPrecoTotalItem().doubleValue())
                .sum();

        BigDecimal valorTotal = new BigDecimal(valor).setScale(2,BigDecimal.ROUND_HALF_UP);
        return valorTotal;
    }

    @JsonProperty("quantidadeItens")
    public Integer obterQuantidadeDeItens(){
        return this.items.size();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
}
