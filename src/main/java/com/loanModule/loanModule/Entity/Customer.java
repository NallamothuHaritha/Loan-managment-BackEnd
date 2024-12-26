package com.loanModule.loanModule.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Marks this class as a JPA Entity
@Table(name = "customer") // Maps the class to the "customer" table in the database
public class Customer {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    @Column(name = "customer_id") // Maps this field to the "customer_id" column
    private Long customerId;

    @Column(name = "store_id", nullable = false) // Maps this field to the "store_id" column
    private Long storeId;

    @Column(name = "first_name", length = 50, nullable = false) // Length limit for the column
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "address_id", nullable = false)
    private Long addressId;

    @Column(name = "active") // Boolean fields are mapped as tinyint(1) in most databases
    private Boolean active;    
    
    @Column(name = "authority_id")
    private Long authorityId;
   
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanDetails> loanDetails = new ArrayList<>();
    
    public void addLoanDetail(LoanDetails loanDetail) {
        loanDetails.add(loanDetail);
        loanDetail.setCustomer(this);
    }

    public void removeLoanDetail(LoanDetails loanDetail) {
        loanDetails.remove(loanDetail);
        loanDetail.setCustomer(null);
    }

	// Getter and Setter for accountCreationDate	
    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

   
	public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

}

