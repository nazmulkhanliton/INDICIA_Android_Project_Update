package com.indicia.indicia.predictor;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indicia.indicia.R;
import com.indicia.indicia.ui.finder.FragmentDoctors;
import com.indicia.indicia.ui.predictor.PredictedDiseaseFragment;

import java.util.ArrayList;

public class PredictedDiseaseListAdapter extends ArrayAdapter<DiseaseModel> {

    private final PredictedDiseaseFragment predictedDiseaseFragment;
    private final Context context;
    private final int resourceLayout;

    public PredictedDiseaseListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DiseaseModel> objects, PredictedDiseaseFragment predictedDiseaseFragment) {
        super(context, resource, objects);
        this.context = context;
        this.resourceLayout = resource;
        this.predictedDiseaseFragment = predictedDiseaseFragment;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(resourceLayout, parent, false);
        }
        DiseaseModel diseaseModel = getItem(position);
        if (diseaseModel != null) {
            TextView id = view.findViewById(R.id.textview_disease_id);
            TextView name = view.findViewById(R.id.textview_disease_name);
            id.setText("D" + diseaseModel.getId().toString());
            name.setText(diseaseModel.getName().toString());
        }
        return view;
    }
}
