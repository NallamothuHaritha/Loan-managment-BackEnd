package com.loanModule.loanModule.Dao;



public class ResponseEligibility {
	
	private String isApproved;
	private String responseMessages;
	
	public String getisApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getResponseMessages() {
		return responseMessages;
	}
	public void setResponseMessages(String rejectionReasons) {
		this.responseMessages = rejectionReasons;
	}
	

}
