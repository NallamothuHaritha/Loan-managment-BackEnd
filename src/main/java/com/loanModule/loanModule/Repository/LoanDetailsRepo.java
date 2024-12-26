package com.loanModule.loanModule.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loanModule.loanModule.Entity.LoanDetails;

public interface LoanDetailsRepo extends JpaRepository<LoanDetails,Long> {

    Optional<LoanDetails> findByCustomer_CustomerId(Long customerId);

}
