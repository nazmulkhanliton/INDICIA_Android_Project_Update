package com.indicia.indicia.predictor;

public class DiseaseSymptom {
    private DiseaseModel diseaseModel;
    private SymptomModel symptomModel;

    public DiseaseSymptom(DiseaseModel diseaseModel, SymptomModel symptomModel) {
        this.diseaseModel = diseaseModel;
        this.symptomModel = symptomModel;
    }

    public DiseaseModel getDiseaseModel() {
        return diseaseModel;
    }

    public void setDiseaseModel(DiseaseModel diseaseModel) {
        this.diseaseModel = diseaseModel;
    }

    public SymptomModel getSymptomModel() {
        return symptomModel;
    }

    public void setSymptomModel(SymptomModel symptomModel) {
        this.symptomModel = symptomModel;
    }
}
