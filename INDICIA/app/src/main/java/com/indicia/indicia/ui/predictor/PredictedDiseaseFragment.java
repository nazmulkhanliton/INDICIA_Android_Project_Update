package com.indicia.indicia.ui.predictor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.indicia.indicia.predictor.DiseaseModel;
import com.indicia.indicia.predictor.PredictDiseaseFromSymptoms;
import com.indicia.indicia.predictor.PredictedDiseaseListAdapter;
import com.indicia.indicia.ui.finder.FragmentDoctors;

import java.util.ArrayList;
import java.util.Objects;

public class PredictedDiseaseFragment extends Fragment {

    private PredictorViewModel predictorViewModel;
    private PredictedDiseaseListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runDiseasePredictor();
        ArrayList<DiseaseModel> diseases = new ArrayList<>();
        adapter = new PredictedDiseaseListAdapter(Objects.requireNonNull(getContext()), R.layout.custom_view_listview_object_disease, diseases, this);
    }

    private void runDiseasePredictor() {
        new PredictDiseaseFromSymptoms().predict();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_predicted_disease, container, false);
        predictorViewModel = ViewModelProviders.of(this).get(PredictorViewModel.class);
        predictorViewModel.getDiseaseList().observe(this, new Observer<ArrayList<DiseaseModel>>() {
            @Override
            public void onChanged(ArrayList<DiseaseModel> diseaseModels) {
                TextView textViewPredictedDiseaseTitle = view.findViewById(R.id.textview_predicted_disease_title);
                ListView listViewPredictedDisease = view.findViewById(R.id.listview_predicted_diseases);
                TextView textViewNoDiseaseDetectedMessage = view.findViewById(R.id.textview_no_disease_detected_message);
                if (diseaseModels.size() == 0) {
                    textViewPredictedDiseaseTitle.setVisibility(View.GONE);
                    listViewPredictedDisease.setVisibility(View.GONE);
                    textViewNoDiseaseDetectedMessage.setVisibility(View.VISIBLE);
                } else {
                    textViewPredictedDiseaseTitle.setVisibility(View.VISIBLE);
                    listViewPredictedDisease.setVisibility(View.VISIBLE);
                    textViewNoDiseaseDetectedMessage.setVisibility(View.GONE);
                }
                adapter.clear();
                adapter.addAll(diseaseModels);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonBackToPredictor = view.findViewById(R.id.button_back_to_predictor_from_predicted_disease);
        buttonBackToPredictor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        final ListView listView = view.findViewById(R.id.listview_predicted_diseases);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (predictorViewModel.arrayListDiseases != null) {
                    FragmentDoctors.filterDoctorType = predictorViewModel.arrayListDiseases.get(i).getDoctorType();
                } else {
                    FragmentDoctors.filterDoctorType = "";
                }
                translateFragment(new FragmentDoctors());
            }
        });
        listView.setAdapter(adapter);
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
