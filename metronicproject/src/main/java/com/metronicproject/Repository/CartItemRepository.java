package com.metronicproject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metronicproject.model.CartItem;


@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
