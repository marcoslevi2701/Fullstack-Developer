package com.maxima.frete.servico;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
public class FreteServiceImpl implements FreteService{

    private Random random;
    private static final Integer minimo = 5;
    private static final Integer maximo = 10;
    private static final Logger LOG = LoggerFactory.getLogger(FreteServiceImpl.class);

    public FreteServiceImpl() {
      this.random = new Random();
    }

    @Override
    public BigDecimal calcularFrete(Integer quantidade) {
      validarQuantidade(quantidade);
      Double valor = (random.nextDouble() * (maximo - minimo)) + (minimo);

      BigDecimal valorConvertido = new BigDecimal(valor).setScale(2,RoundingMode.CEILING);


      BigDecimal valorFrete = new BigDecimal(quantidade).multiply(valorConvertido).setScale(2, RoundingMode.UNNECESSARY);
      return valorFrete;
    }

    public void validarQuantidade(Integer quantidade){
        Validate.notNull(quantidade,"Necessário informar a quantidade de itens.");
        Validate.isTrue(quantidade > 0,"Quantidade informada inválida.");
    }
}
