package com.example.sanjeev.alumninetwork.java_classes;

public class motor {

        private int rpm;
        public motor(){
            this.rpm = 190;
        }
        public int getRpm(){
            return rpm;
        }
        public void accelerate(int value){
            rpm = rpm + value;
        }
        public void brake(){
            rpm = 0;
        }
}
