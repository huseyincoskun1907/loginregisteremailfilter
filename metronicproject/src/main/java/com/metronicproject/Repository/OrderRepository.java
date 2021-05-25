package com.metronicproject.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.metronicproject.model.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
