package com.indicia.indicia.predictor;

import com.indicia.indicia.ui.predictor.PredictorViewModel;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class PredictDiseaseFromSymptoms {

    private PredictorViewModel predictorViewModel;

    public void predict() {
        ArrayList<SymptomModel> symptoms = PredictorViewModel.getArrayListSymptoms();
        ArrayList<DiseaseModel> diseases = new ArrayList<>();
        ArrayList<DiseaseSymptom> diseaseSymptom = null;
        if (!PredictorLoader.isLoaded()) {
            diseaseSymptom = PredictorLoader.load();
        }
        if (diseaseSymptom != null) {
            TreeMap<String, Integer> treeFreq = new TreeMap<>();
            for (int i = 0; i < symptoms.size(); i++) {
                for (int j = 0; j < diseaseSymptom.size(); j++) {
                    if (symptoms.get(i).getId().equals(diseaseSymptom.get(j).getSymptomModel().getId())) {
                        if (treeFreq.containsKey(symptoms.get(i).getId())) {
                            int count = treeFreq.get(symptoms.get(i).getId());
                            treeFreq.put(symptoms.get(i).getId(), count + 1);
                        } else {
                            treeFreq.put(symptoms.get(i).getId(), 0);
                        }
                    }
                }
            }
            TreeMap<String, Integer> treeName = new TreeMap<>();
            for (Map.Entry<String, Integer> entry : treeFreq.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                for (int i = 0; i < diseaseSymptom.size(); i++) {
                    if (key.equals(diseaseSymptom.get(i).getDiseaseModel().getId())) {
                        if (!treeName.containsKey(diseaseSymptom.get(i).getDiseaseModel().getName())) {
                            diseases.add(new DiseaseModel(key, diseaseSymptom.get(i).getDiseaseModel().getName(), Double.parseDouble(value.toString()), diseaseSymptom.get(i).getDiseaseModel().getDoctorType()));
                            treeName.put(diseaseSymptom.get(i).getDiseaseModel().getName(), 1);
                        }
                    }
                }
            }
        }
        predictorViewModel = new PredictorViewModel();
        predictorViewModel.setArrayListDiseases(diseases);
    }
}
