package com.example.sanjeev.alumninetwork.module;
import com.example.sanjeev.alumninetwork.java_classes.motor;
import com.example.sanjeev.alumninetwork.java_classes.vehicle;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

    @Module
    public class vehicle_module {

        @Provides @Singleton
        motor provideMotor(){
            return new motor();
        }

        @Provides @Singleton
        vehicle provideVehicle(){
            return new vehicle(new motor());
        }
    }


