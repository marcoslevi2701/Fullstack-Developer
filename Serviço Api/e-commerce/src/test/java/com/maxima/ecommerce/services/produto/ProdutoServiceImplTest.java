package com.maxima.ecommerce.services.produto;

import com.maxima.ecommerce.domain.Produto;
import com.maxima.ecommerce.domain.ProdutoID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class ProdutoServiceImplTest {

    @Autowired
    private ProdutoService produtoService;

    public static final Produto produto     = new Produto();
    public static final ProdutoID produtoID = new ProdutoID();

    @BeforeAll
    static void setup(){
       produto.setImagemUrl("teste imagem");
       produto.setNome("teste nome");
       produto.setPrecoUnitario(new BigDecimal("129.99"));

       produtoID.setCodigo("1");
       produtoID.setId("2");

       produto.setProdutoID(produtoID);
    }

    @Test
    public void teste_inserir_sucesso(){
       final Produto produtoInserido = this.produtoService.save(produto);

       assertThat(produtoInserido,notNullValue());
       assertThat(produtoInserido.obterCodigo(),equalTo(produto.obterCodigo()));
       assertThat(produtoInserido.obterId(),equalTo(produto.obterId()));

       assertThat(produtoInserido.getPrecoUnitario(),equalTo(produto.getPrecoUnitario()));
       assertThat(produtoInserido.getImagemUrl(),equalTo(produto.getImagemUrl()));
       assertThat(produtoInserido.getNome(),equalTo(produto.getNome()));
    }

    @Test
    public void teste_inserirNull(){
        assertThatThrownBy( () -> produtoService.save(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o produto");
    }

    @Test
    public void teste_inserirNomeVazio(){
        final Produto produtoNomeVazio = new Produto();

        produtoNomeVazio.setProdutoID(produtoID);

        produtoNomeVazio.setNome("");

        assertThatThrownBy( () -> produtoService.save(produtoNomeVazio))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Necessário informar o nome do produto");
    }

    @Test
    public void teste_inserir_sem_id(){
        final Produto produtoTeste = new Produto();

        produtoTeste.setNome("teste");
        produtoTeste.setProdutoID(new ProdutoID(null,"10"));

        assertThatThrownBy( () -> produtoService.save(produtoTeste))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o id");
    }

    @Test
    public void teste_inserir_sem_codigo(){
        final Produto produtoTeste = new Produto();

        produtoTeste.setNome("teste");
        produtoTeste.setProdutoID(new ProdutoID("10",null));

        assertThatThrownBy( () -> produtoService.save(produtoTeste))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o codigo do produto");
    }

    @Test
    public void teste_inserir_sem_preco(){
        final Produto produtoTeste = new Produto();

        produtoTeste.setNome("teste");
        produtoTeste.setProdutoID(new ProdutoID("10","20"));

        assertThatThrownBy( () -> produtoService.save(produtoTeste))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o preço do produto");
    }

    @Test
    public void obterNuloSemID(){
        assertThatThrownBy( () -> this.produtoService.findByProdutoID_IdAndProdutoID_Codigo(null,"10"))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o id");
    }

    @Test
    public void obterNuloSemCodigo(){
        assertThatThrownBy( () -> this.produtoService.findByProdutoID_IdAndProdutoID_Codigo("10",null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar o codigo do produto");
    }

    @Test
    public void obterSucesso(){
        final Produto produtoInserido = this.produtoService.save(produto);

        assertThat(produtoInserido,notNullValue());

        assertThat(produtoInserido.obterId(),notNullValue());
        assertThat(produtoInserido.obterCodigo(),notNullValue());
        assertThat(produtoInserido.getNome(),notNullValue());
        assertThat(produtoInserido.getImagemUrl(),notNullValue());
        assertThat(produtoInserido.getPrecoUnitario(),notNullValue());

        final Produto produtoBusca =
                this.produtoService.findByProdutoID_IdAndProdutoID_Codigo(produtoInserido.obterId(),produtoInserido.obterCodigo()).get();

        assertThat(produtoBusca,notNullValue());

        assertThat(produtoBusca.obterCodigo(),equalTo(produtoInserido.obterCodigo()));
        assertThat(produtoBusca.obterId(),equalTo(produtoInserido.obterId()));

        assertThat(produtoBusca.getPrecoUnitario(),equalTo(produtoInserido.getPrecoUnitario()));
        assertThat(produtoBusca.getImagemUrl(),equalTo(produtoInserido.getImagemUrl()));
        assertThat(produtoBusca.getNome(),equalTo(produtoInserido.getNome()));
    }

}