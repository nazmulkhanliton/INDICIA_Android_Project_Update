package com.indicia.indicia.finder;

public class Doctor {

    private String type;
    private String name;
    private String fee;
    private String details;

    public Doctor(String type, String name, String fee, String details) {
        this.type = type;
        this.name = name;
        this.fee = fee;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
