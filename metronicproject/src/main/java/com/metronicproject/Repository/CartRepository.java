package com.metronicproject.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metronicproject.model.CartItem;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartItem, Long> {
    @Query("select ci from CartItem ci where ci.buyer.id = :buyerId")
    public List<CartItem> getCartItemByBuyerId(Long buyerId);
}
