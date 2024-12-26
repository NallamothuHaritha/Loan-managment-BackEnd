package com.loanModule.loanModule.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanModule.loanModule.Dao.CustomerDAO;
import com.loanModule.loanModule.Dao.ResponseEligibility;
import com.loanModule.loanModule.Entity.Customer;
import com.loanModule.loanModule.Entity.LoanDetails;
import com.loanModule.loanModule.Repository.CustomerRepository;
import  com.loanModule.loanModule.Repository.LoanDetailsRepo;
import java.time.LocalDate;

import java.time.temporal.ChronoUnit;


@Service
public class LoanService {

	@Autowired
	private LoanDetailsRepo loanDetailsRepo;

	@Autowired
	private CustomerRepository customerRepository;

	// creating a logger
    Logger logger
        = LoggerFactory.getLogger(LoanService.class);
	
	public ResponseEligibility checkLoanEligibility(CustomerDAO customerDao) {
  
		 logger.trace("Checking loan eligibility for: your at Service layer starting of function " + customerDao.getCustomerId());
		 logger.trace("Checking loan eligibility for: your at Service layer starting of function " + customerDao.getCreditScore());
		 logger.trace("Checking loan eligibility for: your at Service layer starting of function " + customerDao.getTotalDebt());
		 logger.trace("Checking loan eligibility for: your at Service layer starting of function " + customerDao.getEmploymentStatus());
	    
    	
    	
	    ResponseEligibility newResponse = new ResponseEligibility();
	    // Fetch customer data
	    Optional<Customer> customerOptional = customerRepository.findById(customerDao.getCustomerId());
	    if (!customerOptional.isPresent()) {
	        newResponse.setIsApproved("Rejected");
	        newResponse.setResponseMessages("You are not an existing customer.");
	        return newResponse;
	    }

	    Customer customer = customerOptional.get();

	    // Eligibility logic
	    StringBuilder rejectionReasons = new StringBuilder();
	    boolean eligible = true;

	    // 1. Minimum Credit Score
	    if (customerDao.getCreditScore() < 650) {
	        eligible = false;
	        rejectionReasons.append("Credit score below 650. ");
	    }

	    // 2. Outstanding Loan Limit
	    if (customerDao.getTotalDebt() > 10000) {
	        eligible = false;
	        rejectionReasons.append("Outstanding loans exceed $10,000. ");
	    }

	    // 3. Income-to-Debt Ratio (IDR)
	    double yearlyIncome = 50000; // Example; fetch from DB or API if needed
	    double idr = customerDao.getTotalDebt() / yearlyIncome;
	    if (idr >= 0.40) {
	        eligible = false;
	        rejectionReasons.append("Income-to-Debt Ratio exceeds 40%. ");
	    }

	    // 4. Employment Status
	    if ("Unemployed".equalsIgnoreCase(customerDao.getEmploymentStatus())) {
	        eligible = false;
	        rejectionReasons.append("Employment status is Unemployed. ");
	    }

	    // 5. Account Age Check (1 year minimum)
	    LocalDate currentDate = LocalDate.now();  // Define currentDate as the current date
	    Optional<LoanDetails> loanDetailsOptional = loanDetailsRepo.findByCustomer_CustomerId(customerDao.getCustomerId());
	    if (loanDetailsOptional.isPresent()) {
	    LocalDate accountCreatedDate = loanDetailsOptional.get().getAccountCreationDate(); // customer.getAccountCreationDate();  // Already LocalDate
	    long accountAgeInMonths = ChronoUnit.MONTHS.between(accountCreatedDate, currentDate);
	    if (accountAgeInMonths < 12) {
	        eligible = false;
	        rejectionReasons.append("Account age is less than 1 year. ");
	    }
	    }
	    // Prepare LoanDetails object
	    LoanDetails loanDetails = new LoanDetails();
	    loanDetails.setCustomer(customer);
	    loanDetails.setCreditScore(customerDao.getCreditScore());
	    loanDetails.setTotalDebt(customerDao.getTotalDebt());
	    loanDetails.setEmploymentStatus(customerDao.getEmploymentStatus());
	    loanDetails.setAccountCreationDate(LocalDate.now());

	    if (eligible) {
	        loanDetails.setLoanStatus("Approved");
	        newResponse.setIsApproved("Approved");
	        newResponse.setResponseMessages("You are eligible for a loan in the current cycle.");
	    } else {
	        loanDetails.setLoanStatus("Rejected");
	        newResponse.setIsApproved("Rejected");
	        newResponse.setResponseMessages(rejectionReasons.toString());
	    }

	    // Save loan details
	    loanDetailsRepo.save(loanDetails);
	    logger.trace("Checking loan eligibility for: your at Service layer ending of function " + customerDao.getCustomerId());
	    logger.trace("Checking loan eligibility for: your at Service layer ending of function " + customerDao.getCreditScore());
	    logger.trace("Checking loan eligibility for: your at Service layer ending of function " + customerDao.getTotalDebt());
	    logger.trace("Checking loan eligibility for: your at Service layer ending of function " + customerDao.getEmploymentStatus());
	    
	    
	    
	    return newResponse;
	}
}