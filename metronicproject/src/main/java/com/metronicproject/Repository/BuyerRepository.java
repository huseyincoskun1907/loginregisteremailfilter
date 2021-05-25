package com.metronicproject.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metronicproject.model.Buyer;
import com.metronicproject.model.User;

import java.util.List;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long> {
    Buyer findBuyerByUser(User user);

}
