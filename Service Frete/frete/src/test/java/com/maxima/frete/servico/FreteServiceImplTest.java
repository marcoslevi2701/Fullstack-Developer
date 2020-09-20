package com.maxima.frete.servico;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class FreteServiceImplTest {

    @Autowired
    private FreteService freteService;


    @Test
    public void teste_inserir_quantidade_nula(){
        assertThatThrownBy( () -> this.freteService.calcularFrete(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Necessário informar a quantidade de itens.");
    }

    @Test
    public void teste_inserir_quantidade_invalida(){
        Integer quantidade = 0;
        assertThatThrownBy( () -> this.freteService.calcularFrete(quantidade))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quantidade informada inválida.");
    }

}