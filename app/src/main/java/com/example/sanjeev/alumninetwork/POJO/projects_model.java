package com.example.sanjeev.alumninetwork.POJO;

public class projects_model
{
    private String ptitle;
    private String pmentor;
    private String pdescription;

    //Getters and setters
    public String gettitle() {
        return ptitle;
    }

    public void settitle(String title) {
        this.ptitle = title;
    }


    public String getmentor() {
        return pmentor;
    }

    public void setmentor(String mentor) {
        this.pmentor = mentor;
    }

    public String get_description() {
        return pdescription;
    }

    public void set_description(String descp) {
        this.pdescription = descp;
    }

}
