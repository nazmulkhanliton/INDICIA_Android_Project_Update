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
import com.indicia.indicia.ui.predictor.PredictorViewModel;
import com.indicia.indicia.ui.predictor.SymptomSearchFragment;

import java.util.ArrayList;

public class SymptomSearchListAdapter extends ArrayAdapter<SymptomModel> {

    private final SymptomSearchFragment symptomSearchFragment;
    private final Context context;
    private final int resourceLayout;
    private PredictorViewModel predictorViewModel;

    public SymptomSearchListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SymptomModel> objects, SymptomSearchFragment symptomSearchFragment) {
        super(context, resource, objects);
        this.context = context;
        this.resourceLayout = resource;
        this.symptomSearchFragment = symptomSearchFragment;
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
            final TextView id = view.findViewById(R.id.textview_symptom_search_id);
            TextView name = view.findViewById(R.id.textview_symptom_search_name);
            final ImageButton imageButtonAddRemove = view.findViewById(R.id.imagebutton_add_remove);
            if (id != null) {
                id.setText("S" + symptomModel.getId().toString());
            }
            if (name != null) {
                name.setText(symptomModel.getName());
            }
            predictorViewModel = ViewModelProviders.of(symptomSearchFragment).get(PredictorViewModel.class);
            if (imageButtonAddRemove != null) {
                if (predictorViewModel.isInArray(symptomModel.getId())) {
                    imageButtonAddRemove.setBackgroundResource(R.drawable.ic_remove_circle_black_24dp);
                } else {
                    imageButtonAddRemove.setBackgroundResource(R.drawable.ic_add_circle_black_24dp);
                }
                imageButtonAddRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (predictorViewModel.isInArray(symptomModel.getId())) {
                            predictorViewModel.deleteSymptom(symptomModel.getId());
                            imageButtonAddRemove.setBackgroundResource(R.drawable.ic_add_circle_black_24dp);
                        } else {
                            predictorViewModel.addSymptom(symptomModel);
                            imageButtonAddRemove.setBackgroundResource(R.drawable.ic_remove_circle_black_24dp);
                        }
                    }
                });
            }
        }
        return view;
    }
}
