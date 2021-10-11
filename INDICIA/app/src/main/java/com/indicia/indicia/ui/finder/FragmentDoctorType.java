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

public class FragmentDoctorType extends Fragment {

    public FragmentDoctorType() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_type, container, false);
        String[] doctorTypes = getContext().getResources().getStringArray(R.array.doctor_type);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, doctorTypes);
        final ListView listView = view.findViewById(R.id.listview_doctor_type);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                FragmentDoctors.filterDoctorType = arrayAdapter.getItem(index);
                translateFragment(new FragmentDoctors());
            }
        });
        listView.setAdapter(arrayAdapter);
        Button buttonBack = view.findViewById(R.id.button_back_to_finder_from_doctor_type);
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
