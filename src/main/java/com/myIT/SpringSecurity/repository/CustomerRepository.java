package com.myIT.SpringSecurity.repository;

import com.myIT.SpringSecurity.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    List<Customer> findByEmail(String email);
}
