package com.maxima.ecommerce.services.cliente;

import com.maxima.ecommerce.domain.Cliente;
import com.maxima.ecommerce.repository.ClienteRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
       this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
       return this.clienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
       validarInformacoesCliente(cliente);
       return this.clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> findById(String id) {
       Validate.notNull(id,"Necessário informar o id para a busca do cliente.");
       return this.clienteRepository.findById(id);
    }

    @Override
    public boolean validarClienteExiste(Optional<Cliente> cliente) {
      return cliente.isPresent();
    }

    private void validarInformacoesCliente(Cliente cliente){
       Validate.notNull(cliente,"Necessário informar o cliente");
       Validate.isTrue(StringUtils.isNotBlank(cliente.getNome()),"Necessário informar o nome do cliente");
    }


}
