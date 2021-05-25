package com.metronicproject.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.metronicproject.model.Role;


public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findAll();
    Role findByRolename(String name);
}