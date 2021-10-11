package com.indicia.indicia.finder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indicia.indicia.R;

import java.util.ArrayList;

public class DoctorListAdapter extends ArrayAdapter<Doctor> {

    private final Context context;
    private final int resource;

    public DoctorListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Doctor> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(resource, parent, false);
        }
        Doctor doctor = getItem(position);
        if (doctor != null) {
            TextView textViewName = view.findViewById(R.id.textview_doctor_name);
            TextView textViewDetails = view.findViewById(R.id.textview_doctor_details);
            textViewName.setText(doctor.getName());
            textViewDetails.setText(doctor.getType() + " / Visiting Fee: " + doctor.getFee());
        }
        return view;
    }
}
