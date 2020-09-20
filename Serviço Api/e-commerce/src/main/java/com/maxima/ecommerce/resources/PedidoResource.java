package com.maxima.ecommerce.resources;

import com.maxima.ecommerce.domain.Item;
import com.maxima.ecommerce.domain.Pedido;
import com.maxima.ecommerce.dto.PedidoDTO;
import com.maxima.ecommerce.dto.PedidoDtoInfo;
import com.maxima.ecommerce.services.pedido.ItemPedidoService;
import com.maxima.ecommerce.services.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;

    @Autowired
    public PedidoResource(PedidoService pedidoService,ItemPedidoService itemPedidoService) {
        this.pedidoService     = pedidoService;
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
       List<Pedido> pedidos = this.pedidoService.findAll();
       if(CollectionUtils.isEmpty(pedidos)){
          return new ResponseEntity("Não foram encontrados pedidos.",HttpStatus.NOT_FOUND);
       }
       List<PedidoDTO> pedidosDTO = PedidoDTO.fromListDTO(pedidos);
       return new ResponseEntity(pedidosDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable(value = "id") String id){
       Optional<Pedido>  pedido = this.pedidoService.findById(id);
       if(!pedido.isPresent()){
          return new ResponseEntity("Não foi encontrado pedido com o id informado",HttpStatus.NOT_FOUND);
       }
       PedidoDTO pedidoDTO = PedidoDTO.fromDTO(pedido.get());
       return new ResponseEntity(pedidoDTO,HttpStatus.OK);
    }

    @GetMapping(value = "{id}/itens")
    public ResponseEntity<List<Item>> findAllItens(@PathVariable String id){
       List<Item> itens = this.itemPedidoService.findAllByItemID_Pedido(id);
       if(CollectionUtils.isEmpty(itens)){
          return new ResponseEntity("Não foram encontrados itens para o pedido informado.",HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity(itens, HttpStatus.OK);
    }

    @GetMapping(value = "/codigo")
    public ResponseEntity<Integer> getCodeNext(){
       Long codigo = this.pedidoService.gerarCodigoPedido();
       return new ResponseEntity(codigo,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> saveOrder(@RequestBody PedidoDtoInfo pedidoDtoInfo){
       Pedido pedido = this.pedidoService.save(pedidoDtoInfo);
       return new ResponseEntity(pedido,HttpStatus.CREATED);
    }




}
