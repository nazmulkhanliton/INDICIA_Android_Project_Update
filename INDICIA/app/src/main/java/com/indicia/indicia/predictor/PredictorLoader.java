package com.indicia.indicia.predictor;

import android.content.Context;

import com.indicia.indicia.R;

import java.util.ArrayList;

public class PredictorLoader {

    private static Context context = null;
    private static boolean loaded = false;

    public PredictorLoader(Context context) {
        this.context = context;
    }

    private static ArrayList<DiseaseSymptom> diseaseSymptoms = null;

    public static ArrayList<DiseaseSymptom> load() {
        if (diseaseSymptoms == null) {
            diseaseSymptoms = new ArrayList<>();
            if (context != null) {
                int[] ids = {R.array.predictor_disease_symptom_array};
                int count = 0;
                for (int i = 0; i < ids.length; i++) {
                    String[] str = context.getResources().getStringArray(ids[i]);
                    count = count + appendToList(str, diseaseSymptoms, count);
                }
            }
        }
        return diseaseSymptoms;
    }

    public static int appendToList(String[] arrayList, ArrayList<DiseaseSymptom> diseaseSymptoms, int count) {
        for (int i = 0; i < arrayList.length; i++) {
            String[] str = arrayList[i].split("###");
            DiseaseModel diseaseModel = new DiseaseModel(String.valueOf(i + 1), str[0], 0.0, str[2]);
            SymptomModel symptomModel = new SymptomModel(String.valueOf(i + 1), str[1]);
            DiseaseSymptom diseaseSymptom = new DiseaseSymptom(diseaseModel, symptomModel);
            diseaseSymptoms.add(diseaseSymptom);
            count++;
        }
        return count;
    }

    public static boolean isLoaded() {
        return loaded;
    }

    public static ArrayList<DiseaseSymptom> getDiseaseSymptoms() {
        if (!isLoaded()) {
            load();
        }
        return diseaseSymptoms;
    }
}
