package com.maxima.ecommerce.repository;

import com.maxima.ecommerce.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido,String> {

    @Query(value = "SELECT nextval('SEQUENCE_PEDIDO')",nativeQuery = true)
    Long gerarCodigoPedido();
}
