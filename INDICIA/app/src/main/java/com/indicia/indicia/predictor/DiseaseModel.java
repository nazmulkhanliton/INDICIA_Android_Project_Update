package com.indicia.indicia.predictor;

public class DiseaseModel {
    private String id;
    private String name;
    private Double weight;
    private String doctorType;

    public DiseaseModel(String id, String name, Double weight, String doctorType) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.doctorType = doctorType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }
}
