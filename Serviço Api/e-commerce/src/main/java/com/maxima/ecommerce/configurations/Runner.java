package com.maxima.ecommerce.configurations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class Runner implements CommandLineRunner {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public void run(String... args) throws Exception {
      this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
      this.session = sessionFactory.openSession();

      ClienteInsumo clienteInsumo = new ClienteInsumo(this.session);
      clienteInsumo.salvarDadosClientes();

      ProdutoInsumo produtoInsumo = new ProdutoInsumo(this.session);
      produtoInsumo.salvarDadosProduto();

      this.criarSequence();
    }

    private void criarSequence() throws SQLException {
       final String sql = "CREATE SEQUENCE IF  NOT EXISTS SEQUENCE_PEDIDO";
       Connection connection = ((SessionImpl)this.session).connection();

       PreparedStatement pst = connection.prepareStatement(sql);

       pst.execute();
    }

}
