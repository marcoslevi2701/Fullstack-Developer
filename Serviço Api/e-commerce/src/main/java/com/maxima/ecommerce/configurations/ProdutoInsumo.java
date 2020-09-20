package com.maxima.ecommerce.configurations;

import com.maxima.ecommerce.domain.Produto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoInsumo {

    private final  Session session;

    private static final String SQL_SELECT = "SELECT ID FROM PRODUTOS WHERE ID = ? AND CODIGO = ?";
    private static final String SQL_INSERT = "INSERT INTO PRODUTOS (ID,CODIGO,IMAGEM_URL,NOME,PRECO_UNITARIO) VALUES (?,?,?,?,?)";

    public ProdutoInsumo(Session session) {
       this.session = session;
    }

    public void salvarDadosProduto(){
       List<Produto> produtos = this.obterProdutos();

       Transaction transaction = session.beginTransaction();

       Query query = session.createNativeQuery(SQL_INSERT);

       produtos.forEach(produto -> {
         if(!existeRegistroProduto(produto.obterId(), produto.obterCodigo())){

             query.setParameter(1,produto.obterId());
             query.setParameter(2,produto.obterCodigo());
             query.setParameter(3,produto.getImagemUrl());
             query.setParameter(4,produto.getNome());
             query.setParameter(5,produto.getPrecoUnitario());

             query.executeUpdate();
         }
       });

       transaction.commit();
    }

    private boolean existeRegistroProduto(String id,String codigo){
       Query query = session.createSQLQuery(SQL_SELECT);
       query.setParameter(1,id);
       query.setParameter(2,codigo);
       return query.getResultList().size() > 0;
    }

    private List<Produto> obterProdutos(){
      List<Produto> produtos = new ArrayList();

      produtos.add(
       new Produto("176c3694-f403-47d7-848c-7152ba4950d6","50","b9d3b74e-c97c-4029-bff3-aa5d051010f5",new BigDecimal(178),null)
      );

      produtos.add(
        new Produto("176c3694-f403-47d7-848c-7152ba4950d6","12","iPhone 11 Apple com 128GB, Tela Retina HD de 6,1”, iOS 13, Dupla Câmera Traseira de 12 MP, Resistente à Água e Bateria de Longa Duração - Branco",
                new BigDecimal(4699),"https://images-submarino.b2w.io/produtos/01/00/img/1611324/8/1611324805_1SZ.jpg")
      );

      produtos.add(
        new Produto("176c3694-f403-47d7-848c-7152ba4950d6","35","Monitor Widescreen LCD LED 18.5” AOC HD E970SWNL",new BigDecimal(444.9),null)
      );

      produtos.add(
        new Produto("a2f49077-99d2-4a16-89f1-83315d73468b","312800A","Fogão 3 Bocas 1 Dupla Cristalaço Industrial Baixa Pressão", new BigDecimal(718.08),null)
      );

      produtos.add(
        new Produto("c363bddf-18a5-48e9-a874-5bd178e09198","478ZB","Fone de Ouvido Apple AirPods 2 com Estojo de Recarga",new BigDecimal(1099),null)
      );

      produtos.add(
        new Produto("fc845cba-cf11-42bc-9506-acfa04c5c935","114523KL","Galaxy S20 Ultra Cosmic Gray 128GB", new BigDecimal(6029.1),
                "https://images-submarino.b2w.io/produtos/01/00/img/1521500/7/1521500721_1SZ.jpg")
      );

      return produtos;
    }
}
