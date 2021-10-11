package com.indicia.indicia.ui.finder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.indicia.indicia.R;

public class FinderFragment extends Fragment {

    private FinderViewModel finderViewModel;

    public static FinderFragment newInstance() {
        return new FinderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finder, container, false);
        View buttonFindDoctor = view.findViewById(R.id.button_find_doctor);
        buttonFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translateFragment(new FragmentDoctorType());
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        finderViewModel = ViewModelProviders.of(this).get(FinderViewModel.class);
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
