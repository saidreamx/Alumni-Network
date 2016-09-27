package com.example.sanjeev.alumninetwork.java_classes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.sanjeev.alumninetwork.R;
import com.example.sanjeev.alumninetwork.component.Daggervehicle_component;
import com.example.sanjeev.alumninetwork.component.vehicle_component;
import com.example.sanjeev.alumninetwork.module.vehicle_module;

public class main_activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        vehicle mVehicle;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        vehicle_component component = Daggervehicle_component.builder().vehicle_module(new vehicle_module()).build();

        mVehicle = component.provideVehicle();

        Toast.makeText(this, String.valueOf(mVehicle.getSpeed()), Toast.LENGTH_SHORT).show();
    }
}
