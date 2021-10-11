package com.indicia.indicia.ui.finder;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.indicia.indicia.R;
import com.indicia.indicia.finder.Doctor;

import java.util.ArrayList;

public class FragmentDoctor extends Fragment {


    private final ArrayList<Doctor> items;
    private final int index;

    public FragmentDoctor(int index, ArrayList<Doctor> items) {
        this.index = index;
        this.items = items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);
        TextView textViewName = view.findViewById(R.id.doctor_name);
        textViewName.setText(items.get(index).getName());
        TextView textViewFee = view.findViewById(R.id.doctor_visiting_fee);
        textViewFee.setText("Visiting Fee: " + items.get(index).getFee());
        TextView textViewDetails = view.findViewById(R.id.details);
        textViewDetails.setText(items.get(index).getDetails());
        Button buttonTakeAppointment = view.findViewById(R.id.button_take_appointment);
        buttonTakeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Taking Appointment...", Toast.LENGTH_SHORT).show();
                final boolean isSuccessful = true;
                final ProgressDialog progressdialog = new ProgressDialog(getActivity());
                progressdialog.setMessage("Please Wait...");
                progressdialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        progressdialog.dismiss();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (isSuccessful) {
                            Toast.makeText(getContext(), "Appointment Taking Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failed To Take An Appointment", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 2000);
            }
        });
        Button buttonBack = view.findViewById(R.id.button_back_to_doctors_from_doctor);
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

}
