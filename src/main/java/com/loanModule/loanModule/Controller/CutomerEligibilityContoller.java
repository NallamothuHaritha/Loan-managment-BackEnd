package com.loanModule.loanModule.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanModule.loanModule.Dao.CustomerDAO;
import com.loanModule.loanModule.Dao.ResponseEligibility;
import com.loanModule.loanModule.Entity.Customer;

import com.loanModule.loanModule.Repository.CustomerRepository;
import com.loanModule.loanModule.Service.LoanService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/users")
public class CutomerEligibilityContoller {
	
	 Logger logger= LoggerFactory.getLogger(CutomerEligibilityContoller.class);
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private CustomerRepository customerRepository;

	
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
	    return customerRepository.getCustomerByCustomerId(id);  // Use 'id' here
	}
	 
	// Check loan eligibility
	 @PostMapping("/eligibility")
	    public ResponseEntity<ResponseEligibility> checkLoanEligibility(@RequestBody CustomerDAO customerData) {
	        try {
	        	
	        	 logger.trace("Checking loan eligibility for: your at controller " + customerData.getCustomerId());
	        	 logger.trace("Checking loan eligibility for: your at controller " + customerData.getCreditScore());
	        	 logger.trace("Checking loan eligibility for: your at controller " + customerData.getTotalDebt());
	        	 logger.trace("Checking loan eligibility for: your at controller " + customerData.getEmploymentStatus());

	        	ResponseEligibility response = loanService.checkLoanEligibility(customerData);
	            if ("Approved".equalsIgnoreCase(response.getisApproved())) {
	            	System.out.println(response.getisApproved());
	            	System.out.println(response.getResponseMessages());
	                return ResponseEntity.ok(response); // Return HTTP 200 if approved
	            } else {
	            	System.out.println(response.getisApproved());
	            	System.out.println(response.getResponseMessages());
	                return ResponseEntity.ok(response);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(null); // Return HTTP 500 for unexpected errors
	        }
	    }
	
	 
	@GetMapping("hi")
     public String welcome() {
		return "Hi currently you in welcome CutomerEligibilityContoller..!!!!";
	}
	
	
	@GetMapping("bye")
    public String bye() {
		return "Hi currently you in bye CutomerEligibilityContoller..!!!!";
	}
}

