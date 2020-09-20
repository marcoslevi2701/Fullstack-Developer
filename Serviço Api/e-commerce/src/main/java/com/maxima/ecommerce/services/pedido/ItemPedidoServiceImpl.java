package com.maxima.ecommerce.services.pedido;

import com.maxima.ecommerce.domain.Item;
import com.maxima.ecommerce.repository.ItemRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemPedidoServiceImpl(ItemRepository itemRepository) {
       this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
       this.validarInformacoesObrigatorias(item);
       return this.itemRepository.save(item);
    }

    @Override
    public List<Item> findAllByItemID_Pedido(String id) {
        Validate.notNull(id,"Necessário informar o id do item para pesquisa.");
        return this.itemRepository.findAllByItemID_Pedido_Id(id);
    }

    private void validarInformacoesObrigatorias(Item item){
        Validate.notNull(item,"Necessário informar o item");
        Validate.notNull(item.getQuantidade(),"Necessário informar a quantiade do item");
        Validate.notNull(item.obterProduto(),"Necessário informar o produto do item");
    }


}
