package com.indicia.indicia.ui.predictor;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.indicia.indicia.R;
import com.indicia.indicia.predictor.SymptomListAdapter;
import com.indicia.indicia.predictor.SymptomModel;

import java.util.ArrayList;
import java.util.Objects;

public class DiseasePredictorFragment extends Fragment {

    private PredictorViewModel predictorViewModel;
    private SymptomListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<SymptomModel> symptoms = new ArrayList<>();
        adapter = new SymptomListAdapter(Objects.requireNonNull(getContext()), R.layout.custom_view_listview_object_symptom, symptoms, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        predictorViewModel = ViewModelProviders.of(this).get(PredictorViewModel.class);
        predictorViewModel.getSymptomList().observe(this, new Observer<ArrayList<SymptomModel>>() {

            @Override
            public void onChanged(ArrayList<SymptomModel> symptomModels) {
                adapter.clear();
                adapter.addAll(symptomModels);
                adapter.notifyDataSetChanged();
            }
        });
        return inflater.inflate(R.layout.fragment_predictor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText editTextSearchSymptom = view.findViewById(R.id.edittext_search_symptoms);
        final Button buttonSearchSymptom = view.findViewById(R.id.button_search_symptoms);
        editTextSearchSymptom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    buttonSearchSymptom.setText("Search");
                } else {
                    buttonSearchSymptom.setText("Show All");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        buttonSearchSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symptom = editTextSearchSymptom.getText().toString().trim();
                if (symptom.isEmpty()) {
                    predictorViewModel.setCurrentSymptomForSearch("");
                } else {
                    predictorViewModel.setCurrentSymptomForSearch(symptom);
                }
                translateFragment(new SymptomSearchFragment());
            }
        });
        ListView listViewSymptoms = view.findViewById(R.id.listview_symptoms);
        listViewSymptoms.setAdapter(adapter);
        Button buttonPredictDisease = view.findViewById(R.id.button_predict_disease);
        buttonPredictDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translateFragment(new PredictedDiseaseFragment());
            }
        });
    }

    private void translateFragment(Fragment fragment) {
        if (getFragmentManager() != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}