package com.example.sanjeev.alumninetwork.java_classes;


import com.example.sanjeev.alumninetwork.java_classes.motor;

import javax.inject.Inject;

public class vehicle {

    private motor motor_1;

    @Inject
    public vehicle(motor motor_2){
        this.motor_1 = motor_2;
    }

    public void increaseSpeed(int value){
        motor_1.accelerate(value);
    }

    public void stop(){
        motor_1.brake();
    }

    public int getSpeed(){
        return motor_1.getRpm();
    }
}
