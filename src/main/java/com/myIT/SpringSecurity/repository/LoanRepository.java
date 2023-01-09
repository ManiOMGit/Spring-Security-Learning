package com.myIT.SpringSecurity.repository;

import com.myIT.SpringSecurity.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loans, Long> {
    @PreAuthorize("hasRole('USER')")
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
