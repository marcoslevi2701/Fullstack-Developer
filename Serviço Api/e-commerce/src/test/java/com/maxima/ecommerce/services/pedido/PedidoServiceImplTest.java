package com.maxima.ecommerce.services.pedido;

import com.maxima.ecommerce.domain.*;
import com.maxima.ecommerce.dto.ItensPedidoDTO;
import com.maxima.ecommerce.dto.PedidoDtoInfo;
import com.maxima.ecommerce.services.cliente.ClienteService;
import com.maxima.ecommerce.services.produto.ProdutoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@SpringBootTest
@Transactional
class PedidoServiceImplTest {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    private static final PedidoDtoInfo pedido = new PedidoDtoInfo();
    private static final Cliente cliente = new Cliente();
    private static final Produto produto = new Produto();
    private static final ProdutoID produtoID = new ProdutoID();
    private static final ItensPedidoDTO item = new ItensPedidoDTO();
    private static final List<ItensPedidoDTO>  itens = new ArrayList();


    @BeforeAll
    static void setup(){
      cliente.setId("1");
      cliente.setCodigo("10");
      cliente.setNome("teste");

      produto.setImagemUrl("teste imagem");
      produto.setNome("teste nome");
      produto.setPrecoUnitario(new BigDecimal("129.99"));

      produtoID.setCodigo("1");
      produtoID.setId("2");

      produto.setProdutoID(produtoID);

      pedido.setCodigoPedido(1L);
      pedido.setValorFrete(new BigDecimal(10));

      item.setId_produto(produtoID.getId());
      item.setCodigo_produto(produtoID.getCodigo());
      item.setQuantidade(10);

      itens.add(item);
    }

    @Test
    public void teste_inserir_nulo(){
        assertThatThrownBy( () -> pedidoService.save(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o pedido.");
    }

    @Test
    public void teste_inserir_sucesso(){
      Cliente clienteCadastro = this.clienteService.save(cliente);
      this.produtoService.save(produto);

      pedido.setItems(itens);
      pedido.setIdCliente(clienteCadastro.getId());

      Pedido pedidoInserido = this.pedidoService.save(pedido);

      assertThat(pedidoInserido,notNullValue());
      assertThat(pedidoInserido.getId(),notNullValue());
      assertThat(pedidoInserido.getCodigo(),notNullValue());
      assertThat(pedidoInserido.getCliente().getId(),equalTo(clienteCadastro.getId()));
      assertThat(pedidoInserido.getValorFrete(),equalTo(pedido.getValorFrete()));


      List<Item> itensPedido = this.itemPedidoService.findAllByItemID_Pedido(pedidoInserido.getId());

      assertThat(itensPedido.size(),equalTo(pedido.getItems().size()));
      assertThat(itensPedido.get(0).obterProduto().obterCodigo(),equalTo(pedido.getItems().get(0).getCodigo_produto()));
      assertThat(itensPedido.get(0).obterProduto().obterId(),equalTo(pedido.getItems().get(0).getId_produto()));
    }

    @Test
    public void teste_inserir_sem_codigo(){
       Cliente clienteCadastro = this.clienteService.save(cliente);
       this.produtoService.save(produto);

       PedidoDtoInfo pedidoDtoInfo = new PedidoDtoInfo();
       pedidoDtoInfo.setIdCliente(clienteCadastro.getId());

       assertThatThrownBy( () -> this.pedidoService.save(pedidoDtoInfo))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o codigo do pedido.");
    }

    @Test
    public void teste_inserir_semClientes(){
        this.produtoService.save(produto);

        PedidoDtoInfo pedidoDtoInfo = new PedidoDtoInfo();
        pedidoDtoInfo.setCodigoPedido(1L);

        assertThatThrownBy( () -> this.pedidoService.save(pedidoDtoInfo))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o cliente do pedido.");
    }

    @Test
    public void teste_inserir_semValorFrete(){
        Cliente clienteCadastro = this.clienteService.save(cliente);
        this.produtoService.save(produto);

        PedidoDtoInfo pedidoDtoInfo = new PedidoDtoInfo();
        pedidoDtoInfo.setCodigoPedido(1L);
        pedidoDtoInfo.setIdCliente(clienteCadastro.getId());

        assertThatThrownBy( () -> this.pedidoService.save(pedidoDtoInfo))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o valor do frete.");
    }

    @Test
    public void teste_inserir_semItens(){
        Cliente clienteCadastro = this.clienteService.save(cliente);
        this.produtoService.save(produto);

        PedidoDtoInfo pedidoDtoInfo = new PedidoDtoInfo();
        pedidoDtoInfo.setCodigoPedido(1L);
        pedidoDtoInfo.setIdCliente(clienteCadastro.getId());
        pedidoDtoInfo.setValorFrete(new BigDecimal("10"));

        assertThatThrownBy( () -> this.pedidoService.save(pedidoDtoInfo))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar quais são os itens do pedido.");
    }

    @Test
    public void teste_inserir_sem_cliente_cadastrado(){
       this.produtoService.save(produto);

       PedidoDtoInfo pedidoDtoInfo = new PedidoDtoInfo();
       pedidoDtoInfo.setCodigoPedido(1L);
       pedidoDtoInfo.setIdCliente(cliente.getId());
       pedidoDtoInfo.setValorFrete(new BigDecimal("10"));
       pedidoDtoInfo.setItems(itens);

       assertThatThrownBy( () -> this.pedidoService.save(pedidoDtoInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cliente informado não está cadastrado.");
    }

    @Test
    public void teste_inserir_sem_produto_cadastrado(){
        Cliente clienteCadastro = this.clienteService.save(cliente);

        PedidoDtoInfo pedidoDtoInfo = new PedidoDtoInfo();
        pedidoDtoInfo.setCodigoPedido(1L);
        pedidoDtoInfo.setIdCliente(clienteCadastro.getId());
        pedidoDtoInfo.setValorFrete(new BigDecimal("10"));


        ItensPedidoDTO itensPedidoDTOVazio = new ItensPedidoDTO();
        itensPedidoDTOVazio.setQuantidade(10);
        itensPedidoDTOVazio.setCodigo_produto("100");
        itensPedidoDTOVazio.setId_produto("50");

        List<ItensPedidoDTO> itensVazios = new ArrayList();

        itensVazios.add(itensPedidoDTOVazio);

        pedidoDtoInfo.setItems(itensVazios);

        assertThatThrownBy( () -> this.pedidoService.save(pedidoDtoInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Existem produtos que não estão cadastrados, favor verificar.");
    }

    @Test
    public void teste_inserir_sem_produtoRepetido(){
        Cliente clienteCadastro = this.clienteService.save(cliente);

        PedidoDtoInfo pedidoDtoInfo = new PedidoDtoInfo();
        pedidoDtoInfo.setCodigoPedido(1L);
        pedidoDtoInfo.setIdCliente(clienteCadastro.getId());
        pedidoDtoInfo.setValorFrete(new BigDecimal("10"));

        Produto produtoCadastrado     = new Produto();
        ProdutoID produtoIDCadastrado = new ProdutoID();

        produtoCadastrado.setNome("teste");
        produtoCadastrado.setPrecoUnitario(new BigDecimal("10"));
        produtoCadastrado.setImagemUrl("teste");

        produtoIDCadastrado.setId("50");
        produtoIDCadastrado.setCodigo("100");

        produtoCadastrado.setProdutoID(produtoIDCadastrado);

        this.produtoService.save(produtoCadastrado);

        ItensPedidoDTO itensPedidoDTOVazio = new ItensPedidoDTO();
        itensPedidoDTOVazio.setQuantidade(10);

        itensPedidoDTOVazio.setId_produto("50");
        itensPedidoDTOVazio.setCodigo_produto("100");

        List<ItensPedidoDTO> itensVazios = new ArrayList();

        itensVazios.add(itensPedidoDTOVazio);
        itensVazios.add(itensPedidoDTOVazio);

        pedidoDtoInfo.setItems(itensVazios);

        assertThatThrownBy( () -> this.pedidoService.save(pedidoDtoInfo))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Existem produtos que estão informados mais de uma vez, favor verificar.");
    }

    @Test
    public void obterNulo(){
        assertThatThrownBy( () -> this.pedidoService.findById(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o id");
    }

    @Test
    public void obterSucesso(){
       Cliente clienteCadastro = this.clienteService.save(cliente);
       this.produtoService.save(produto);

       pedido.setItems(itens);
       pedido.setIdCliente(clienteCadastro.getId());

       Pedido pedidoInserido = this.pedidoService.save(pedido);

       assertThat(pedidoInserido,notNullValue());
       assertThat(pedidoInserido.getId(),notNullValue());
       assertThat(pedidoInserido.getCodigo(),notNullValue());
       assertThat(pedidoInserido.getCliente().getId(),equalTo(clienteCadastro.getId()));

       Pedido pedidoBusca = this.pedidoService.findById(pedidoInserido.getId()).get();

       assertThat(pedidoBusca,notNullValue());
       assertThat(pedidoBusca.getId(),equalTo(pedidoInserido.getId()));
    }





}