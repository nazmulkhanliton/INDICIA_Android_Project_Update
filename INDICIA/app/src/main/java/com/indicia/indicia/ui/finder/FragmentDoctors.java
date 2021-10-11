package com.indicia.indicia.ui.finder;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.indicia.indicia.R;
import com.indicia.indicia.finder.Doctor;
import com.indicia.indicia.finder.DoctorListAdapter;

import java.util.ArrayList;

public class FragmentDoctors extends Fragment {

    public FragmentDoctors() {
    }

    public static String filterDoctorType = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctors, container, false);
        String[] doctors = getContext().getResources().getStringArray(R.array.doctors);
        final ArrayList<Doctor> items = new ArrayList();
        for (int i = 0; i < doctors.length; i++) {
            String[] dct = doctors[i].split("###");
            String type = dct[0];
            String name = dct[1];
            String fee = dct[2];
            String details = dct[3];
            if (filterDoctorType.equals("") || filterDoctorType.equals(type)) {
                items.add(new Doctor(type, name, fee, details));
            }
        }
        filterDoctorType = "";
        ArrayAdapter<Doctor> arrayAdapter = new DoctorListAdapter(getContext(), R.layout.custom_view_listview_object_doctor, items);
        final ListView listView = view.findViewById(R.id.listview_doctors);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                translateFragment(new FragmentDoctor(index, items));
            }
        });
        listView.setAdapter(arrayAdapter);
        Button buttonBack = view.findViewById(R.id.button_back_to_doctor_type_from_doctors);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        return view;
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
