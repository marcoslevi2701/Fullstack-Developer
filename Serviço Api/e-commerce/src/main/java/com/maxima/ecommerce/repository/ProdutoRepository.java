package com.maxima.ecommerce.repository;

import com.maxima.ecommerce.domain.Produto;
import com.maxima.ecommerce.domain.ProdutoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, ProdutoID> {
    Optional<Produto> findByProdutoID_IdAndProdutoID_Codigo(String id,String codigo);
}
