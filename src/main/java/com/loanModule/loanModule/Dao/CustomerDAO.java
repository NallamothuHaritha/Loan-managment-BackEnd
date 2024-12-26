package com.loanModule.loanModule.Dao;


public class CustomerDAO {

	
		private Long customerId;
		private Long creditScore;
		private Double totalDebt;
		private String employmentStatus;
		

		public Long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}
		public Long getCreditScore() {
			return creditScore;
		}
		public void setCreditScore(Long creditScore) {
			this.creditScore = creditScore;
		}
		public Double getTotalDebt() {
			return totalDebt;
		}
		public void setTotalDebt(Double totalDebt) {
			this.totalDebt = totalDebt;
		}
		public String getEmploymentStatus() {
			return employmentStatus;
		}
		public void setEmploymentStatus(String employmentStatus) {
			this.employmentStatus = employmentStatus;
		}
	  
}
