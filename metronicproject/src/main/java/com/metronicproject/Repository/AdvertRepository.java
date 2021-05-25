package com.metronicproject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.metronicproject.model.Advert;


@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {
}
