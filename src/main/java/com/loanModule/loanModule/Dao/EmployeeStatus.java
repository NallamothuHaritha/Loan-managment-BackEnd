package com.loanModule.loanModule.Dao;

public enum EmployeeStatus {
    EMPLOYED(true),
    SELF_EMPLOYED(true),
    UNEMPLOYED(false);

    private final boolean isEligible;

    EmployeeStatus(boolean isEligible) {
        this.isEligible = isEligible;
    }

    public boolean isEligible() {
        return isEligible;
    }
}
