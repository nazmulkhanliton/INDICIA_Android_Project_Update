package com.indicia.indicia.ui.predictor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.indicia.indicia.R;
import com.indicia.indicia.predictor.SymptomModel;
import com.indicia.indicia.predictor.SymptomSearchListAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class SymptomSearchFragment extends Fragment {

    private PredictorViewModel predictorViewModel;
    private SymptomSearchListAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<SymptomModel> symptoms = new ArrayList<>();
        adapter = new SymptomSearchListAdapter(Objects.requireNonNull(getContext()), R.layout.custom_view_listview_object_symptom_search, symptoms, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_symptom_search, container, false);
        predictorViewModel = ViewModelProviders.of(this).get(PredictorViewModel.class);
        predictorViewModel.getSymptomSearchList().observe(this, new Observer<ArrayList<SymptomModel>>() {
            @Override
            public void onChanged(ArrayList<SymptomModel> symptomModels) {
                TextView textViewTitle = view.findViewById(R.id.textview_matched_symptoms_title);
                TextView textViewNoDisease = view.findViewById(R.id.textview_no_symptoms_matched_message);
                ListView listViewSymptomSearch = view.findViewById(R.id.listview_symptom_search_result);
                if (symptomModels.size() == 0) {
                    textViewTitle.setVisibility(View.GONE);
                    textViewNoDisease.setVisibility(View.VISIBLE);
                    listViewSymptomSearch.setVisibility(View.GONE);
                } else {
                    textViewTitle.setVisibility(View.VISIBLE);
                    textViewNoDisease.setVisibility(View.GONE);
                    listViewSymptomSearch.setVisibility(View.VISIBLE);
                }
                adapter.clear();
                adapter.addAll(symptomModels);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listViewSymptomSearch = view.findViewById(R.id.listview_symptom_search_result);
        listViewSymptomSearch.setAdapter(adapter);
        Button buttonBackToPredictor = view.findViewById(R.id.button_back_to_predictor_from_symptom_search);
        buttonBackToPredictor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
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
