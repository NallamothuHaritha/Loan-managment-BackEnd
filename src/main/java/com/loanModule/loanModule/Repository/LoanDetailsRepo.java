package com.loanModule.loanModule.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loanModule.loanModule.Entity.LoanDetails;

public interface LoanDetailsRepo extends JpaRepository<LoanDetails,Long> {

 	    Optional<LoanDetails> findTopByCustomer_CustomerIdOrderByAccountCreationDateDesc(@Param("customerId") Long customerId);

	
  //  Optional<LoanDetails> findByCustomer_CustomerId(Long customerId);

}
