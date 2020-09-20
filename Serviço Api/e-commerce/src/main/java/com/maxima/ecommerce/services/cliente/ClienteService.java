package com.maxima.ecommerce.services.cliente;

import com.maxima.ecommerce.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
   List<Cliente> findAll();
   Cliente save(Cliente cliente);
   Optional<Cliente> findById(String id);
   boolean validarClienteExiste(Optional<Cliente> cliente);
}
