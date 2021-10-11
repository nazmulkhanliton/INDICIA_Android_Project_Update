package com.indicia.indicia.ui.predictor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.indicia.indicia.predictor.DiseaseModel;
import com.indicia.indicia.predictor.DiseaseSymptom;
import com.indicia.indicia.predictor.PredictorLoader;
import com.indicia.indicia.predictor.SymptomModel;

import java.util.ArrayList;
import java.util.TreeMap;

public class PredictorViewModel extends ViewModel {

    private static ArrayList<SymptomModel> arrayListSymptoms;
    private static ArrayList<SymptomModel> arrayListSearchSymptoms;
    public static ArrayList<DiseaseModel> arrayListDiseases;
    private MutableLiveData<ArrayList<SymptomModel>> listSymptoms;
    private MutableLiveData<ArrayList<SymptomModel>> listSearchSymptoms;
    private MutableLiveData<ArrayList<DiseaseModel>> listDisease;

    public PredictorViewModel() {
        if (arrayListSymptoms == null) {
            arrayListSymptoms = new ArrayList<>();
        }
        if (arrayListSearchSymptoms == null) {
            arrayListSearchSymptoms = new ArrayList<>();
        }
        if (arrayListDiseases == null) {
            arrayListDiseases = new ArrayList<>();
        }
        listSymptoms = new MutableLiveData<>();
        listSearchSymptoms = new MutableLiveData<>();
        listDisease = new MutableLiveData<>();
        listSymptoms.setValue(arrayListSymptoms);
        listSearchSymptoms.setValue(arrayListSearchSymptoms);
        listDisease.setValue(arrayListDiseases);
    }


    public void addSymptom(SymptomModel symptomModel) {
        arrayListSymptoms.add(symptomModel);
        listSymptoms.setValue(arrayListSymptoms);
    }

    public void deleteSymptom(String id) {
        for (int i = 0; i < arrayListSymptoms.size(); i++) {
            if (arrayListSymptoms.get(i).getId().equals(id)) {
                arrayListSymptoms.remove(i);
                listSymptoms.setValue(arrayListSymptoms);
                break;
            }
        }
    }

    public boolean isInArray(String id) {
        for (int i = 0; i < arrayListSymptoms.size(); i++) {
            if (arrayListSymptoms.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<SymptomModel> getArrayListSymptoms() {
        return arrayListSymptoms;
    }

    public void setArrayListDiseases(ArrayList<DiseaseModel> arrayListDiseases) {
        this.arrayListDiseases = arrayListDiseases;
        listDisease.setValue(arrayListDiseases);
    }

    public LiveData<ArrayList<SymptomModel>> getSymptomList() {
        return listSymptoms;
    }

    public LiveData<ArrayList<SymptomModel>> getSymptomSearchList() {
        return listSearchSymptoms;
    }

    public LiveData<ArrayList<DiseaseModel>> getDiseaseList() {
        return listDisease;
    }

    public void setCurrentSymptomForSearch(String symptom) {
        ArrayList<DiseaseSymptom> diseaseSymptom = PredictorLoader.getDiseaseSymptoms();
        arrayListSearchSymptoms = new ArrayList<>();
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        if (symptom.isEmpty()) {
            for (int i = 0; i < diseaseSymptom.size(); i++) {
                SymptomModel symptomModel = diseaseSymptom.get(i).getSymptomModel();
                if (!treeMap.containsKey(symptomModel.getName())) {
                    arrayListSearchSymptoms.add(symptomModel);
                    treeMap.put(symptomModel.getName(), 1);
                }
            }
        } else {
            for (int i = 0; i < diseaseSymptom.size(); i++) {
                SymptomModel symptomModel = diseaseSymptom.get(i).getSymptomModel();
                String symptomName = symptomModel.getName();
                symptomName = symptomName.toLowerCase();
                symptom = symptom.toLowerCase();
                if (symptom.length() < symptomName.length()) {
                    boolean ok = true;
                    for (int j = 0; j < symptom.length(); j++) {
                        if (symptom.charAt(j) != symptomName.charAt(j)) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        if (!treeMap.containsKey(symptomModel.getName())) {
                            arrayListSearchSymptoms.add(symptomModel);
                            treeMap.put(symptomModel.getName(), 1);
                        }
                    }
                }
            }
        }
        listSearchSymptoms.setValue(arrayListSearchSymptoms);
    }
}