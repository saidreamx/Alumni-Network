package com.example.sanjeev.alumninetwork.profileInfo;

public class profile_model {

    //Variables that are in our json
    private int s_id;
    private String s_course;
    private String s_f_name;
    private String s_l_name;
    private String s_internship;

    //Getters and setters
    public String getname() {
        return s_f_name+" " +s_l_name;
    }

    public void setFName(String name) {
        this.s_l_name = name;
    }
    public void setLName(String name) {
        this.s_f_name = name;
    }

    public String getcourse() {
        return s_course;
    }

    public void setcourse(String ver) {
        this.s_course = ver;
    }

    public String get_internship() {
        return s_internship;
    }

    public void setS_internship(String intnshp) {
        this.s_internship = intnshp;
    }

}

