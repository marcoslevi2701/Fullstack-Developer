package com.maxima.ecommerce.repository;

import com.maxima.ecommerce.domain.Item;
import com.maxima.ecommerce.domain.ItemID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, ItemID> {
    List<Item> findAllByItemID_Pedido_Id(String id);
}
