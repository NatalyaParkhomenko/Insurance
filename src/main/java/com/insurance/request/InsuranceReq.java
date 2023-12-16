package com.insurance.request;

public class InsuranceReq {
    private String type;
    private int coveragePercentage;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(int coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }
}
