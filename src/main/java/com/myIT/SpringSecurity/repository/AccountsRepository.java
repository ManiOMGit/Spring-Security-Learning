package com.myIT.SpringSecurity.repository;

import com.myIT.SpringSecurity.model.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<Accounts, Long> {
    Accounts findByCustomerId(int customerId);
}
