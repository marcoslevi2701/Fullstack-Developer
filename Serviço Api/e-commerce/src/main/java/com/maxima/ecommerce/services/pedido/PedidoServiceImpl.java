package com.maxima.ecommerce.services.pedido;

import com.maxima.ecommerce.domain.Cliente;
import com.maxima.ecommerce.domain.Item;
import com.maxima.ecommerce.domain.Pedido;
import com.maxima.ecommerce.domain.Produto;
import com.maxima.ecommerce.dto.ItensPedidoDTO;
import com.maxima.ecommerce.dto.PedidoDtoInfo;
import com.maxima.ecommerce.repository.PedidoRepository;
import com.maxima.ecommerce.services.cliente.ClienteService;
import com.maxima.ecommerce.services.produto.ProdutoService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements  PedidoService{

    private final PedidoRepository  pedidoRepository;
    private final ClienteService    clienteService;
    private final ProdutoService    produtoService;
    private final ItemPedidoService itemPedidoService;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteService clienteService, ProdutoService produtoService, ItemPedidoService itemPedidoService) {
      this.pedidoRepository = pedidoRepository;
      this.clienteService = clienteService;
      this.produtoService = produtoService;
        this.itemPedidoService = itemPedidoService;
    }

    @Override
    public List<Pedido> findAll() {
      return this.pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> findById(String id) {
      Validate.notNull(id,"Necessário informar o id");
      return this.pedidoRepository.findById(id);
    }

    @Override
    public Pedido save(PedidoDtoInfo pedidoDtoInfo) {
      validarInformacoesObrigatoriasPedido(pedidoDtoInfo);

      List<ItensPedidoDTO> itensInexistentes = this.produtoService.validarProdutosExistentes(pedidoDtoInfo.getItems());

      if(!CollectionUtils.isEmpty(itensInexistentes)){
         throw new RuntimeException("Existem produtos que não estão cadastrados, favor verificar.");
      }

      Long quantidadeItensInforamados = pedidoDtoInfo.getItems().stream().count();
      Long quantidadeItensRepetidos   = this.produtoService.validarItensRepetidosPedido(pedidoDtoInfo.getItems());

      if(!quantidadeItensInforamados.equals(quantidadeItensRepetidos)){
         throw new RuntimeException("Existem produtos que estão informados mais de uma vez, favor verificar.");
      }

      Pedido pedido = new Pedido();

      Optional<Cliente> cliente = this.clienteService.findById(pedidoDtoInfo.getIdCliente());
      if(!this.clienteService.validarClienteExiste(cliente)){
         throw new RuntimeException("Cliente informado não está cadastrado.");
      } else {
         pedido.setCliente(cliente.get());
         pedido.setCodigo(pedidoDtoInfo.getCodigoPedido());
         pedido.setValorFrete(pedidoDtoInfo.getValorFrete());
      }

      pedido = this.pedidoRepository.save(pedido);

      List<Item> itens = salvarItensPedido(pedidoDtoInfo.getItems(),pedido);

      pedido.setItems(itens);

      return this.pedidoRepository.save(pedido);
    }

    @Override
    public Long gerarCodigoPedido() {
      return this.pedidoRepository.gerarCodigoPedido();
    }

    private void validarInformacoesObrigatoriasPedido(PedidoDtoInfo pedidoDtoInfo){
      Validate.notNull(pedidoDtoInfo,"Necessário informar o pedido.");
      Validate.notNull(pedidoDtoInfo.getCodigoPedido(),"Necessário informar o codigo do pedido.");
      Validate.notNull(pedidoDtoInfo.getIdCliente(),"Necessário informar o cliente do pedido.");
      Validate.notNull(pedidoDtoInfo.getValorFrete(),"Necessário informar o valor do frete.");
      Validate.notNull(pedidoDtoInfo.getItems(),"Necessário informar quais são os itens do pedido.");
    }

    private List<Item> salvarItensPedido(List<ItensPedidoDTO> itensPedidoDTOS, Pedido pedido){
        List<Item> itens = new ArrayList();

        for(ItensPedidoDTO itemDTO :  itensPedidoDTOS){
            Optional<Produto> produto = this.produtoService
                                            .findByProdutoID_IdAndProdutoID_Codigo(itemDTO.getId_produto(),itemDTO.getCodigo_produto());
            Item item  = this.itemPedidoService.save(new Item(pedido,produto.get(),itemDTO.getQuantidade()));
            itens.add(item);
        }
        return itens;
    }

}
