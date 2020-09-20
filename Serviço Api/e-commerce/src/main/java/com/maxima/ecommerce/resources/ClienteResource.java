package com.maxima.ecommerce.resources;

import com.maxima.ecommerce.domain.Cliente;
import com.maxima.ecommerce.services.cliente.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    @Autowired
    public ClienteResource(ClienteService clienteService) {
       this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
      List<Cliente> clientes = this.clienteService.findAll();
      if(CollectionUtils.isEmpty(clientes)){
        return new ResponseEntity("Não foram encontrados registros de clientes.", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(clientes,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable(value = "id") String id){
      Optional<Cliente> cliente = this.clienteService.findById(id);
      if(!cliente.isPresent()){
         return new ResponseEntity("Não foi encontrado cliente com o id informado.",HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(cliente.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
      return new ResponseEntity(this.clienteService.save(cliente),HttpStatus.CREATED);
    }

}
