package com.maxima.ecommerce.services.cliente;

import com.maxima.ecommerce.domain.Cliente;
import com.maxima.ecommerce.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class ClienteServiceImplTest {

    @Autowired
    private ClienteService clienteService;

    private static final Cliente cliente = new Cliente();

    @BeforeAll
    static void setup(){
      cliente.setCodigo("1");
      cliente.setCodigo("1");
      cliente.setNome("teste");
    }

    @Test
    public void test_inserir_sucesso(){
       final Cliente clienteInserido = clienteService.save(cliente);

       assertThat(clienteInserido,notNullValue());

       assertThat(clienteInserido.getId(),notNullValue());

       assertThat(clienteInserido.getCodigo(),notNullValue());
       assertThat(clienteInserido.getCodigo(),equalTo(cliente.getCodigo()));

       assertThat(clienteInserido.getNome(),notNullValue());
       assertThat(clienteInserido.getNome(),equalTo(cliente.getNome()));
    }

    @Test
    public void teste_inserir_null(){
      assertThatThrownBy( () -> clienteService.save(null))
              .isInstanceOf(NullPointerException.class)
              .hasMessage("Necessário informar o cliente");
    }

    @Test
    public void teste_inserir_nomeVazio(){
      final Cliente cliente_nome = new Cliente();
      cliente_nome.setNome("");

      assertThatThrownBy( () -> clienteService.save(cliente_nome))
              .isInstanceOf(IllegalArgumentException.class)
              .hasMessage("Necessário informar o nome do cliente");
    }

    @Test
    public void obterNulo(){
        assertThatThrownBy( () -> this.clienteService.findById(null))
                         .isInstanceOf(NullPointerException.class)
                         .hasMessage("Necessário informar o id para a busca do cliente.");

    }

    @Test
    public void obterComSucesso(){
       final Cliente clienteInserir = this.clienteService.save(cliente);

       assertThat(clienteInserir,notNullValue());
       assertThat(clienteInserir.getId(),notNullValue());
       assertThat(clienteInserir.getCodigo(),notNullValue());
       assertThat(clienteInserir.getNome(),notNullValue());

       final Cliente clienteBusca = this.clienteService.findById(clienteInserir.getId()).get();

       assertThat(clienteBusca,notNullValue());
       assertThat(clienteBusca.getId(),equalTo(clienteInserir.getId()));
       assertThat(clienteBusca.getCodigo(),equalTo(clienteInserir.getCodigo()));
       assertThat(clienteBusca.getNome(),equalTo(clienteInserir.getNome()));
    }


}