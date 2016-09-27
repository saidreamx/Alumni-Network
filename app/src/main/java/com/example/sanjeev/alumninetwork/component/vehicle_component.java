package com.example.sanjeev.alumninetwork.component;

import com.example.sanjeev.alumninetwork.java_classes.vehicle;
import com.example.sanjeev.alumninetwork.module.vehicle_module;
import javax.inject.Singleton;
import dagger.Component;
import dagger.Module;

@Singleton
    @Component (modules = {vehicle_module.class})
    public interface vehicle_component {
        vehicle provideVehicle();
    }
