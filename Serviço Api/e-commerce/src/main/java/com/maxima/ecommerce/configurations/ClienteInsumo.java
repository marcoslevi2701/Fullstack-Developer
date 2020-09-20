package com.maxima.ecommerce.configurations;

import com.maxima.ecommerce.domain.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsumo {

    public final Session session;

    private static final String codigoCliente = "146bbaf1-f5c3-440b-b60e-52d0a29bc837";

    private static final String SQL_SELECT = "SELECT ID FROM CLIENTES WHERE ID = ?";
    private static final String SQL_INSERT = "INSERT INTO CLIENTES (ID,CODIGO,NOME) VALUES (?,?,?)";

    public ClienteInsumo(Session session) {
        this.session = session;
    }

    private List<Cliente> obterClientes(){
        List<Cliente> clientes = new ArrayList();

        clientes.add(new Cliente("4f8e6b89-7b3f-450d-9f9d-03dba15d6e3a",codigoCliente,"Kegyu Guida"));
        clientes.add(new Cliente("d758c9df-af24-4a8a-b9b4-f221e76c45be",codigoCliente,"Miohu Daein"));
        clientes.add(new Cliente("40ba630c-e838-4231-82c5-080215420358",codigoCliente,"Kauvi Hifio"));
        clientes.add(new Cliente("b219af51-3557-43e3-8233-74ceb7a75cd2",codigoCliente,"Befey Saoen"));
        clientes.add(new Cliente("fbf874af-da87-4b84-a0c3-f35251d566df",codigoCliente,"Lyeko Ceotirun"));
        clientes.add(new Cliente("69227753-526a-429b-856b-a6533e1bb208",codigoCliente,"Mebol Morui"));

        return clientes;
    }

    public void salvarDadosClientes(){
        List<Cliente> clientes = obterClientes();

        Transaction transaction = session.beginTransaction();

        Query query = session.createNativeQuery(SQL_INSERT);

        clientes.forEach( cliente -> {
            if(!existeRegistroCliente(cliente.getId())){
                query.setParameter(1,cliente.getId());
                query.setParameter(2,cliente.getCodigo());
                query.setParameter(3,cliente.getNome());
                query.executeUpdate();
            }
        });

        transaction.commit();
    }

     private boolean existeRegistroCliente(String id){
        Query query = session.createSQLQuery(SQL_SELECT);
        query.setParameter(1,id);
        return query.getResultList().size() > 0;
     }



}
