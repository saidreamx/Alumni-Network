package com.example.sanjeev.alumninetwork.profileInfo;

public class profile_model {

    //Variables that are in our json
    private int s_id;
    private String s_course;
    private String s_f_name;
    private String s_l_name;
    private String s_internship;

    //Getters and setters

    public String getS_course() {
        return s_course;
    }

    public String getS_l_name() {
        return s_l_name;
    }

    public String getS_f_name() {
        return s_f_name;
    }

    public int getS_id() {
        return s_id;
    }

    public void setcourse(String ver) {
        this.s_course = ver;
    }

    public String getS_internship() {
        return s_internship;
    }

    public void setS_internship(String intnshp) {
        this.s_internship = intnshp;
    }

}

