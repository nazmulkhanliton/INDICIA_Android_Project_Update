package com.indicia.indicia.predictor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.indicia.indicia.R;
import com.indicia.indicia.ui.predictor.DiseasePredictorFragment;
import com.indicia.indicia.ui.predictor.PredictorViewModel;

import java.util.ArrayList;

public class SymptomListAdapter extends ArrayAdapter<SymptomModel> {

    private final DiseasePredictorFragment diseasePredictorFragment;
    private ArrayList<SymptomModel> objects;
    private Context context;
    private int resourceLayout;
    private PredictorViewModel predictorViewModel;

    public SymptomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SymptomModel> objects, DiseasePredictorFragment diseasePredictorFragment) {
        super(context, resource, objects);
        this.context = context;
        this.resourceLayout = resource;
        this.objects = objects;
        this.diseasePredictorFragment = diseasePredictorFragment;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(resourceLayout, parent, false);
        }
        final SymptomModel symptomModel = getItem(position);
        if (symptomModel != null) {
            TextView id = view.findViewById(R.id.textview_symptom_id);
            TextView name = view.findViewById(R.id.textview_symptom_name);
            ImageButton imageButtonDelete = view.findViewById(R.id.imagebutton_delete);
            if (id != null) {
                id.setText("SS" + symptomModel.getId());
            }
            if (name != null) {
                name.setText(symptomModel.getName());
            }
            if (imageButtonDelete != null) {
                imageButtonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        predictorViewModel = ViewModelProviders.of(diseasePredictorFragment).get(PredictorViewModel.class);
                        predictorViewModel.deleteSymptom(symptomModel.getId());
                    }
                });
            }
        }
        return view;
    }
}
